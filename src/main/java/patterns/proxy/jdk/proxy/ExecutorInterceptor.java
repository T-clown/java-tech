package patterns.proxy.jdk.proxy;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 拦截结果集的处理
 */
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class, method = "execute", args = {String.class}
        ),
        @Signature(
                type = Executor.class,
                method = "execute",
                args = {String.class, Long.class}
        )
})
public class ExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("增强方法：{},参数个数:{}", invocation.getMethod().getName(), invocation.getArgs().length);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        log.info("拦截的类：{}", target.getClass().getName());
//        if (target instanceof Executor) {
//            return Plugin.wrap(target, this);
//        }
         return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info(JSON.toJSONString(properties));
    }
}
