package patterns.proxy.jdk.proxy;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author ti
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Signature {
    /**
     * 拦截的类
     * @return 类
     */
    Class<?> type();

    /**
     * 拦截的方法
     * @return 方法名称
     */
    String method();

    /**
     * 拦截的方法参数
     * @return 参数类型
     */
    Class<?>[] args();
}
