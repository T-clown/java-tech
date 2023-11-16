[SpringBoot自动装配原理分析](https://mp.weixin.qq.com/s/4WSe2mzN0yFiyAuD5HDWcA)

### 实现自动装配

1. 创建SpringBoot项目，名称为test-spring-boot-starter（命名一般叫：{name}-spring-boot-starter）
2. 新建自动装配类，如：TestAutoConfiguration，并加上注解@Configuration
3. 在 resources 目录下新建一个文件 META-INF/spring.factories 文件，文件内新增一个如下配置
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
   com.springboot.autoconfigure.TestAutoConfiguration
4. test-spring-boot-starter项目打包成jar，并在另一个项目中引入依赖
5. 这时在新项目中即可引用test-spring-boot-starter中的Bean
6. 坑点 ：引入jar包后还需要配置装配类的包扫描路径，否则则无法加载

### 自动装配原理

1. SpringBoot有一个注解是@SpringBootApplication，它是一个复合注解，包含了@Configuration、@EnableAutoConfiguration和@ComponentScan。@EnableAutoConfiguration注解启用了Spring
   Boot的自动装配机制
2. 通过扫描项目中的类，得到一个需要加载的类Class集合
3. 根据条件化配置决定要自动配置哪些组件，如@Conditional，excludes等的过滤
4. 然后加载自动配置类并创建相应的Bean，最后将这些Bean注入到应用程序中
