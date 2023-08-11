[Spring 源码第 9 篇，深入分析 FactoryBean](https://mp.weixin.qq.com/s/uNmbDpiAZ1Lku1GQz7IqDQ)
[一道阿里面试题：说说你知道的关于BeanFactory和FactoryBean的区别](https://mp.weixin.qq.com/s/BAS2T0XjWD0WN4EdBrg0fg)


- BeanFactory是Spring IoC容器的核心接口，它负责管理和创建Bean对象的生命周期。它提供了一种机制，可以通过容器管理的方式，实现Bean的创建、配置、生命周期管理、依赖注入等。BeanFactory是一个抽象接口，定义了IoC容器的基本行为。

- FactoryBean是一个工厂Bean，它是一个特殊的Bean，用于创建其他Bean。它的作用是将复杂的Bean创建逻辑封装到一个单独的Bean中，以便于容器管理。当容器需要获取某个Bean时，会先从FactoryBean中获取该Bean，然后再通过该Bean创建出最终的Bean对象。FactoryBean在Bean的创建过程中提供了更多的控制权，可以自定义Bean的创建过程，从而实现更加灵活的Bean创建和配置。

- 因此，BeanFactory是IoC容器的核心接口，它负责Bean对象的管理和生命周期，而FactoryBean是一种特殊的Bean，它用于创建其他Bean，提供了更多的灵活性和控制权