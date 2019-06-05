package patterns.singleton;

public class AppTest {
    public static void main(String[] args) {
        System.out.println(SingletonOne.getInstance()==SingletonOne.getInstance());

        System.out.println(SingletonTwo.getInstance()==SingletonTwo.getInstance());

        System.out.println(SingletonThree.getInstance()==SingletonThree.getInstance());

        System.out.println(SingletonFour.getInstance()==SingletonFour.getInstance());
    }
}
aaaaaaaaaaaaaaaaaaaaaaaa
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
aaaaaaaaaaaaaaaaaaaaaaaaaa
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
aaaaaaaaaaaaaaaaaaaa
package patterns.singleton;

/**
 * 线程安全的延迟加载单例
 */
public class SingletonThree {
    private  static SingletonThree instance;
    private SingletonThree(){
        //防止通过反射初始化实例
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized SingletonThree getInstance(){
        if(instance==null){
            instance=new SingletonThree();
        }
        return instance;
    }
}
aaaaaaaaaaaaaaaaaaa
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
