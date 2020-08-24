package patterns.singleton;

/**
 * 静态内部类单例
 */
public class StaticInnerSingleton {
    private StaticInnerSingleton(){}

    public static StaticInnerSingleton getInstance(){
        return InstanceHolder.INSTANCE;
    }

    private static  class InstanceHolder {
        private  static final StaticInnerSingleton INSTANCE=new StaticInnerSingleton();
    }
}
