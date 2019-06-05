package spring.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Bean的生命周期
 */
public class Book  implements BeanNameAware, BeanFactoryAware,
    ApplicationContextAware, InitializingBean, DisposableBean {
    private String bookName;

    public Book(){
        System.out.println( "第一步：Book Initializing ");
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("第二步：setBookName: Book name has set.");
    }

    public void setBeanName(String name) {
        System.out.println("第三步：Book.setBeanName invoke");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第四步：Book.setBeanFactory invoke");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第五步：Book.setApplicationContext invoke");
    }

    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("第六步：@PostConstruct");
    }



    public void afterPropertiesSet() {
        System.out.println("第七步：Book.afterPropertiesSet invoke");
    }


    public void myPostConstruct(){
        System.out.println("第八步：Book.myPostConstruct invoke");
    }

    public String getBookName() {
        System.out.println("第九步：使用bean");
        return bookName;
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPreDestory(){
        System.out.println("第十步：@PreDestory");
    }

    public void destroy() {
        System.out.println("第十一步：Book.destory invoke");
    }


    public void myPreDestory(){
        System.out.println("第十二步Book.myPreDestory invoke");
        System.out.println("---------------destroy-----------------");
    }



    @Override
    protected void finalize() {
        System.out.println("------inside finalize-----");
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaa
package spring.entity;

import lombok.Data;

@Data
public class Phone {
    public String name;
    public String brand;
    public int price;
}
aaaaaaaaaaaaaaaaaaaaaaa
package spring.entity;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

public class SubBook extends Book implements BeanClassLoaderAware,
    EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware,
    ApplicationEventPublisherAware, MessageSourceAware {

    private String bookSystem;

    public String getBookSystem() {
        return bookSystem;
    }

    public void setBookSystem(String bookSystem) {
        System.out.println("设置BookSystem 的属性值");
        this.bookSystem = bookSystem;
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("SubBookClass.setBeanClassLoader() 方法被调用了");
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("SubBookClass.setApplicationEventPublisher() 方法被调用了");
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("SubBookClass.setEmbeddedValueResolver() 方法被调用了");
    }

    public void setEnvironment(Environment environment) {
        System.out.println("SubBookClass.setEnvironment() 方法被调用了");
    }

    public void setMessageSource(MessageSource messageSource) {
        System.out.println("SubBookClass.setMessageSource() 方法被调用了");
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("SubBookClass.setResourceLoader() 方法被调用了");
    }

}