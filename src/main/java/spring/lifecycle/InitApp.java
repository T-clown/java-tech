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