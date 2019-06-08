package patterns.singleton;

/**
 * 1.饿汉式单例
 */
public class SingletonOne {
    private SingletonOne(){}

    private  static  final  SingletonOne INSTANCE=new SingletonOne();

    public static SingletonOne getInstance(){
        return INSTANCE;
    }
}