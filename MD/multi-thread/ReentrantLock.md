

### ReentrantLock
1. 可重入锁
2. 公平锁，非公平锁
3. 内部类Sync通过继承AbstractQueuedSynchronizer并实现方法
4. 无法获取锁的线程加入双向链表，并LockSupport.park()让线程等待
5. 释放锁的时候，若state==0，则取出链表第一个Node，并用LockSupport.unpark()唤醒线程
6. 内部的ConditionObject，也是一个链表，类似于加锁和释放锁(都用到了LockSupport)

##特点
1. 可重⼊
   - ReentrantLock 和 synchronized 关键字⼀样，都是可重⼊锁，不过两者实现原理稍有差
   别， ReentrantLock 利⽤ AQS 的的 state 状态来判断资源是否已锁，同⼀线程重⼊加锁， state 的
   状态 +1 ; 同⼀线程重⼊解锁, state 状态 -1 (解锁必须为当前独占线程，否则异 常); 当 state 为 0 时
   解锁成功
2. 需要⼿动加锁、解锁
   - synchronized 关键字是⾃动进⾏加锁、解锁的，⽽ ReentrantLock 需要
   lock() 和 unlock() ⽅法配合 try/finally 语句块来完成，来⼿动加锁、解锁。
3. ⽀持设置锁的超时时间
   - synchronized 关键字⽆法设置锁的超时时间，如果⼀个获得锁的线程内部
   发⽣死锁，那 么其他线程就会⼀直进⼊阻塞状态，⽽ ReentrantLock 提供 tryLock ⽅法，允许设
   置线 程获取锁的超时时间，如果超时，则跳过，不进⾏任何操作，避免死锁的发⽣。
4. ⽀持公平/⾮公平锁
   - synchronized 关键字是⼀种⾮公平锁，先抢到锁的线程先执⾏。⽽
   ReentrantLock 的 构造⽅法中允许设置 true/false 来实现公平、⾮公平锁，如果设置为 true ，则
   线程获取 锁要遵循"先来后到"的规则，每次都会构造⼀个线程 Node ，然后到双向链表的"尾
   巴"后⾯排队，等待前⾯的 Node 释放锁资源
5. 可中断锁
   - ReentrantLock 中的 lockInterruptibly() ⽅法使得线程可以在被阻塞时响应中断，⽐ 如
   ⼀个线程 t1 通过 lockInterruptibly() ⽅法获取到⼀个可重⼊锁，并执⾏⼀个⻓时间 的任务，
   另⼀个线程通过 interrupt() ⽅法就可以⽴刻打断 t1 线程的执⾏，来获取t1持 有的那个可重⼊锁。⽽通
   过 ReentrantLock 的 lock() ⽅法或者 synchronized 持有锁 的线程是不会响应其他线程的
   interrupt() ⽅法的，直到该⽅法主动释放锁之后才会响应 interrupt() ⽅法
