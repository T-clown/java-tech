package java8.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    //要代理的真实对象
    private Object invocation;

    //构造方法，给我们要代理的真实对象赋初值
    public DynamicProxy(Object invocation) {
        this.invocation = invocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before run ");
        System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(invocation, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after run tired");

        return null;

    }

}
