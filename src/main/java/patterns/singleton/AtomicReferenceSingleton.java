package patterns.singleton;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSingleton {
    private static final AtomicReference<AtomicReferenceSingleton> INSTANCE = new AtomicReference<>();

    private AtomicReferenceSingleton() {
    }

    public static AtomicReferenceSingleton getInstance() {
        while (INSTANCE.get() == null) {
            if (INSTANCE.compareAndSet(null, new AtomicReferenceSingleton())) {
                break;
            }
        }
        return INSTANCE.get();
    }
}
