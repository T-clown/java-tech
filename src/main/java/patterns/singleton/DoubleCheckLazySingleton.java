package patterns.singleton;

/**
 * 线程安全的双检单例(懒汉式的变种)
 */
public class DoubleCheckLazySingleton {
    private static volatile DoubleCheckLazySingleton instance;

    private DoubleCheckLazySingleton(){
        //防止通过反射构造实例
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static DoubleCheckLazySingleton getInstance(){
        DoubleCheckLazySingleton result=instance;
        if(result==null){
            synchronized (DoubleCheckLazySingleton.class){
                result=instance;
                if(result==null){
                    result=instance=new DoubleCheckLazySingleton();
                }
            }
        }
        return result;
    }
}