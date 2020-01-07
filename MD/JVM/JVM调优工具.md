原文链接：https://www.iteye.com/blog/josh-persistence-2161848
1.jps(Java Virtual Machine Process Status Tool)
作用：jps主要用来输出JVM中运行的进程状态信息
语法：jps [options] [hostid]  如果不指定hostid就默认为当前主机或服务器
-q 不输出类名、Jar名和传入main方法的参数
-m 输出传入main方法的参数
-l 输出main类或Jar的全限名
-v 输出传入JVM的参数

2.jstack
作用：jstack主要用来查看某个Java进程内的线程堆栈信息
语法：jstack [option] pid
      jstack [option] executable core
      jstack [option] [server-id@]remote-hostname-or-ip
-l long listings，会打印出额外的锁信息，在发生死锁时可以用jstack -l pid来观察锁持有情况
-m mixed mode，不仅会输出Java堆栈信息，还会输出C/C++堆栈信息（比如Native方法）
jstack可以定位到线程堆栈，根据堆栈信息我们可以定位到具体代码，所以它在JVM性能调优中使用得非常多

案例：找出某个Java进程中最耗费CPU的Java线程并定位堆栈信息
(1)先找出Java进程ID，服务器上的Java应用名称为wordcount.jar
    ps -ef | grep wordcount | grep -v grep  
(2)找出该进程内最耗费CPU的线程
    ps -Lfp pid ： 即 ps -Lfp 2860  
    ps -mp pid -o THREAD, tid, time ：即 ps -mp 2860 -o THREAD,tid,time  
    top -Hp pid： 即 top -Hp 2860
    TIME列就是各个Java线程耗费的CPU时间
    printf "%x\n" 2968 :得到2968的十六进制值为b98
(3)jstack 2860 | grep b98

3. jmap（Memory Map）和 jhat（Java Heap Analysis Tool）
作用： jmap用来查看堆内存使用状况，一般结合jhat使用
语法：jmap [option] pid  
      jmap [option] executable core  
      jmap [option] [server-id@]remote-hostname-or-ip 
(1)查看进程堆内存使用情况:包括使用的GC算法、堆配置参数和各代中堆内存使用：jmap -heap pid
(2)查看堆内存中的对象数目、大小统计直方图，如果带上live则只统计活对象：jmap -histo[:live] pid
(3)用jmap把进程内存使用情况dump到文件中，再用jhat分析查看。dump出来的文件还可以用MAT、VisualVM等工具查看
   jmap -dump:format=b,file=dumpFileName pid(如：jmap -dump:format=b,file=/home/dump.dat 2860  )
(4)jhat -port 8888 /home/dump.dat
   注意如果Dump文件太大，可能需要加上-J-Xmx512m参数以指定最大堆内存，即jhat -J-Xmx512m -port 8888 /home/dump.dat。
   然后就可以在浏览器中输入主机地址:8888查看了
   
4.jstat（JVM统计监测工具） 
作用：看看各个区内存和GC的情况
语法：jstat [ generalOption | outputOptions vmid [interval[s|ms] [count]] ]  (如jstat -gc 2860 250 6  )

5.hprof（Heap/CPU Profiling Tool）
作用：hprof能够展现CPU使用率，统计堆内存使用情况
语法：java -agentlib:hprof[=options] ToBeProfiledClass  
     java -Xrunprof[:options] ToBeProfiledClass  
     javac -J-agentlib:hprof[=options] ToBeProfiledClass  

分析死锁：
jstack -l pid > deadlock.jstack