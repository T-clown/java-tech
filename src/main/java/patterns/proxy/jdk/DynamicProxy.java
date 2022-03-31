package patterns.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DynamicProxy implements InvocationHandler {
    /**
     * 要代理的真实对象
     */
    private Object invocation;

    /**
     * 构造方法，给我们要代理的真实对象赋初值
     *
     * @param invocation
     */
    public DynamicProxy(Object invocation) {
        this.invocation = invocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        //log.info("before: {} ",method.getName());

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(invocation, args);
        System.out.println(method.getDeclaringClass());
        //在代理真实对象后我们也可以添加一些自己的操作
        //log.info("after: {}",method.getName());
        return result;

    }

}