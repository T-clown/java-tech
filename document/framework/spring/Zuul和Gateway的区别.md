### Zuul

- 是Netflix的，早期在微服务中使用较广泛，是基于servlet实现的，阻塞式的api，不支持长连接。 只能同步，不支持异步。
- 内部没有实现限流、负载均衡，其负载均衡的实现是采用 Ribbon + Eureka 来实现本地负载均衡

### SpringCloud Gateway

- SpringCloud自己研制的微服务网关，是基于Spring5构建，能够实现响应式非阻塞式的Api，支持长连接。 支持异步。
- 内部实现了限流、负载均衡等，扩展性也更强。Spring Cloud Gateway明确的区分了 Router 和 Filter，并且一个很大的特点是内置了非常多的开箱即用功能，并且都可以通过 SpringBoot
  配置或者手工编码链式调用来使用。
- 依赖于spring-webflux，仅适合于Spring Cloud套件
- Nginx适合于服务器端负载均衡,Zuul和Gateway 是本地负载均衡，适合微服务中实现网关


