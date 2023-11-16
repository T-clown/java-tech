[头条高级面试题：请谈谈Redis 9种数据结构以及它们的内部编码实现](https://mp.weixin.qq.com/s?__biz=MzA4NjgxMjQ5Mg==&mid=2665763950&idx=2&sn=d73e689d65ba2d755d8f5e5c8406f291&chksm=84d2064db3a58f5b05184b0dab859514ef771b810748b2b6064a3b34681ce89430f645efeccc&mpshare=1&scene=24&srcid=&sharer_sharetime=1589162326685&sharer_shareid=c39308937f2815a44c41054898432d19&key=8789ad4c0cbeb143b8fed987903ae0b3fd51df3c241bd6b35bad80b3fad446000fb06b7ce3472c8826752cac0a190aa010d44cb7299aca8cb84d59e67e1ac14d8319172d7ebb95a77bc0704d7c926e32&ascene=14&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=ARSwXhe60kMe6Dg2yy8MCVQ%3D&pass_ticket=%2BUPT7tryhpGcvLtueQqS9DncJWiw%2B7vMueLeyKKbuDBllUr4jQt4RjLHDlveutCz)
[Redis底层数据结构的映射关系](https://mp.weixin.qq.com/s?__biz=MzA4MTk3MjI0Mw==&mid=2247487882&idx=1&sn=24c8b5bf9dc3988b5d234d642caaef17&chksm=9f8d8cf6a8fa05e0970db20c62d4a447510f13dc9af18af59520f3b43d36657bff9a682ed781&mpshare=1&scene=24&srcid=&sharer_sharetime=1589163467891&sharer_shareid=c39308937f2815a44c41054898432d19&key=8789ad4c0cbeb143070bda5c990217005a6443780d06e4193f48ffd4dcffd24bad66544ff0bb3a2e9b322e7d702c3aac7deb513be04617765b2d5a083af0542c5e5014c7515cc09d0c5c926654fedb35&ascene=14&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=AVIexZuPgt3WwEGi3sbjnxc%3D&pass_ticket=%2BUPT7tryhpGcvLtueQqS9DncJWiw%2B7vMueLeyKKbuDBllUr4jQt4RjLHDlveutCz)

### 1.String(三种内部编码)

- 数据结构：支持自动动态扩容的字节数组
- 编码类型
    1. 当value是整数的时候，且长度小于20的时候为int编码(长度<20，否则为embstr编码)
    2. 当字符串长度小于44时，为embstr编码，否则为raw编码
    3. embstr和raw两种编码比较：
        - embstr编码的字符串对象的所有数据都保存在一块连续的内存里面，所以这种编码的字符串对象比起raw编码的字符串对象能更好地利用缓存带来的优势
        - 释放embstr编码的字符串对象只需要调用一次内存释放函数，而释放raw编码对象的字符串对象需要调用两次内存释放函数
- 使用场景：
    1. 缓存
    2. 计数
    3. 共享session
    4. 限流
    5. 分布式锁

### 2.list

- 数据结构：双向链表(对应java的LinkedList)
- 编码类型：quicklist(以ziplist作为结点的LinkedList(双向链表))
- 使用场景:
    1. 栈
    2. 队列
    3. 有限集合
    4. 消息队列

### 3.hash

- 数据结构：类似java中的hashmap
- 编码类型：
    - ziplist（压缩列表）：元素个数小于hash-max-ziplist-entries（默认512个）且同时所有值都小于hash-max-ziplist-value（默认64字节）
    - hashtable（哈希表）：无法满足ziplist的条件时
- 使用场景：存储结构化数据

### 4.set

- 数据结构：类似java中的Set
- 编码类型：
    - intset（有序的无重复整型数组）：集合中必须是64位有符号的十进制整型，且集合中的元素都是整数且元素个数小于set-max-intset-entries（默认512个）
    - hashtable（哈希表）：无法满足intset的条件时
- 使用场景：
    1. 去重
    2. 标签

### 5.zset

- 数据结构：跳表
- 编码类型：
    - ziplist（压缩列表）（新版本改成了listpack(紧凑列表)）：当元素个数小于zset-max-ziplist-entries（默认128个），同时每个元素的值都小于zset-max-ziplist-value（默认64字节）时
    - skiplist（跳跃表）：ziplist条件不满足时
- 使用场景： 排行榜，点赞数

### 6.bitmap

- 数据结构：本质上是String数据结构，只不过操作的粒度变成了位，即bit。因为String类型最大长度为512MB，所以bitmap最多可以存储2^32个bit
- 使用场景：判断某个元素存不存在，布隆过滤器BloomFilter，独立的用户统计

### 7.GeoHash

- 数据结构：Geo本身不是一种数据结构，它本质上还是借助于Sorted Set（ZSET），并且使用GeoHash技术进行填充
- 使用场景：搜索附近的人

### 8.HyperLogLog

- 数据结构：本质上是字符串
- 编码：raw
- 使用场景：提供不精确的去重统计方案，比如统计uv等，pfadd，pfcount

### 9.Streams(5.0版本增加的数据结构)

- Streams就是Redis实现的内存版kafka
- 数据结构：Radix Tree(基数树)，事实上就几乎相同于传统的二叉树
