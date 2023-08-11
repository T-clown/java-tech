[w3cschool](https://www.w3cschool.cn/redis_all_about/redis_all_about-2zsb26w0.html)

### 全局命令

- key * ：查看所有key（会遍历所有key，时间复杂度O(n)，生产环境禁用）
- dbsize：查看键总数，时间复杂度O(1)
- exists key：检查键是否存在
- del key：删除键
- expire key seconds：设置过期时间
- ttl key：查看键的过期时间
- type key：查看键的数据类型
- object encoding key：查看key的编码类型(debug object key也可以)
- scan命令可以解决keys命令可能带来的阻塞问题，同时Redis还提供了hscan、sscan、zscan渐进式地遍历hash、set、zset

### 1.字符串String

- setnx key value #键不存在时可以设置成功
- incr key # 递增数字，仅仅对数字类型的键有用,相当于Java的i++运算
- incrby key increment # key自增increment，increment可以为负数，表示减少。
- decr key # 递减数字，仅仅对数字类型的键有用，相当于Java的i–-
- decrby key decrement # key自减decrement，decrement可以为正数，表示增加。
- incrbyfloat key increment # 增加指定浮点数，仅仅对数字类型的键有用
- append key value # 向尾部追加值,相当于append方法
- strlen key # 获取字符串长度
- mset key1 value1 [key2 value2 ...]    # 同时设置多个key的值
- mget key1 [key2 ...]                  # 同时获取多个key的值

### 2.列表list

- rpush key value # 在名称为key的list尾添加一个值为value的元素
- lpush key value # 在名称为key的list头添加一个值为value的 元素
- llen key # 返回名称为key的list的长度
- lrange key start end # 返回名称为key的list中start至end之间的元素
- ltrim key start end # 截取名称为key的list
- lindex key index # 返回名称为key的list中index位置的元素
- lset key index value # 给名称为key的list中index位置的元素赋值
- lrem key count value # 删除count个key的list中值为value的元素
- lpop key # 返回并删除名称为key的list中的首元素
- rpop key # 返回并删除名称为key的list中的尾元素
- rpoplpush srckey dstkey # 返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
- blpop key[key ...] timeout #同lpop，会阻塞
- brpop key[key ...] timeout #同rpop，会阻塞

### 3.集合set

- sadd key member # 向名称为key的set中添加元素
- srem key element #删除元素
- membersrem key member # 删除名称为key的set中的元素
- memberspop key # 随机返回并删除名称为key的set中一个元素
- smove srckey dstkey member # 移到集合元素
- scard key # 返回名称为key的set的基数
- sismember key member # member是否是名称为key的set的元素
- sinter key1 key2 …key # 求交集
- sinterstore dstkey keys # 求交集并将交集保存到dstkey的集合
- sunion key1 keys # 求并集
- sunionstore dstkey keys # 求并集并将并集保存到dstkey的集合
- sdiff key1 keys # 求差集
- sdiffstore dstkey keys # 求差集并将差集保存到dstkey的集合
- smembers key # 返回名称为key的set的所有元素
- srandmember key # 随机返回名称为key的set的一个元素

### 4.有序集合zset

- ZADD key score1 value1 [score2 value2 score3 value3 ...]    # 添加元素
- ZSCORE key value # 获取元素的分数
- ZRANGE key start stop [WITHSCORE]    # 获取排名在某个范围的元素，按照元素从小到大的顺序排序，从0开始编号，包含start和stop对应的元素，WITHSCORE选项表示是否返回元素分数
- ZREVRANGE key start stop [WITHSCORE]    # 获取排名在某个范围的元素，和上一个命令用法一样，只是这个倒序排序的。
- ZRANGEBYSCORE key min max # 获取指定分数范围内的元素，包含min和max，(min表示不包含min，(max表示不包含max，+inf表示无穷大
- ZINCRBY key increment value # 增加某个元素的分数
- ZCARD key # 获取集合中元素的个数
- ZCOUNT key min max # 获取指定分数范围内的元素个数，min和max的用法和5中的一样
- ZREM key value1 [value2 ...]    # 删除一个或多个元素
- ZREMRANGEBYRANK key start stop # 按照排名范围删除元素
- ZREMRANGEBYSCORE key min max # 按照分数范围删除元素，min和max的用法和4中的一样
- ZRANK key value # 获取正序排序的元素的排名
- ZREVRANK key value # 获取逆序排序的元素的排名

### 5.哈希hash

- hset key field value # 赋值
- hmset key field1 value1 [field2 values]    # 一次赋值多个字段
- hget key field # 取值
- hmget key field1 [field2] # 一次取多个字段的值
- hgetall key # 一次取所有字段的值(元素多会阻塞redis)
- hscan key #获取所有字段值，渐进式遍历
- hexits key field # 判断字段是否存在
- hsetnx key field value # 当字段不存在时赋值
- hdel key field # 删除字段
- hkeys key # 获取所有字段名
- hvals key # 获取所有字段值
- hlen key # 获取字段数量
- hincrby key field（同incrby）
- hincrbyfloat key field（同incrbyfloat）