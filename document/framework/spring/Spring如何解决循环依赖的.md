[Spring 的循环依赖：真的必须非要三级缓存吗](https://mp.weixin.qq.com/s/jVK70MXWjUVFLIGqBkYCgw)

1. 提前暴露对象引用：当容器创建Bean A时，如果发现Bean A依赖于Bean B，但Bean B尚未完全创建，Spring会提前暴露Bean A的一个早期引用，以便让Bean B可以在后续创建过程中引用到Bean A的实例。

2. 使用三级缓存：Spring在创建Bean的过程中使用三级缓存来解决循环依赖。三级缓存分别是singletonFactories、earlySingletonObjects和singletonObjects。
   当创建Bean时，会先检查singletonObjects缓存中是否存在该Bean的实例，如果存在则直接返回。如果不存在，则检查earlySingletonObjects缓存中是否存在早期引用，
   如果存在则返回早期引用。如果早期引用也不存在，则使用singletonFactories中的工厂方法创建Bean的实例。创建完成后，将Bean实例放入singletonObjects缓存中，并清除早期引用和工厂方法。

3. 构造器循环依赖：对于构造器循环依赖的情况，Spring无法通过提前暴露对象引用来解决。在这种情况下，Spring会抛出BeanCurrentlyInCreationException异常，表示无法解决循环依赖。