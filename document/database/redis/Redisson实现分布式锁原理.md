[](https://www.yht7.com/news/2473)
[](https://www.cnblogs.com/keeya/p/14332131.html)

### 高效分布式锁需要满足的一些条件

1. 互斥：在分布式高并发的条件下，我们最需要保证，同一时刻只能有一个线程获得锁，这是最基本的一点
2. 防止死锁：在分布式高并发的条件下，比如有个线程获得锁的同时，还没有来得及去释放锁，就因为系统故障或者其它原因使它无法执行释放锁的命令,导致其它线程都无法获得锁，造成死锁。
   所以分布式非常有必要设置锁的有效时间，确保系统出现故障后，在一定时间内能够主动去释放锁，避免造成死锁的情况
3. 性能：对于访问量大的共享资源，需要考虑减少锁等待的时间，避免导致大量线程阻塞。所以在锁的设计时，需要考虑两点
   锁的颗粒度要尽量小。比如你要通过锁来减库存，那这个锁的名称你可以设置成是商品的ID,而不是任取名称。这样这个锁只对当前商品有效,锁的颗粒度小。 锁的范围尽量要小。比如只要锁2行代码就可以解决问题的，那就不要去锁10行代码了
4. 重入：同一个线程可以重复拿到同一个资源的锁。重入锁非常有利于资源的高效利用

### Redisson原理分析

1. redisson分布式锁用到的redis数据类型为hash，名称为加锁的key值，内部键值对的键为uuid:线程id，其中uuid为当前redisson实例的id，值为数字，代表线程加锁的重入次数
2. 加锁机制： 线程去获取锁，获取成功， 执行lua脚本，保存数据到redis数据库。 线程去获取锁，获取失败: 一直通过while循环尝试获取锁，获取成功后，执行lua脚本，保存数据到redis数据库。
3. redisson的watchdog
    - 解决问题：普通的分布式锁因为加了超时时间，如果时间到了任务却还没结束，会产生并发问题，即多个线程执行
    - 使用方法：获取到RLock实例后，调用其tryLock()的无参方法，则redisson会自动启动一个watchdog后台线程，不断的重置锁key的过期时间
    - 自动延期原理：
        1. RedissonLock.tryAcquireOnceAsync方法里判断锁的超时时间leaseTime是否大于0，大于0则不开启watchdog线程，否则默认key的过期时间为30秒，且可以自定义设置
        2. 加锁成功则调用scheduleExpirationRenewal(long threadId)，这里会创建一个ExpirationEntry，并添加到ConcurrentMap<
           String,ExpirationEntry>里面，以供重复续约使用
        3. 此时调用renewExpiration()，方法里面会用到netty的内存版延迟任务HashedWheelTimer的Timeout newTimeout(TimerTask task, long delay,
           TimeUnit unit)方法创建一个延迟任务 此延迟任务的延迟时间为internalLockLeaseTime / 3 (internalLockLeaseTime默认为30秒，可自定义设置)
           ，即默认每10重置key的过期时间
        4. 当延迟任务执行后，如有异常会移除延迟key，正常则自调用重新创建一个延迟任务，等待下一次执行
4. 为什么要用lua脚本：保证命令的原子性(redis是单线程执行命令)
5. 可重入加锁机制 :Redis存储锁的数据类型是 Hash类型，Hash数据类型的key值包含了当前线程id

### Redis分布式锁的缺点

- 哨兵模式或者主从模式下，如果 master实例宕机的时候，可能导致多个客户端同时完成加锁
- 在Redis哨兵模式下:客户端1 对某个master节点写入了redisson锁，此时会异步复制给对应的 slave节点。但是这个过程中一旦发生 master节点宕机，主备切换，slave节点从变为了 master节点。 这时客户端2
  来尝试加锁的时候，在新的master节点上也能加锁，此时就会导致多个客户端对同一个分布式锁完成了加锁。这时系统在业务语义上一定会出现问题，导致各种脏数据的产生
- 解决办法：用RedLock红锁