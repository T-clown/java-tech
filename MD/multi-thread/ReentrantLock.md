ReentrantLock
可重入锁
公平锁，非公平锁
内部类Sync通过继承AbstractQueuedSynchronizer并实现方法
无法获取锁的线程加入双向链表，并LockSupport.park()让线程等待
释放锁的时候，若state==0，则取出链表第一个Node，并用LockSupport.unpark()唤醒线程
内部的ConditionObject，也是一个链表，类似于加锁和释放锁(都用到了LockSupport)
