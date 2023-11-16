[线上服务内存OOM问题定位三板斧](https://mp.weixin.qq.com/s/iOC1fiKDItn3QY5abWIelg)
[JVM 内存分析工具 MAT 的深度讲解与实践——入门篇](https://juejin.cn/post/6908665391136899079#heading-5)
[JVM 内存分析工具 MAT 的深度讲解与实践——进阶篇](https://juejin.cn/post/6911624328472133646)

### 问题产生原因
1. 老年代内存不足（大对象过多或内存泄漏）
2. Metaspace 空间不足
3. 代码主动触发 System.gc()
4. YGC 时的悲观策略
5. dump live 的内存信息时，比如 jmap -dump:live

1. 本身资源不够，如堆内存太小，可加大内存

2. 申请的太多内存，没有及时释放，如内存泄漏
    - 申请完资源后，未调用close()或dispose()释放资源
    - 消费者消费速度慢（或停止消费了），而生产者不断往队列中投递任务，导致队列中任务累积过多

3. 资源耗尽，如创建线程

### 问题定位方式一

1. 找到进程id
    - jps
    - ps
2. 找到最耗内存的对象
    - jmap -histo:live 3059 | more
    - jhsdb jmap --histo --pid 3059 | more
3. 确认是否是资源耗尽
    - pstree
    - netstat -lap | fgrep port (查看某个端口连接情况)
    - ll /proc/11826/fd | wc -l (查看进程的句柄数量)
    - ll /proc/11826/task | wc -l  (查看进程的线程数量)
    - ps -p 11826 -L | wc -l  (查看进程的线程数量)
