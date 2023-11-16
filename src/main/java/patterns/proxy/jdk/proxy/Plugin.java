package patterns.proxy.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Plugin implements InvocationHandler {
    private final Object target;
    private final Interceptor interceptor;
    private final Map<Class<?>, Set<Method>> signatureMap;

    private Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    public static Object wrap(Object target, Interceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        return interfaces.length > 0 ? Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Plugin(target, interceptor, signatureMap)) : target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Set<Method> methods = this.signatureMap.get(method.getDeclaringClass());
            return methods != null && methods.contains(method) ? this.interceptor.intercept(new Invocation(this.target, method, args)) : method.invoke(this.target, args);
        } catch (Exception var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) {
        Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptsAnnotation == null) {
            throw new PluginException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        } else {
            Signature[] sigs = interceptsAnnotation.value();
            Map<Class<?>, Set<Method>> signatureMap = new HashMap<>();
            for (Signature sig : sigs) {
                Set<Method> methods = MapUtil.computeIfAbsent(signatureMap, sig.type(), (k) -> new HashSet<>());
                try {
                    Method method = sig.type().getMethod(sig.method(), sig.args());
                    methods.add(method);
                } catch (NoSuchMethodException e) {
                    throw new PluginException("Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + e, e);
                }
            }

            return signatureMap;
        }
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        HashSet<Class<?>> interfaces;
        for (interfaces = new HashSet<>(); type != null; type = type.getSuperclass()) {
            Class<?>[] typeInterfaces = type.getInterfaces();
            for (Class<?> c : typeInterfaces) {
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                }
            }
        }

        return interfaces.toArray(new Class[0]);
    }
}

