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