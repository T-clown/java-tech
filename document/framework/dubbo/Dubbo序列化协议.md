[关于Dubbo序列化协议，面试官喜欢问那些问题？](https://mp.weixin.qq.com/s/kID3Rmv1EYHsEwH-4XwGLw)

### dubbo支持的通信协议，在dubbo-rpc模块下面，目前有13种

1. dubbo 协议(默认)： 默认就是走 dubbo 协议，单一长连接，进行的是 NIO 异步通信，基于 hessian 作为序列化协议。使用的场景是：传输数据量小（每次请求在 100kb 以内），但是并发量很高
2. rmi 协议： 走 Java 二进制序列化，多个短连接，适合消费者和提供者数量差不多的情况，适用于文件的传输，一般较少用
3. hessian 协议： 走 hessian 序列化协议，多个短连接，适用于提供者数量比消费者数量还多的情况，适用于文件的传输，一般较少用
4. http 协议： 走 json 序列化
5. webservice： 走 SOAP 文本
6. redis： 基于 redis 实现的 RPC 协议
7. memcached ： 基于 memcached 实现的 RPC 协议

### dubbo支持的序列化协议，在dubbo-serialization模块下面，目前有10种

1. hessian，默认
2. fastjson
3. gson
4. jdk
5. kryo
6. protobuff
7. protostuff
8. fst
9. native-hessian
10. avro

### 说一下 Hessian 的数据结构？

    Hessian 的对象序列化机制有 8 种原始类型：
    原始二进制数据
        boolean
        64-bit date（64 位毫秒值的日期）
        64-bit double
        32-bit int
        64-bit long
        null
        UTF-8 编码的 string
    另外还包括 3 种递归类型：
        list for lists and arrays
        map for maps and dictionaries
        object for objects
    还有一种特殊的类型：
        ref：用来表示对共享对象的引用

### 为什么 PB 的效率是最高的？

    Protocol Buffer 其实是 Google 出品的一种轻量并且高效的结构化数据存储格式，性能比 JSON、XML 要高很多

- 第一，它使用 proto 编译器，自动进行序列化和反序列化，速度非常快，应该比 XML 和 JSON 快上了 20~100 倍；
- 第二，它的数据压缩效果好，就是说它序列化后的数据量体积小。因为体积小，传输起来带宽和速度上会有优化。  