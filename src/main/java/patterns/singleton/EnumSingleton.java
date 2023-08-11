package patterns.singleton;

/**
 * 枚举单例
 */
public class EnumSingleton {

    private EnumSingleton() {
    }

    enum SingletonEnum {
        /**
         * 枚举对象天生为单例
         */
        INSTANCE;

        private final EnumSingleton instance;


        SingletonEnum() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
}
