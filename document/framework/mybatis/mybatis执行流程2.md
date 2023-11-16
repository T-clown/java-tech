mybatis执行流程

1.MapperProxy：构造并缓存MapperMethod，执行MapperMethod的execute方法

2.MapperMethod：执行SqlSessionTemplate的方法

3.SqlSessionTemplate：执行SqlSessionTemplate里sqlSessionProxy的方法（sqlSessionProxy是DefaultSqlSession的动态代理实例）

4.SqlSessionTemplate.SqlSessionInterceptor：创建DefaultSqlSession实例，执行DefaultSqlSession代理的方法

​	(1)打开数据库连接

​	(2)调用Configuration的方法创建Executor(这里new CachingExecutor(executor)，executor是真正执行的executor)

​	(3)如果实现了插件拦截Executor，则interceptorChain.pluginAll(executor)一层一层创建代理包装并返回

​	(f4)创建DefaultSqlSession实例，里面包含了创建的executor

5.DefaultSqlSession：根据mapper接口方法的全限定名调用Configuration获取MappedStatement，执行CachingExecutor的方法（如果有插件则会执行插件方法，在插件方法里执行真正的executor的方法）

6.CachingExecutor(二级缓存)：如果有缓存且缓存数据不为空则直接返回数据，有缓存且缓存数据为空则查询数据后把数据添加到缓存，若无缓存则调用内部Executor的方法

7.BaseExecutor(一级缓存)：执行抽象方法，进入SimpleExecutor，这里会把结果加入到一级缓存中

8.SimpleExecutor：

​	(1)通过Configuration对象构造RoutingStatementHandler(RoutingStatementHandler里包含了真正执行的StatementHandler，这里也用到装饰器模式）同时创建了ParameterHandler和ResultSetHandler，创建这三个handler的时候也判断有无插件拦截，有的话同样通过interceptorChain.pluginAll对其用代理进行包装

​	(2)获取数据库连接，并调用StatementHandler(RoutingStatementHandler)的方法初始化Statement(里面实际调用的是connection.prepareStatement(sql))，再调用ParameterHandler对Statement进行参数设置，设置参数的时候会把类型映射成对应的jdbc类型，然后调用handler方法进入下一步

9.PreparedStatementHandler：调用Statement(PreparedStatement)的execute进行查询，再通过ResultSetHandler对结果进行处理并返回

