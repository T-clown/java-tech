
###获取Mapper接口的代理MapperProxy的流程
获取时机：项目启动时（因为通过@Autowired注入Mapper接口，因此启动项目时需要进行初始化并注入）
1.调用SqlSessionTemplate的 getMapper(Class<T> type) 方法(SqlSessionTemplate实现了SqlSession接口，线程安全)
2.获取Configuration对象并调用其getMapper(type, this)                                            方法，在项目启动的时候就会把Mapper接口加载并解析存储到Configuration对象
3.调用Configuration对象中的MapperRegistry对象属性的getMapper方法
4.根据type类型，从MapperRegistry对象中的knownMappers获取到当前类型对应的代理工厂类MapperProxyFactory（Map<Class<?>, MapperProxyFactory<?>> knownMappers） 
5.通过代理工厂类MapperProxyFactory生成Mapper对应的MapperProxy(mapperProxyFactory.newInstance(sqlSession))


###Mapper接口和映射文件关联的时机和流程
关联时机：Mapper接口和映射文件是何时关联的
1.调用SqlSessionFactoryBuilder方法中的build()方法 build(InputStream inputStream, String environment, Properties properties)
2.构造一个XMLConfigBuilder对象，并调用其parse()方法 new XMLConfigBuilder(inputStream, environment, properties)
3.XMLConfigBuilder对象会继续调用自己的parseConfiguration来解析配置文件，这里面就会分别去解析全局配置文件的顶级节点
4.继续调用自己的mapperElement来解析mappers文件，这里面分了四种方式来解析mappers节点的配置，对应了4种mapper配置方式
5.直接配置xml映射文件的解析方式(第2种和第3中)，会构建一个XMLMapperBuilder对象并调用其parse方法
6.解析完映射文件之后，调用自身方法bindMapperForNamespace，开始绑定Mapper接口和映射文件
7.调用Configuration对象的addMapper
8.调用Configuration对象的属性MapperRegistry内的addMapper方法，这个方法就是正式将Mapper接口添加到knownMappers


##sql执行流程   以select为例
1.调用Mapper接口进入到MapperProxy(代理类)的invoke方法中
2.自调用cachedMapperMethod(Method method) 方法，构造MapperMethod并将MapperMethod存在MapperProxy中的Map<Method, MapperMethod> methodCache里面
3.调用构造的MapperMethod的execute(SqlSession sqlSession, Object[] args)方法，execute方法里面对应五种执行类型：INSERT，UPDATE，DELETE，SELECT，FLUSH
4.接着调用SqlSession的相关查询接口进入SqlSessionTemplate(MapperMethod中的SqlSession就是SqlSessionTemplate,SqlSessionTemplate实现了SqlSession接口)
5.调用SqlSessionTemplate里sqlSessionProxy属性的方法后进入到SqlSessionInterceptor类的invoke方法里面(SqlSessionTemplate的内部类)，然后根据sqlSessionFactory，executorType和
    exceptionTranslator获取到sqlSession，接着进入到DefaultSqlSession（sqlSessionProxy就是DefaultSqlSession的代理实例）
6.查询最终都会进入到selectList(String statement, Object parameter, RowBounds rowBounds)方法里面（statement是接口方法的全限定名，如xx.xxMapper.list），然后调用Configuration
  的方法获取MappedStatement，获取MappedStatement时会解析未完成的语句
7.接着调用DefaultSqlSession里面Executor的query方法进入CachingExecutor（这里如果有插件拦截Executor的query方法的话会进入Plugin的invoke方法里面执行插件方法），接着获取BoundSql，创建缓存key
8.接着调用CachingExecutor的的属性对象Executor delegate的query方法进入BaseExecutor，此时通过CachingExecutor里构造的key去获取缓存localCache.getObject(key)，localCache为HashMap，若有缓存则不查数据库
9.若无缓存，则调用BaseExecutor的本地方法queryFromDatabase（此方法里面查到数据后会加入缓存localCache.putObject(key, list)），再调用自己的抽象方法doQuery进入SimpleExecutor(SimpleExecutor继承了BaseExecutor)
10.从MappedStatement里获取Configuration，通过Configuration的方法创建StatementHandler，同时也会创建ResultSetHandler和ParameterHandler(里面也会调用StatementHandler的插件拦截方法)，
    此时创建的 StatementHandler是RoutingStatementHandler，它只是起到一个路由的作用，真正执行操作的是它里面包含的对象属性StatementHandler delegate，分为三种：
    SimpleStatementHandler：直接操作sql，不进行预编译
    PreparedStatementHandler：预处理参数，进行预编译
    CallableStatementHandler：执行存储过程  
11.接着执行prepareStatement(handler, ms.getStatementLog())方法构造Statement，里面会做如下操作：
    (1)获取mysql的链接Connection
    (2)调用handler.prepare(connection, transaction.getTimeout())构造Statement(里面其实也是调用connection.prepareStatement(sql)进行构造)
    (3)调用handler.parameterize(stmt)，为Statement设置执行sql需要的参数（里面调用TypeHandler进行设置参数）
12.调用StatementHandler的query(stmt, resultHandler)方法(RoutingStatementHandler)，在RoutingStatementHandler里面又调用PreparedStatementHandler的查询方法，接着执行PreparedStatement的execute方法，
  调用ResultSetHandler处理结果集，返回
