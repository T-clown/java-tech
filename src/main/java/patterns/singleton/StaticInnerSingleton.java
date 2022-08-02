package patterns.singleton;

import java.io.Serializable;

/**
 * 静态内部类单例
 */
public class StaticInnerSingleton implements Serializable {
    private StaticInnerSingleton() {
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
