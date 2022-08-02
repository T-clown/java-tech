[Class.forName 和 ClassLoader 有什么区别？](https://mp.weixin.qq.com/s?subscene=23&__biz=MjM5MTM0NjQ2MQ==&mid=2650142987&idx=3&sn=ed707a3dde8f49d5dde3e826d7e272fa&chksm=beb7a37989c02a6f74f7b2b41a6a4598d6204ed9128fef25765964e5daab9ea24f41a165f78d&scene=7&key=f853a52f8b711eb3409dbe6d23702e6347a19f4bbe08ca0d9b86b402bac7adecaeafb6f52b04fb315ed1dae4f3662cc950953d6ce8a68ba890c4bc6e8f267788125f959d19d9d0635c5eae79a8176f38&ascene=0&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=AfWY0C%2FCd8i0v0%2B2v2FN9ZY%3D&pass_ticket=tg2L%2BWgcb8a8umziteteT5XK6CInZ68fRwYRGwJzIkWY8LHjKieVaQJIqJITzYs3)

1. Class.forName() 和 ClassLoader 都可以对类进行加载。ClassLoader
   就是遵循双亲委派模型最终调用启动类加载器的类加载器，实现的功能是“通过一个类的全限定名来获取描述此类的二进制字节流”，获取到二进制流后放到 JVM 中
2. Class.forName() 方法实际上也是调用的 CLassLoader 来实现的
3. Class.forName()会执行静态代码块，初始化静态变量，而ClassLoader则不会
4. Spring 框架中的 IOC 的实现就是使用的 ClassLoader，使用 JDBC 时通常是使用 Class.forName() 方法来加载数据库连接驱动。这是因为在 JDBC 规范中明确要求 Driver(数据库驱动)类必须向
   DriverManager 注册自己。
