[](https://mp.weixin.qq.com/s/PCcEXPfLxKGqmAobrHfggQ)
Spring中的事务是通过代理实现的，代理分为JDK动态代理和CGLIB，两者的区别
1.JDK动态代理是默认的，而且代理对象必须实现接口
2.CGLIB代理则带不带接口都行，其通过子类继承方式实现

面试问题
一个接口有两个方法a和b，其中a方法上有事务注解，b方法没有，在方法b里调用方法a，再调用方法b，会有事务吗
调用的方法不带注解，因此代理类不开事务，而是直接调用目标对象的方法。当进入目标对象的方法后，执行的上下文已经变成目标对象本身了，
因为目标对象的代码是我们自己写的，和事务没有半毛钱关系，此时你再调用带注解的方法，照样没有事务，只是一个普通的方法调用而已

只要是以代理方式实现的声明式事务，无论是JDK动态代理，还是CGLIB直接写字节码生成代理，都只有public方法上的事务注解才起作用。
而且必须在代理类外部调用才行，如果直接在目标类里面调用，事务照样不起作用。


[事务失效原因](https://mp.weixin.qq.com/s/6EpeHAF5UmFzEuaQPWjdTw)
1.数据库引擎不支持事务，MyISAM引擎不支持事务，InnoDB支持事务
2.当前类没有被 Spring 管理
3.事务方法不是 public 的，如果要用在非 public 方法上，可以开启 AspectJ 代理模式
4.自身调用问题(内部调用)
5.数据源没有配置事务管理器
6.不支持事务(事务传播行为是Propagation.NOT_SUPPORTED)
7.事务方法内异常被吃了，无法回滚
8.异常类型错误(事务方法抛出的异常和@Transactional(rollbackFor = Exception.class)上的异常不对应)


###@Transactional注解事务自调用不生效解决办法
    1.直接用@Autowired注入自身，this调用改为注入的自身调用
    2.用AopContext.currentProxy()获取自身代理对象进行调用，当前类或者启动类上需加上@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
    3.通过ApplicationContext获取自身代理对象，applicationContext.getBean(this.getClass())