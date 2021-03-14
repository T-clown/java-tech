[](https://segmentfault.com/a/1190000010603813)
###1.内存调优   通过这些参数可以对JVM的内存分配做调整
    堆区初始值
    -Xms2g 或 -XX:InitialHeapSize=2048m
    
    堆区最大值
    -Xmx2g 或 -XX:MaxHeapSize=2048m
    
    新生代最大值
    -Xmn512m 或 -XX:MaxNewSize=512m
    
    永久代初始值(JDK1.8以后已废弃)
    -XX:PermSize=128m
    
    永久代最大值(JDK1.8以后已废弃)
    -XX:MaxPermSize=256m
    
    元数据区初始大小(JDK1.8以后用于替换PermSize)
    -XX:MetaspaceSize=128m
    
    元数据区最大值(JDK1.8以后用于替换MaxPermSize)
    -XX:MaxMetaspaceSize=256m
    
    线程栈最大值
    -Xss256k 或 -XX:ThreadStackSize=256k
    
    最大直接内存（堆外）大小
    -XX:MaxDirectMemorySize=256m


###2.GC策略相关    通过这些参数可以对JVM的GC性能进行调优
    -XX:SurvivorRatio=6
    eden区和survivor的比值
    假如设为6，则表示每个survivor区跟eden区的比值为1:6,每个survivor区占新生代的八分之一
    
     -XX:PretenureSizeThreshold=1000000
    在新生代直接分配的对象最大值，0表示没有最大值，大于这个值的对象直接在老年代分配
    设置该参数，可以使大于这个值的对象直接在老年代分配，避免在Eden区和Survivor区发生大量的内存复制，该参数只对Serial和ParNew收集器有效，Parallel Scavenge并不认识该参数
    
     -XX:MaxTenuringThreshold=15
    年轻代最大年龄，每个对象在坚持过一次Minor GC之后，年龄就增加1，当超过这个参数值时就进入老年代，最大支持15
    
    开启 -XX:+UseSerialGC
    关闭 -XX:-UseSerialGC
    年轻代使用Serial垃圾收集器，不推荐使用，性能太差，老年代将会使用SerialOld垃圾收集器
    
    开启 -XX:+UseParNewGC
    关闭 -XX:-UseParNewGC
    年轻代使用ParNew垃圾收集器
    
    -XX:ParallelGCThreads=16
    并行执行gc的线程数
    
    开启 -XX:+UseParallelGC
    关闭 -XX:-UseParallelGC
    年轻代使用Parallel Scavenge垃圾收集器
    Linux下1.6,1.7,1.8默认开启，老年代将会使用SerialOld垃圾收集器
    
    开启 -XX:+UseParallelOldGC
    关闭 -XX:-UseParallelOldGC
    年轻代使用Parallel Scavenge垃圾收集器，老年代将会使用Parallel Old收集器
   
    开启 -XX:+UseConcMarkSweepGC
    关闭 -XX:-UseConcMarkSweepGC
    老年代使用CMS收集器（如果出现"Concurrent Mode Failure"，会使用SerialOld收集器）
    年轻代将会使用ParNew收集器
    
    -XX:+CMSInitiatingOccupancyFraction=75
    触发执行CMS回收的当前年代区内存占用的百分比，负值表示使用CMSTriggerRatio设置的值
    
    开启 -XX:+UseCMSInitiatingOccupancyOnly
    关闭 -XX:-UseCMSInitiatingOccupancyOnly
    只根据占用情况作为开始执行CMS收集的标准，默认关闭
    
    开启 -XX:+UseCMSCompactAtFullCollection
    关闭 -XX:-UseCMSCompactAtFullCollection
    使用CMS执行Full GC时对内存进行压缩，默认关闭
    
    -XX:CMSFullGCsBeforeCompaction=1
    多少次FGC后进行内存压缩
    
    开启 -XX:+CMSClassUnloadingEnabled
    关闭 -XX:-CMSClassUnloadingEnabled
    当使用CMS GC时是否启用类卸载功能，默认关闭
    
    开启 -XX:+CMSParallelRemarkEnabled
    关闭 -XX:-CMSParallelRemarkEnabled
    是否启用并行标记（仅限于ParNewGC），默认关闭
    
    开启 -XX:+UseG1GC
    关闭 -XX:-UseG1GC
    使用G1垃圾收集器

    -XX:MaxGCPauseMillis=200
    自适应大小策略的最大GC暂停时间目标（以毫秒为单位），或（仅G1）每个MMU时间片的最大GC时间
    
    开启 -XX:+DisableExplicitGC
    关闭 -XX:-DisableExplicitGC
    禁用System.gc()触发FullGC
    PS:不建议开启，如果开启了这个参数可能会导致对外内存无法及时回收造成对外内存溢出
    
###3.GC日志相关    通过这些参数可以对JVM的GC日志输出进行配置，方便分析
    -Xloggc:/data/gclog/gc.log
    GC日志文件路径
    
    开启 -XX:+UseGCLogFileRotation
    关闭 -XX:-UseGCLogFileRotation
    滚动GC日志文件，须配置Xloggc

    -XX:NumberOfGCLogFiles=4
    滚动GC日志文件数，默认0，不滚动
    
    -XX:GCLogFileSize=100k
    GC文件滚动大小，需配置UseGCLogFileRotation，设置为0表示仅通过jcmd命令触发
    
    开启 -XX:+PrintGCDetails
    关闭 -XX:-PrintGCDetails
    GC时打印更多详细信息，默认关闭
    可以通过jinfo -flag [+|-]PrintGCDetails <pid> 或 jinfo -flag PrintGCDetails=<value> <pid> 来动态开启或设置值
    
    开启 -XX:+PrintGCDateStamps
    关闭 -XX:-PrintGCDateStamps
    GC时打印时间戳信息，默认关闭
    可以通过jinfo -flag [+|-]PrintGCDateStamps <pid> 或 jinfo -flag PrintGCDateStamps=<value> <pid> 来动态开启或设置值
    
    开启 -XX:+PrintTenuringDistribution
    关闭 -XX:-PrintTenuringDistribution
    打印存活实例年龄信息，默认关闭
    
    开启 -XX:+PrintGCApplicationStoppedTime
    关闭 -XX:-PrintGCApplicationStoppedTime
    打印应用暂停时间，默认关闭
    
    开启 -XX:+PrintHeapAtGC
    关闭 -XX:-PrintHeapAtGC
    GC前后打印堆区使用信息，默认关闭
    
###4.异常相关   通过这些参数可以在JVM异常情况下执行某些操作，以保留现场做分析用
    开启 -XX:+HeapDumpOnOutOfMemoryError
    关闭 -XX:-HeapDumpOnOutOfMemoryError
    抛出内存溢出错误时导出堆信息到指定文件，默认关闭
    可以通过jinfo -flag [+|-]HeapDumpOnOutOfMemoryError <pid> 或 jinfo -flag HeapDumpOnOutOfMemoryError=<value> <pid> 来动态开启或设置值
    
    -XX:HeapDumpPath=/data/dump/jvm.dump
    当HeapDumpOnOutOfMemoryError开启的时候，dump文件的保存路径，默认为工作目录下的java_pid<pid>.hprof文件
    除非必要，建议不设置
 ###5.问题定位及优化相关
    -server
    使用服务端模式
    
    开启 -XX:+TieredCompilation
    关闭 -XX:-TieredCompilation
    启用多层编译
    java 1.8默认开启分层编译，该参数无效
    
    开启详细信息 -XX:NativeMemoryTracking=detail
    开启概要信息 -XX:NativeMemoryTracking=summary
    开启本机内存追踪
    开启的话，大概会增加5%-10%的性能消耗
    
    -XX:+UnlockDiagnosticVMOptions
    解锁对JVM进行诊断的选项参数，默认关闭
    
    -XX:+PrintNMTStatistics
    在jvm shutdown的时候输出整体的native memory统计，默认关闭
    必须配合参数-XX:+UnlockDiagnosticVMOptions使用，并且只能加在其后才能生效
    
    开启 -XX:+UseAdaptiveSizePolicy
    关闭 -XX:-UseAdaptiveSizePolicy
    使用自适应分代内存策略
    1.7以后默认会开启该参数，如果使用CMS回收算法，则会关闭该参数，该参数开启以后会使SurvivorRatio参数失效，如果显示指定了SurvivorRatio，需要关闭该参数

    