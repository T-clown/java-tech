[讲解 Zookeeper 的五个核心知识点](https://mp.weixin.qq.com/s/iSU3BO-_57v1V5THF4sNdQ)
[便捷搭建 Zookeeper 服务器的方法](https://mp.weixin.qq.com/s/hBfFe2KLjzYfQI_7_K8kJA)
[分布式锁用Redis好，还是Zookeeper好？](https://mp.weixin.qq.com/s/CXKwk0e0ZpiKk6WF7Qq00w)
[一文彻底搞懂 zookeeper 核心知识点](https://mp.weixin.qq.com/s/faUx6QUV1VqTD0jyPevZQw)
[用大白话给你解释 Zookeeper 的选举机制](https://mp.weixin.qq.com/s/ukY9aW3H0IvWE5bjJ9ROZg)
[注册中心 ZooKeeper、Eureka、Consul 、Nacos 对比！](https://mp.weixin.qq.com/s/kujyp7XIFhduv-qAS4aIDw)
[话说关于 ZooKeeper 方面，面试有什么可问的啊？​](https://mp.weixin.qq.com/s/PZPSQcxJEeBFYgN2lJoSRQ)
[分布式锁用 Redis 还是 Zookeeper？](https://mp.weixin.qq.com/s/xfRd282_-RH8fMoYhhkQOg)
[]()
###节点类型
    持久化目录节点 PERSISTENT：客户端与zookeeper断开连接后，该节点依旧存在。
    持久化顺序编号目录节点 PERSISTENT_SEQUENTIAL：客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号。
    临时目录节点 EPHEMERAL：客户端与zookeeper断开连接后，该节点被删除。
    临时顺序编号目录节点 EPHEMERAL_SEQUENTIAL：客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号。
###节点四种状态
    LOOKING：寻 找 Leader 状态。当服务器处于该状态时会认为当前集群中没有 Leader，因此需要进入 Leader 选举状态。
    FOLLOWING：跟随者状态。处理客户端的非事务请求，转发事务请求给 Leader 服务器，参与事务请求 Proposal(提议) 的投票，参与 Leader 选举投票。
    LEADING：领导者状态。事务请求的唯一调度和处理者，保证集群事务处理的顺序性，集群内部个服务器的调度者(管理follower,数据同步)。
    OBSERVING：观察者状态。3.0 版本以后引入的一个服务器角色，在不影响集群事务处理能力的基础上提升集群的非事务处理能力，处理客户端的非事务请求，转发事务请求给 Leader 服务器，不参与任何形式的投票。
###Leader选举
    Leader的选举一般分为启动时选举跟Leader挂掉后的运行时选举
Zookeeper的使用场景
1.分布式协调

2.分布式锁

3.元数据/配置信息管理

4.HA高可用性