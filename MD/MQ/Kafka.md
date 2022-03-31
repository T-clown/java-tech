Kafka是消息引擎系统，也是一个分布式流处理平台
一个kafka实例其实就是一个borker服务器进程，多个broker构成kafka集群
topic：主题，即一种消息类型
partition：分区，一个topic的消息被分成多个partition保存在不同的broker上，每个partition都有一个序号，从0开始，
           partition中的消息都有一个序号，即分区位移，每个partition有n个副本(replica)存在不同的broker上，即一个leader
           分区和n-1个follower分区，leader分区负责提供对外读写功能，follower则只负责数据同步
ConsumerGroup：消费者组，由多个consumer(消费者实例Consumer Instance)组成，一个consumer消费一个topic的一个partition，实现了高吞吐率
               如果有一个consumer挂了，其负责的partition会被分配给活着的consumer(Rebalance重平衡)，实现高可用
               consumer在partition中都有一个消费者位移(Consumer Offset)，表明消费者消费消息的进度标识         
kafka使用消息日志(Log)保存数据，消息通过追加写入保持高吞吐率，且会定期删除消息回收磁盘