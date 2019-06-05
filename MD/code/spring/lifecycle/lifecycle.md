package spring.lifecycle;

import spring.entity.Phone;

public class BeanFactory {
    public static Phone getInstance(){
        return new Phone();
    }

    public Phone getPhone(){
        return new Phone();
    }
}
aaaaaaaaaaaaaaa
package spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.entity.Phone;

public class InitApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-application.xml");
        //无参构造
        Phone phone1 = (Phone)context.getBean("phone1");
        Phone phone2 = (Phone)context.getBean("phone2");
        Phone phone3= (Phone)context.getBean("phone3");
        System.out.println(phone1);
        System.out.println(phone2);
        System.out.println(phone3);
    }
}
aaaaaaaaaaaaaaaa
package spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import spring.entity.Book;
import spring.entity.SubBook;

public class LifecycleApp {
    public static void main(String[] args) {
        lifecycle();
    }

    private static void lifecycle(){
        // 为面试而准备的Bean生命周期加载过程
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        ApplicationContext context =  new FileSystemXmlApplicationContext("classpath:spring-application.xml");
        Book book = (Book)context.getBean("book");
        System.out.println("第九步：Book name = " + book.getBookName());
        // ((ClassPathXmlApplicationContext) context).destroy();
        ((FileSystemXmlApplicationContext) context).destroy();
    }

    private static void conpleteLifecycle(){
        // 完整的加载过程，当然了解的越多越好
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-application.xml");
        SubBook subBookClass = (SubBook) applicationContext.getBean("subBook");
        System.out.println("BookSystemName = " + subBookClass.getBookSystem());
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}
aaaaaaaaaaaaaaaaaa

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
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
        }
        return bean;
    }
}