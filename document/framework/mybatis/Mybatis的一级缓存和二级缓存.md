
[](https://juejin.cn/post/7206261082453671994?searchId=202307132139084B0F96B37C8D47A3D1D4)
### 一级缓存

- 一级缓存默认开启，即默认是SESSION级别的缓存（LocalCacheScope枚举有两种类型，SESSION和STATEMENT），即不同的会话有各自的一级缓存，互不影响
- 在同一个会话中，对数据库的增，删，改操作，均会使一级缓存失效，因为内部会调用clearLocalCache()清楚一级缓存
- 在BaseExecutor实现了一级缓存，内部维护了一个PerpetualCache变量，而PerpetualCache其实是对HashMap的包装

### 二级缓存

- 二级缓存默认关闭，开启通过useCache开关控制，STATEMENT级别的缓存
- 在CachingExecutor实现了二级缓存，内部维护了一个TransactionalCacheManager实例，而这个实例里面又维护了一个 Map<Cache, TransactionalCache>
  其中Cache为缓存key， TransactionalCache则是对Map<Object, Object>的一个包装，里面存储了真正的值
- mybatis会先从二级缓存拿数据，拿不到再从一级缓存拿数据，再没有则会查询数据库
- 二级缓存的更新需要开启事务，如不开启事务则数据不会更新到二级缓存中
- 增删改的操作也是需要开启事务才会清除二级缓存