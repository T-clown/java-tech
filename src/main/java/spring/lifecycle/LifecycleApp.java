package spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import spring.entity.Book;
import spring.entity.SubBook;

public class LifecycleApp {
    public static void main(String[] args) {
        lifecycle();
       // conpleteLifecycle();
    }

    private static void lifecycle(){
        // 为面试而准备的Bean生命周期加载过程
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        //ApplicationContext context =  new FileSystemXmlApplicationContext("classpath:spring-application.xml");
        Book book = (Book)context.getBean("book");
        System.out.println("第九步：Book name = " + book.getBookName());
        // ((ClassPathXmlApplicationContext) context).destroy();
        ((ClassPathXmlApplicationContext) context).destroy();
    }

    private static void conpleteLifecycle(){
        // 完整的加载过程，当然了解的越多越好
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-application.xml");
        SubBook subBookClass = (SubBook) applicationContext.getBean("subBook");
        System.out.println("BookSystemName = " + subBookClass.getBookSystem());
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}