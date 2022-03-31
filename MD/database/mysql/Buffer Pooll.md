    show variables like "%buffer_pool%";
    innodb_buffer_pool_size：缓冲池的大小，默认128M
    innodb_buffer_pool_instances：缓冲池实例数


##MySQL的生产优化经验多个Buffer Pool优化并发能力
    多线程并发访问一个Buffer Pool， 必然是要加锁的， 然后让一个线程先完成一系列的操作， 比如说加载数据页到 缓存页， 更新free链表， 更新lru链表， 然后释放锁， 接着下一个线程再执行一系列的操作。
    一般来说， MySQL默认的规则是， 如果你给Buffer Pool分配的内存小于1GB， 那么最多就只会给你一个Buffer Pool。
    在实际生产环境中， 设置多个buffer pool来优化高并发访问性能， 是mysql一个很重要的优化技巧。