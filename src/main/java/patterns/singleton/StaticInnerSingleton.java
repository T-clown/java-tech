package patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 静态内部类单例
 */
@Slf4j
public class StaticInnerSingleton implements Serializable {
    private StaticInnerSingleton() {
        log.info("执行StaticInnerSingleton无参构造方法");
    }

    public static StaticInnerSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final StaticInnerSingleton INSTANCE = new StaticInnerSingleton();
    }

    /**
     * 反序列化限制唯一性
     *
     * @return
     */
    private Object readResolve() {
        return InstanceHolder.INSTANCE;
    }
}
