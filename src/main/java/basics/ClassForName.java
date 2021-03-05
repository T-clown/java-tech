package basics;

public class ClassForName {
    static {
        System.out.println("执行静态代码块");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //会执行静态代码块
        Class<?> clazz = Class.forName("basics.ClassForName");
        //不会执行静态代码块
        Class<?> clazz2 = ClassLoader.getSystemClassLoader().loadClass("basics.ClassForName");
    }
}
