## Dubbo大体分为三层

### 业务层（Biz）

- Service：业务代码的接口与实现，即开发者实现的业务代码

### RPC层

- Config(配置层)
    - ServiceConfig（暴露的服务配置）
    - ReferenceConfig（引用的服务配置）
- Proxy(代理层)
    - ProxyFactory
- Registry(注册层)
    - 阿斯顿发
- Cluster(集群容错层)：负责远程调用失败的容错策略和负载均衡
    - Cluster（里面会进行负载均衡选取Invoker）
    - Directory
    - Router
- Monitor(监控层)：统计调用次数和调用时间等
- Protocol(远程调用层)

### Remote远程调用层

- Exchange(信息交换层)：建立Request-Response模型，封装请求响应模式
- Transport(网络传输层)：把网络传输抽象为统一接口
- Serialize(序列化层)
   