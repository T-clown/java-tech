package patterns.singleton;

/**
 * 饿汉式单例
 */
public class HungrySingleton {

    private HungrySingleton() {
    }

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}