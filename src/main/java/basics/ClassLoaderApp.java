package basics;

public class ClassLoaderApp {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader appClassLoader = ClassLoaderApp.class.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(contextClassLoader.getName());
        System.out.println(appClassLoader.getName());
        System.out.println(systemClassLoader.getName());
        System.out.println(appClassLoader.getParent().getName());
        System.out.println(appClassLoader.getParent().getParent());
    }
}
