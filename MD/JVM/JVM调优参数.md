来源：https://segmentfault.com/a/1190000010603813
1.内存相关
堆区初始值
-Xms512m 或 -XX:InitialHeapSize=512m

堆区最大值
-Xmx512m 或 -XX:MaxHeapSize=512m

新生代最大值
-Xmn512m 或 -XX:MaxNewSize=512m

永久代初始值
-XX:PermSize=128m

永久代最大值
-XX:MaxPermSize=256m

线程栈最大值
-Xss256k 或 -XX:ThreadStackSize=256k


2.GC策略相关
eden区和survivor的比值
-XX:SurvivorRatio=6

在新生代直接分配的对象最大值，0表示没有最大值，大于这个值的对象直接在老年代分配
避免在Eden区和Survivor区发生大量的内存复制，该参数只对Serial和ParNew收集器有效，Parallel Scavenge并不认识该参数
-XX:PretenureSizeThreshold=10000

年轻代最大年龄，每个对象在坚持过一次Minor GC之后，年龄就增加1，当超过这个参数值时就进入老年代，最大支持15
-XX:MaxTenuringThreshold=10

年轻代使用Serial垃圾收集器，不推荐使用，性能太差，老年代将会使用SerialOld垃圾收集器
开启 -XX:+UseSerialGC
关闭 -XX:-UseSerialGC

年轻代使用ParNew垃圾收集器
开启 -XX:+UseParNewGC
关闭 -XX:-UseParNewGC

并行执行gc的线程数
-XX:ParallelGCThreads=16

年轻代使用Parallel Scavenge垃圾收集器，老年代将会使用SerialOld垃圾收集器
开启 -XX:+UseParallelGC
关闭 -XX:-UseParallelGC
年轻代使用Parallel Scavenge垃圾收集器，老年代将会使用Parallel Old收集器
开启 -XX:+UseParallelOldGC
关闭 -XX:-UseParallelOldGC

老年代使用CMS收集器（如果出现"Concurrent Mode Failure"，会使用SerialOld收集器）
年轻代将会使用ParNew收集器
开启 -XX:+UseConcMarkSweepGC
关闭 -XX:-UseConcMarkSweepGC

触发执行CMS回收的当前年代区内存占用的百分比，负值表示使用CMSTriggerRatio设置的值
-XX:+CMSInitiatingOccupancyFraction=75

只根据占用情况作为开始执行CMS收集的标准
开启 -XX:+UseCMSInitiatingOccupancyOnly
关闭 -XX:-UseCMSInitiatingOccupancyOnly

使用CMS执行Full GC时对内存进行压缩
开启 -XX:+UseCMSCompactAtFullCollection
关闭 -XX:-UseCMSCompactAtFullCollection

多少次FGC后进行内存压缩
-XX:CMSFullGCsBeforeCompaction=1

当使用CMS GC时是否启用类卸载功能
开启 -XX:+CMSClassUnloadingEnabled
关闭 -XX:-CMSClassUnloadingEnabled

是否启用并行标记（仅限于ParNewGC）
开启 -XX:+CMSParallelRemarkEnabled
关闭 -XX:-CMSParallelRemarkEnabled

使用G1垃圾收集器
开启 -XX:+UseG1GC
关闭 -XX:-UseG1GC

自适应大小策略的最大GC暂停时间目标（以毫秒为单位），或（仅G1）每个MMU时间片的最大GC时间
-XX:MaxGCPauseMillis=200

禁用System.gc()触发FullGC
开启 -XX:+DisableExplicitGC
关闭 -XX:-DisableExplicitGC
PS:不建议开启，如果开启了这个参数可能会导致对外内存无法及时回收造成对外内存溢出

3.GC日志相关
GC日志文件路径
-Xloggc:/data/gclog/gc.log

滚动GC日志文件，须配置Xloggc
开启 -XX:+UseGCLogFileRotation
关闭 -XX:-UseGCLogFileRotation

滚动GC日志文件数，默认0，不滚动
-XX:NumberOfGCLogFiles=4

GC文件滚动大小，需配置UseGCLogFileRotation，设置为0表示仅通过jcmd命令触发
-XX:GCLogFileSize=100k

GC时打印更多详细信息
开启 -XX:+PrintGCDetails
关闭 -XX:-PrintGCDetails
可以通过jinfo -flag [+|-]PrintGCDetails <pid> 或 jinfo -flag PrintGCDetails=<value> <pid> 来动态开启或设置值

GC时打印时间戳信息
开启 -XX:+PrintGCDateStamps
关闭 -XX:-PrintGCDateStamps
可以通过jinfo -flag [+|-]PrintGCDateStamps <pid> 或 jinfo -flag PrintGCDateStamps=<value> <pid> 来动态开启或设置值

打印存活实例年龄信息
开启 -XX:+PrintTenuringDistribution
关闭 -XX:-PrintTenuringDistribution

打印应用暂停时间
开启 -XX:+PrintGCApplicationStoppedTime
关闭 -XX:-PrintGCApplicationStoppedTime

GC前后打印堆区使用信息
开启 -XX:+PrintHeapAtGC
关闭 -XX:-PrintHeapAtGC

4.异常相关
抛出内存溢出错误时导出堆信息到指定文件
开启 -XX:+HeapDumpOnOutOfMemoryError
关闭 -XX:-HeapDumpOnOutOfMemoryError
可以通过jinfo -flag [+|-]HeapDumpOnOutOfMemoryError <pid> 或 jinfo -flag HeapDumpOnOutOfMemoryError=<value> <pid> 来动态开启或设置值

当HeapDumpOnOutOfMemoryError开启的时候，dump文件的保存路径，默认为工作目录下的java_pid<pid>.hprof文件
-XX:HeapDumpPath=/data/dump/jvm.dump

服务端模式
-server

启用多层编译
开启 -XX:+TieredCompilation
关闭 -XX:-TieredCompilation