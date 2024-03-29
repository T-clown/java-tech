package patterns.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //demo();
        test();
    }

    private static void demo() {
        InvocationProxyFactory<Invocation> proxyFactory = new InvocationProxyFactory<>(Invocation.class);
        Invocation proxy = proxyFactory.newInstance();
        proxy.run("aa");
        proxy.sayHello("aa");


        Invocation proxy2 = proxyFactory.newInstance(new InvocationProxy<>(Invocation.class, null));

        proxy2.sayHello("aaa");
    }

    public static void test() {
        //要代理的真实对象
        Invocation invocation = new RealInvocation();

        //我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(invocation);

        /**
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上

         */
        ClassLoader classLoader = handler.getClass().getClassLoader();
        Class[] interfaces = invocation.getClass().getInterfaces();

        Invocation proxyInstance = (Invocation) Proxy.newProxyInstance(classLoader, interfaces, handler);
//        Invocation proxyInstance = (Invocation)Proxy.newProxyInstance(Invocation.class.getClassLoader(), new Class[]{Invocation.class}, handler);
//
//        System.out.println(proxyInstance.getClass().getName());
//
        String result = proxyInstance.sayHello("江南");
        System.out.println(result);

        //proxyInstance.run("江南");


    }
}