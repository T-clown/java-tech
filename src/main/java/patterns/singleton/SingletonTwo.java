package patterns.singleton;

/**
 * 2.懒汉式单例
 */
public class SingletonTwo {
    private SingletonTwo(){}

    public static SingletonTwo getInstance(){
        return InitInstance.INSTANCE;
    }

    private static  class  InitInstance{
        private  static final SingletonTwo INSTANCE=new SingletonTwo();
    }
}
