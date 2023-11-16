package patterns.proxy.jdk;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class InvocationProxy<T> implements InvocationHandler {

    private final Class<T> invocationInterface;

    private final Object instance;

    public Class<T> getInvocationInterface() {
        return invocationInterface;
    }

    public InvocationProxy(Class<T> invocationInterface, Object instance) {
        this.invocationInterface = invocationInterface;
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (instance != null) {
            return method.invoke(instance, args);
        }
        log.info("method:{},args:{}", method.getName(), JSON.toJSONString(args));
        return method.getName() + "---" + JSON.toJSONString(args);
    }
}
