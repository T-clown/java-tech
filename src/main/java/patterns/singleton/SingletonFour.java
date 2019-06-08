package patterns.singleton;

/**
 * 线程安全的双检单例
 */
public class SingletonFour {
    private static volatile SingletonFour instance;

    private SingletonFour(){
        //防止通过反射构造实例
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static SingletonFour getInstance(){
        SingletonFour result=instance;
        if(result==null){
            synchronized (SingletonFour.class){
                result=instance;
                if(result==null){
                    result=instance=new SingletonFour();
                }
            }
        }
        return result;
    }
}