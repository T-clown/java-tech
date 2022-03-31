package patterns.proxy.jdk;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationProxy<T> implements InvocationHandler {

    private final Class<T> invocationInterface;

    private Object instance;


    public InvocationProxy(Class<T> invocationInterface, Object instance) {
        this.invocationInterface = invocationInterface;
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (instance != null) {
            return method.invoke(instance, args);
        }
        return method.getName() + "---" + JSON.toJSONString(args);
    }
}
