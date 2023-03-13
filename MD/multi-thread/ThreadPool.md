[线程池中多余的线程是如何回收的？](https://mp.weixin.qq.com/s/CyOv2e0hTxzKG6yAgVtbcw)
[Java线程池实现原理及其在美团业务中的实践](https://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651751537&idx=1&sn=c50a434302cc06797828782970da190e&chksm=bd125d3c8a65d42aaf58999c89b6a4749f092441335f3c96067d2d361b9af69ad4ff1b73504c&scene=21#wechat_redirect)

### 1.ThreadPoolExecutor线程池参数介绍

1. corePoolSize：核心线程数，即线程池允许存活空闲的线程数量，设置了allowCoreThreadTimeOut后核心线程也会被回收
2. maximumPoolSize：线程池中允许创建的最大线程数
3. keepAliveTime：当线程数大于核心数时，这部分多余的空闲线程在终止之前等待新任务的最长时间
4. keepAliveTime：keepAliveTime参数的时间单位
5. workQueue：任务队列
6. threadFactory：线程工厂，用于创建线程
7. handler：拒绝策略（达到了线程界限和队列容量）

### 2.执行流程

线程池刚创建时，里面没有线程，当有任务进来：
(1)判断线程池中线程数是否小于核心线程数corePoolSize，小于则创建线程执行任务
(2)如果线程池中线程数等于核心线程数corePoolSize，则把任务加到等待队列workQueue
(3)若队列workQueue已满，则重新创建线程执行任务
(4)若线程池已满，即线程数等于maximumPoolSize，则采用拒绝策略handler处理无法执行的任务

### 3.线程池生命周期：

RUNNING:接受新任务并处理队列中的任务 SHUTDOWN:不接受新任务，但是处理排队中的任务 STOP: 不接受新任务，不处理队列中的任务，并且中断进行中的任务     
TIDYING:所有任务已终止，并且线程池为空，这个状态会调用terminated()
TERMINATED: 调用terminated()后转成这个状态

RUNNING -> SHUTDOWN:调用shutdown()方法
(RUNNING or SHUTDOWN) -> STOP:调用shutdownNow()方法 SHUTDOWN -> TIDYING:当线程池和任务队列都为空时 STOP -> TIDYING:线程池为空 TIDYING ->
TERMINATED:调用terminated()方法

### 4.拒绝策略

1. AbortPolicy（默认），舍弃任务并抛出异常
2. DiscardPolicy，舍弃任务，不抛异常
3. DiscardOldestPolicy，舍弃任务队列中头节点的任务，并执行当前任务（线程池未关闭）
4. CallerRunsPolicy，在调用者的线程中执行当前任务
5. 可实现RejectedExecutionHandler接口自定义拒绝策略

### 线程池中的线程异常

当线程池里面的线程异常后，不会影响线程池里面其他线程的正常执行。线程池会把这个线程移除掉，并创建一个新的线程放到线程池中

- 执行方式是execute时,可以看到堆栈异常的输出
- 当执行方式是submit时,堆栈异常没有输出。但是调用Future.get()方法时，可以捕获到异常

### Executors工具类

Executors工具类可创建五种线程池（前四种都是基于ThreadPoolExecutor）：

1. newFixedThreadPool：固定大小线程池，核心线程数=最大线程数
    - corePoolSize：n(需要指定)
    - maximumPoolSize：n(需要指定)
    - keepAliveTime：0
    - workQueue：LinkedBlockingQueue
    - threadFactory：默认(可以指定)
    - handler：默认
    - 缺点：任务队列为LinkedBlockingQueue，队列大小为Integer.MAX_VALUE，任务过多会出现OOM异常

2. newSingleThreadExecutor：单个线程的线程池
    - corePoolSize：1
    - maximumPoolSize：1
    - keepAliveTime：0
    - workQueue：LinkedBlockingQueue
    - threadFactory：默认(可以指定)
    - handler：默认
    - 使用场景：需要顺序执行提交的任务，在该线程停止或发生异常时，newSingleThreadExecutor线程池会启动一个新的线程来代替该线程继续执行任务
    - 缺点：任务队列为LinkedBlockingQueue，队列大小为Integer.MAX_VALUE，任务过多会出现OOM异常

3. newCachedThreadPool：无界线程池，核心线程数为0，最大线程数为Integer.MAX_VALUE，空闲时间60s
    - corePoolSize：0
    - maximumPoolSize：Integer.MAX_VALUE
    - keepAliveTime：60
    - keepAliveTime：TimeUnit.SECONDS
    - workQueue：SynchronousQueue
    - threadFactory：默认(可以指定)
    - handler：默认
    - 使用场景：执行时间很短的大量任务需要执行的情况下使用
    - 缺点：允许创建的线程数为Integer.MAX_VALUE，可能会创建大量的线程，从而引起OOM异常

4. newScheduledThreadPool：延迟或者定时执行任务的线程池
    - corePoolSize：n(需要指定)
    - maximumPoolSize：Integer.MAX_VALUE
    - keepAliveTime：10
    - keepAliveTime：TimeUnit.MILLISECONDS
    - workQueue：DelayedWorkQueue
    - threadFactory：默认(可以指定)
    - handler：默认
    - 内部通过ScheduledThreadPoolExecutor实现，而ScheduledThreadPoolExecutor继承了ThreadPoolExecutor，所以实质上
      还是通过ThreadPoolExecutor实现的
    - 缺点：允许创建的线程数为Integer.MAX_VALUE，可能会创建大量的线程，从而引起OOM异常

5 .newWorkStealingPool：窃取线程池，基于ForkJoinPool

### 线程池线程数设置

- CPU密集型(计算密集型) => CPU数量 + 1
- IO密集型 => CPU数量 * CPU利用率 * (1 + 线程等待时间/线程CPU时间)(或者直接为CPU数量的2倍)
- CPU数量Ncpu=Runtime.getRuntime().availableProcessors()