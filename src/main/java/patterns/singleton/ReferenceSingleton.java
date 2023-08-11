package patterns.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS  实现单例模式
 */
public class ReferenceSingleton {

    private static final AtomicReference<ReferenceSingleton> INSTANCE = new AtomicReference<>();

    private ReferenceSingleton() {
        // 私有构造函数
    }

    public static ReferenceSingleton getInstance() {
        while (true) {
            ReferenceSingleton currentInstance = INSTANCE.get();
            if (currentInstance != null) {
                return currentInstance;
            }
            ReferenceSingleton newSingleton = new ReferenceSingleton();
            if (INSTANCE.compareAndSet(null, newSingleton)) {
                return newSingleton;
            }
        }
    }
}

