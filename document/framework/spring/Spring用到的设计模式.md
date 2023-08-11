DI(Dependecy Inject,依赖注入)是实现控制反转的一种设计模式，依赖注入就是将实例变量传入到一个对象中去

### Spring中的设计模式

1. 工厂模式：
    - Spring中有两种容器，BeanFactory和ApplicationContext，都是用来创建bean对象
    - 两者区别： - BeanFactory ：延迟注入(使用到某个 bean 的时候才会注入),相比于ApplicationContext来说会占用更少的内存，程序启动速度更快。 - ApplicationContext
      ：容器启动的时候，一次性创建所有bean 。BeanFactory 仅提供了最基本的依赖注入支持，ApplicationContext 扩展了 BeanFactory , 除了有BeanFactory的功能还有额外更多功能
2. 单例模式
    - Spring中bean的默认作用域就是singleton(单例)
    - 场景：Spring 通过 ConcurrentHashMap 实现单例注册表的特殊方式实现单例模式

3. 代理模式
    - 场景：事务处理、日志管理、权限控制等（减少系统的重复代码，降低模块间的耦合度）
    - Spring AOP 就是基于动态代理的，如果要代理的对象，实现了某个接口，那么Spring AOP会使用JDK Proxy，去创建代理对象， 而对于没有实现接口的对象，就无法使用 JDK Proxy
      去进行代理了，这时候Spring AOP会使用Cglib ，这时候Spring AOP会使用 Cglib 生成一个被代理对象的子类来作为代理
    - Spring AOP 属于运行时增强（动态代理），而 AspectJ 是编译时增强（静态代理）

4. 模板方法
    - 模板方法模式是一种行为设计模式，它定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。 模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤的实现方式
    - 场景：Spring 中 jdbcTemplate、TransactionTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式

5. 观察者模式
    - 观察者模式是一种对象行为型模式。它表示的是一种对象与对象之间具有依赖关系，当一个对象发生改变的时候，这个对象所依赖的对象也会做出反应
    - 场景：Spring 事件驱动模型

6. 适配器模式
    - 适配器模式(Adapter Pattern) 将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作，其别名为包装器(Wrapper)
    - 场景：Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller

7. 装饰者模式
    - 装饰者模式可以动态地给对象添加一些额外的属性或行为。相比于使用继承，装饰者模式更加灵活。简单点儿说就是当我们需要修改原有的功能，但我们又不愿直接去修改原有的代码时，设计一个Decorator套在原有代码外面
   
8. 策略模式