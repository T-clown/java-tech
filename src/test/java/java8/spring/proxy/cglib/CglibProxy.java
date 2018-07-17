package java8.spring.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before run...");
        System.out.println("superName " + methodProxy.getSuperName());
        methodProxy.invokeSuper(o, objects);
        System.out.println("after run...");
        return null;
    }
}
