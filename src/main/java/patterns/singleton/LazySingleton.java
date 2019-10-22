package patterns.singleton;

/**
 * 懒汉式
 * 线程安全的延迟加载单例
 */
public class LazySingleton {
    private  static volatile LazySingleton instance;
    private LazySingleton(){
        //防止通过反射初始化实例
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized LazySingleton getInstance(){
        if(instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }
}