
package patterns.proxy.jdk;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class InvocationProxyFactory<T> implements InvocationHandler {

    private final Class<T> invocationInterface;

    public InvocationProxyFactory(Class<T> invocationInterface) {
        this.invocationInterface = invocationInterface;
    }

    public Class<T> getInvocationInterface() {
        return invocationInterface;
    }

    public T newInstance() {
        return (T)Proxy.newProxyInstance(invocationInterface.getClassLoader(), new Class[] {invocationInterface},
            this);
    }

    public T newInstance(InvocationProxy<T> invocationProxy) {
        return (T) Proxy.newProxyInstance(invocationInterface.getClassLoader(), new Class[] { invocationInterface }, invocationProxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        System.out.println(JSON.toJSONString(args));
        return null;
    }
}
