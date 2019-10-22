package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import spring.entity.Book;

/**
 * 用于增强bean
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 容器加载的时候会加载一些其他的bean，会调用初始化前和初始化后方法
    // 这次只关注book(bean)的生命周期
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
        }
        return bean;
    }
}