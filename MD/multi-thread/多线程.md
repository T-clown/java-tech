ReentrantLock
可重入锁
公平锁，非公平锁
内部类Sync通过继承AbstractQueuedSynchronizer并实现方法
无法获取锁的线程加入双向链表，并LockSupport.park()让线程等待
释放锁的时候，若state==0，则取出链表第一个Node，并用LockSupport.unpark()唤醒线程
内部的ConditionObject，也是一个链表，类似于加锁和释放锁(都用到了LockSupport)



ThreadPoolExecutor
参数
corePoolSize：核心线程数，即线程池允许存活空闲的线程数量，设置了allowCoreThreadTimeOut后核心线程也会被回收
maximumPoolSize：线程池中允许创建的最大线程数
keepAliveTime：当线程数大于核心数时，这部分多余的空闲线程在终止之前等待新任务的最长时间
keepAliveTime：keepAliveTime参数的时间单位
workQueue：任务队列
threadFactory：线程工厂，用于创建线程
handler：拒绝策略（达到了线程界限和队列容量）

线程池刚创建时，里面没有线程，当有任务进来：
1.如果workCount<corePoolSize，创建新线程执行任务
2.若线程池中存活的线程数等于核心线程数，将任务添加到任务队列
3.当任务队列满时，并且workCount<maximumPoolSize，创建新的线程
4.当队列满时，并且工作线程等于最大线程数，则执行拒绝策略


线程池生命周期：
RUNNING:接受新任务并处理队列中的任务
SHUTDOWN:不接受新任务，但是处理排队中的任务 
STOP: 不接受新任务，不处理队列中的任务，并且中断进行中的任务     
TIDYING:所有任务已终止，并且线程池为空，这个状态会调用terminated()
TERMINATED: 调用terminated()后转成这个状态

RUNNING -> SHUTDOWN:调用shutdown()方法
(RUNNING or SHUTDOWN) -> STOP:调用shutdownNow()方法
SHUTDOWN -> TIDYING:当线程池和任务队列都为空时
STOP -> TIDYING:线程池为空
TIDYING -> TERMINATED:调用terminated()方法

拒绝策略
1.AbortPolicy（默认），舍弃任务并抛出异常
2.DiscardPolicy，舍弃任务，不抛异常
3.DiscardOldestPolicy，舍弃任务队列中头节点的任务，并执行当前任务（线程池未关闭）
4.CallerRunsPolicy，在调用者的线程中执行当前任务

当线程池里面的线程异常后，不会影响线程池里面其他线程的正常执行。线程池会把这个线程移除掉，并创建一个新的线程放到线程池中
执行方式是execute时,可以看到堆栈异常的输出
当执行方式是submit时,堆栈异常没有输出。但是调用Future.get()方法时，可以捕获到异常

Executors工具类可创建五种线程池（前四种基于ThreadPoolExecutor）：
1.newFixedThreadPool：固定大小线程池，核心线程数=最大线程数
2.newSingleThreadExecutor：单个线程的线程池
3.newCachedThreadPool：无界线程池，核心线程数为0，最大线程数为Integer.MAX_VALUE，空闲时间60s
4.newScheduledThreadPool：延迟或者定时执行任务的线程池
5.newWorkStealingPool：ForkJoinPool线程池


