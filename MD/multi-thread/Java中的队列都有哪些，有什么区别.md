add:增加一个元索(内部调用offer添加),如果队列已满，则抛出一个IllegalStateException异常
put        :添加一个元素,如果队列满，则阻塞
offer      添加一个元素并返回true,如果队列已满，则返回false

remove     移除并返回队列头部的元素(内部调用poll),如果队列为空，则抛出一个NoSuchElementException异常
take       移除并返回队列头部的元素,如果队列为空，则阻塞
poll       移除并返问队列头部的元素,如果队列为空，则返回null

peek       返回队列头部的元素,如果队列为空，则返回null
element    返回队列头部的元素,如果队列为空，则抛出一个NoSuchElementException异常


###1.ArrayBlockingQueue阻塞队列
    数据结构：Object[] 数组
    是否有界：有界，初始化时需要指定队列大小
    是否线程安全：线程安全，采用ReentrantLock锁(只有一把锁)

###2.LinkedBlockingQueue阻塞队列
    数据结构：单向链表
    是否有界：有界，初始化时指定容量，不指定则为Integer.MAX_VALUE
    是否线程安全：线程安全，采用ReentrantLock锁(添加和获取各有一把锁)

###3.ConcurrentLinkedQueue非阻塞队列
    数据结构：单向链表
    是否有界：无界
    是否线程安全：线程安全，采用CAS



###4.PriorityQueue优先级队列(非阻塞)
    数据结构：Object[]数组
    是否有界：有界，默认容量11，最大容量Integer.MAX_VALUE - 8
    是否线程安全：非线程安全

###5.PriorityBlockingQueue优先级队列(阻塞队列)
    数据结构：Object[]数组
    是否有界：有界，默认容量11，最大容量Integer.MAX_VALUE - 8
    是否线程安全：线程安全，采用ReentrantLock锁(只有一把锁)
    扩容方式：有用到CAS，oldCap + ((oldCap < 64) ? (oldCap + 2) : (oldCap >> 1));

###6.DelayQueue延迟队列(阻塞)
    数据结构：内部维护一个PriorityQueue队列
    是否有界：同PriorityQueue
    是否线程安全：线程安全，采用ReentrantLock
    元素必须继承Delayed并实现其getDelay方法
         
###7.                                         
###8.                                         
###9.                                         
###10.                                         