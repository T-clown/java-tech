package patterns.proxy.jdk.proxy;

import java.util.Properties;
/**
 * @author ti
 */
public interface Interceptor {
    /**
     * 拦截方法
     * @param invocation
     * @return 真实方法执行结果
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 代理
     * @param target
     * @return 代理
     */
    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    default void setProperties(Properties properties) {

    }
}
