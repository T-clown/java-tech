### bigkey优化

- bigkey的危害
    1. 内存空间不均匀（平衡） ：例如在Redis Cluster中，bigkey会造成节点的内存空间使用不均匀。
    2. 超时阻塞 ：由于Redis单线程的特性，操作bigkey比较耗时，也就意味着阻塞Redis可能性增大。
    3. 网络拥塞 ：每次获取bigkey产生的网络流量较大，占用带宽
- 发现big key
    1. 被动搜集 :修改Redis客户端，当抛出异常时打印出所操作的key，方便排查big key问题
    2. 主动搜集：scan+debug object：如果怀疑存在big key，可以使用scan命令渐进的扫描出所有的key，分别计算每个key的serializedlength，找到对应bigkey进行相应的处理和报警
       serializedlength不代表真实的字节大小，它返回对象使用RDB编码序列化后的长度，值会偏小
    3. 如果键值个数比较多，scan+debug object会比较慢，可能会阻塞Redis，可以利用Pipeline机制完成
- 删除bigkey
    1. 如果是String类型，可直接用del命令进行删除
    2. 如果是hash、list、set、sorted set类型，可用scan命令进行渐进式遍历删除
    3. 惰性删除：给key设置过期时间，让Redis自己删除
    4. 用unlink命令，此命令是把key加入到一个异步删除队列中，无需等待，而del则是同步删除，会阻塞主线程

### 慢查询日志

- 服务器配置有两个和慢查询日志相关的选项：
    1. slowlog-log-slower-than 选项指定执行时间超过多少微秒（1 秒等于 1,000,000 微秒）的命令请求会被记录到日志上。
    2. slowlog-max-len 选项指定服务器最多保存多少条慢查询日志。服务器使用先进先出的方式保存多条慢查询日志： 当服务器储存的慢查询日志数量等于 slowlog-max-len 选项的值时，
       服务器在添加一条新的慢查询日志之前， 会先将最旧的一条慢查询日志删除。
    3. 修改参数： CONFIG SET slowlog-log-slower-than 0 ， CONFIG SET slowlog-max-len 5

- 慢查询记录的保存
   ```
    struct redisServer {
    
        // ...
    
        // 下一条慢查询日志的 ID
        long long slowlog_entry_id;
    
        // 保存了所有慢查询日志的链表
        list *slowlog;
    
        // 服务器配置 slowlog-log-slower-than 选项的值
        long long slowlog_log_slower_than;
    
        // 服务器配置 slowlog-max-len 选项的值
        unsigned long slowlog_max_len;
    
        // ...
    
    };
   ```
    - slowlog_entry_id 属性的初始值为 0 ， 每当创建一条新的慢查询日志时， 这个属性的值就会用作新日志的 id 值， 之后程序会对这个属性的值增一。
    - slowlog 链表保存了服务器中的所有慢查询日志， 链表中的每个节点都保存了一个 slowlogEntry 结构， 每个 slowlogEntry 结构代表一条慢查询日志
    - 新的慢查询日志会被添加到 slowlog 链表的表头， 如果日志的数量超过 slowlog-max-len 选项的值， 那么多出来的日志会被删除
   ```
   typedef struct slowlogEntry {

    // 唯一标识符
    long long id;

    // 命令执行时的时间，格式为 UNIX 时间戳
    time_t time;

    // 执行命令消耗的时间，以微秒为单位
    long long duration;

    // 命令与命令参数(数组)
    robj **argv;

    // 命令与命令参数的数量
    int argc;

   } slowlogEntry;

   ```

- 查询慢查询日志：SLOWLOG GET