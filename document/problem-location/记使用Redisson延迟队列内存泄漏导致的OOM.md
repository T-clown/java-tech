背景：因为项目中的考试功能需要在考试结束的时候进行自动提交，因此需要一个执行延迟任务的功能，通过查询资料，实现延迟队列的功能有很多，我大体分为四大类
一类是单机版本，如jdk的DelayedQueue，netty的HashTimeWheel等
一类是依赖redis的zset实现
一类是依赖消息队列实现，如RabbitMQ
一类是依赖定时任务扫描
综合考虑下，选择了基于redis的实现，即选择现有的java框架redisson（单机版缺点一是容易OOM，二是任务重复执行，三是任务易丢失，消息队列因为用的是kafka，不能直接支持此功能，定时任务会全量扫描，代价大，延迟高，极限情况下延迟时间约定于扫描间隔时间）
redisson版本：3.10.6

某一天早上十点的时候，测试人员在群里说测试环境不可用，问是不是有人在发版，但是没人发版，服务是运行的，
为了不阻塞测试的进度，重启了服务。
但是这个引起了我的警觉，这种现象明显是服务假死，很可能产生了OOM，而且是比较严重的那种，因为白天要改bug发版，所以没暴露出来，而就一晚上的时间就出现了OOM，结果一去查看启动命令设置的dump目录，发现果然有一个9点40分产生的堆转储文件
把dump文件下载到本地，使用MAT打开
在概览里面发现有一块内存占比特别大![img.png](img.png)
进入Leak Suspects![img_1.png](img_1.png)，发现一个内存泄漏点，内存占比达到64.9%，涉及到的关键类有org.redisson.connection.SingleConnectionManager和io.netty.util.concurrent.GenericFutureListener[]数组
点击下面的详情，查看产生内存泄漏线程的堆栈跟踪![img_2.png](img_2.png)，找到产生内存泄漏的具体代码行![img_3.png](img_3.png)，![img_4.png](img_4.png)，所以定位到是使用了Redisson的延迟队列产生了内存泄漏


这个时候就要分析产生内存泄漏的原因
查看对象的支配树(Dominator Tree)，并根据Retained Heap从大到小排序![img_5.png](img_5.png)，发现一个超大对象SingleConnectionManager，再查看此对象的List Objects的外部引用
发现在其引用链中有一个超大的GenericFutureListener对象数组![img_6.png](img_6.png)，数组长度为16384，数组中有13345个元素

通过本地debug调试并结合查看源码，发现每次take的时候，都会调用shutdownPromise去注册一个监听器并添加到GenericFutureListener数组里面，但是源码中却并没有找到移除监听器的地方
而唯一移除监听器的地方却是Redisson的MasterSlaveConnectionManager类执行shutdown方法的时候（也就是redis下线），会去执行所有监听器，然后把字段置为null进行释放内存
到此确定了问题产生的原因

去github查看redisson从3.10.6版本以后的更新记录，发现在3.10.7有一行说明修复了阻塞队列调用时产生的内存泄漏![img_7.png](img_7.png)
再去查看3.10.7版本提交的代码发现在回调函数中添加了一行移除监听器的代码![img_8.png](img_8.png)，至此内存泄漏的问题分析完毕
CommandAsyncService类的handleBlockingOperations方法修改前后对比
修改前：![img_10.png](img_10.png)   修改后：![img_9.png](img_9.png)

问题本地复现，启动项目后并用VisualVM进行实时观察，创建30000个延迟任务，延迟任务执行完毕的时候，发现ImmediateEventExecutor$ImmediatePromise数量明显增加，且数量一直没变，也就是说一直没有被回收
观察老年代内存发现占比明显增加，则问题复现完毕![img_16.png](img_16.png)
测试前：内存占比：![img_11.png](img_11.png) 实例直方图：![img_12.png](img_12.png) 堆内存：![img_13.png](img_13.png)(次图中每个波谷就代表一次GC,上面的Perform GC按钮可手动进行FGC)
测试后：实例直方图：![img_14.png](img_14.png) 内存占比：![img_15.png](img_15.png)
可发现io.netty.util.concurrent.ImmediateEventExecutor$ImmediatePromise，org.redisson.misc.RedissonPromise，org.redisson.command.AsyncDetails等这几个实例内存占比很大，但是实例数量一直没变化，即便每次Minor GC或者
手动执行Full GC也没有变化，代表这些实例一直没有被回收，且通过测试前后的内存占比来看，老年代的使用内存有明显增多，所以这几个实例是出现内存泄漏的罪魁祸首