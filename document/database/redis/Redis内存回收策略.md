[](https://mp.weixin.qq.com/s/7UipC5kC18eeSDBWEdYYIg)

### 内存回收策略

1. 删除到达过期时间的键对象
    1. 惰性删除用于当客户端读取带有超时属性的键时，如果已经超过键设置的过期时间，会执行删除操作并返回空，这种策略是出于节省CPU成本考虑，不需要单独维护TTL链表来处理过期键的删除
    2. Redis内部维护一个定时任务，默认每秒运行10次（通过配置hz控制）。定时任务中删除过期键逻辑采用了自适应算法，根“据键的过期比例、使用快慢两种速率模式回收键


2. 内存使用达到maxmemory上限时触发内存溢出控制策略
    1. noeviction：默认策略，不会删除任何数据，拒绝所有写入操作并返回客户端错误信息（error）OOM command not allowed when used memory，此时Redis只响应读操作
    2. allkeys-lru：根据LRU算法删除键，不管数据有没有设置超时属性，直到腾出足够空间为止
    3. volatile-lru：根据LRU算法删除设置了超时属性（expire）的键
    4. allkeys-random：随机删除所有键
    5. volatile-random：随机删除有过期时间的键
    6. volatile-ttl：根据键值对象的ttl属性，删除最近将要过期数据。如果没有，回退到noeviction策略
    7. volatile-lfu：根据 LFU 算法从有过期时间的键删除
    8. allkeys-lfu：根据 LFU 算法从所有键删除

### 内存优化

- 可以使用scan+object idletime命令批量查询哪些键长时间未被访问，找出长时间不访问的键进行清理，可降低内存占用
