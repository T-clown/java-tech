### Java的SPI机制

- 实现步骤：
    1. META-INF/services目录下，创建一个以接口全路径命名的文件
    2. 文件内容为接口的具体实现类的全路径名，多个以换行隔开
    3. 代码中使用java.util.ServiceLoader来加载具体实现类
- 数据库驱动使用了java的SPI
- 缺点：会一次性加载所有实现，浪费资源

### Dubbo的SPI机制

- 实现步骤
    1. META-INF/services，META-INF/dubbo，META-INF/dubbo/internal目录下，创建一个以接口全路径命名的文件
    2. 文件内容为key=value形式，value为接口的具体实现类的全路径名，多个以换行隔开
- 注解说明
    1. @SPI：标注接口或者类为一个扩展点，注解的value可指定默认的扩展点实现
    2. @Adaptive：自适应注解，可用在类和接口方法上
        - 用在实现类上：当该注解放在实现类上，则整个实现类会直接作为默认实现， 不再自动生成代码。在扩展点接又的多个实现里，只能有 一个实现上可以加@Adaptive 注解。如果多个 实现类都有该注解，则会抛出异常
        - 用在接口方法上：方法级别注解在第一次getExtension时，会自动生成和编译一个动态的Adaptive类，从而达到动态实现类的效果（动态实现类名如：LoadBalance$Adaptive）
        - 该注解可以传入value参数，是一个数组。Adaptive可以传入多个key值，在初始化Adaptive 注解的接口时，会先对传入的URL 进行key 值匹配，第 一个key
          没匹配上则匹配第二个，以此类推。直到所有的key 匹配完毕 如果还没有匹配到， 则使用@SPI 注解中填写的默认值再去匹配。如果@SPI 注解也没有填写默认值，则会抛出 I1legalStateException 异常
    3. @Activate：自动激活注解
        - @Activate 可以标记在类、接口、枚举类和方法上。主要使用在有多个扩展点实现、需要根据不同条件被激活的场景中，如Filter 需要多个同时激活，因为每个Filter 实现的是不同的功能