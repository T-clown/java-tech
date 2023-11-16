[Kafka 会不会丢消息？怎么处理的?](https://mp.weixin.qq.com/s/VOM7b7SoUm9Yp3EgV5OgtQ)
[kafka 的重试机制实践](https://mp.weixin.qq.com/s/1csuekMj5e0eqbaj22VHvg)
[兄弟！kafka的重试机制，你可能用错了](https://mp.weixin.qq.com/s/R6-_N9zhbqbXVuZaJg0OlQ)
[Kafka 消息丢失与消费精确一次性](https://mp.weixin.qq.com/s/6-bgLMg5ISfB6i2pcPKGBg)

- Kafka存在丢消息的问题，消息丢失会发生在Broker，Producer和Consumer三种
- kafka并不能百分百保证不丢消息，只能通过一些手段来保证尽可能的不丢消息

### Producer（发送端）

1. kafka默认是异步发送，异步发送消息改为同步发送消。或者service产生消息时，使用阻塞的线程池，并且线程数有一定上限。整体思路是控制消息产生速度
2. service不直接将消息发送到buffer（内存），而是将消息写到本地的磁盘中（数据库或者文件），由另一个（或少量）生产线程进行消息发送。相当于是在buffer和service之间又加了一层空间更加富裕的缓冲层

### Broker（服务端）

1. acks=0，producer不等待broker的响应，效率最高，但是消息很可能会丢。

2. acks=1，leader broker收到消息后，不等待其他follower的响应，即返回ack。也可以理解为ack数为1。此时，如果follower还没有收到leader同步的消息leader就挂了，那么消息会丢失。按照上图中的例子，如果leader收到消息，成功写入PageCache后，会返回ack，此时producer认为消息发送成功。但此时，按照上图，数据还没有被同步到follower。如果此时leader断电，数据会丢失。

3. acks=-1，leader broker收到消息后，挂起，等待所有ISR列表中的follower返回结果后，再返回ack。-1等效与all。这种配置下，只有leader写入数据到pagecache是不会返回ack的，还需要所有的ISR返回“成功”才会触发ack。如果此时断电，producer可以知道消息没有被发送成功，将会重新发送。如果在follower收到数据以后，成功返回ack，leader断电，数据将存在于原来的follower中。在重新选举以后，新的leader会持有该部分数据

### Consumer（消费端）

1. kafka的ACK机制默认是自动提交，并且提交过程是异步的，可以改为手动提交（会产生重复消费）







