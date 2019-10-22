package spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;

public class App {
    @Autowired
    ExampleService exampleService;

    public static void main(String[] args) {
        App app=new App();
        app.test();
    }

    private void test() {
//        //是否是JDK动态代理
//        System.out.println("isJdkDynamicProxy => " + AopUtils.isJdkDynamicProxy(exampleService));
//        //是否是CGLIB代理
//        System.out.println("isCglibProxy => " + AopUtils.isCglibProxy(exampleService));
//        //代理类的类型
//        System.out.println("proxyClass => " + exampleService.getClass());
//        //代理类的父类的类型
//        System.out.println("parentClass => " + exampleService.getClass().getSuperclass());
//        //代理类的父类实现的接口
//        System.out.println("parentClass's interfaces => " + Arrays.asList(exampleService.getClass().getSuperclass().getInterfaces()));
//        //代理类实现的接口
//        System.out.println("proxyClass's interfaces => " + Arrays.asList(exampleService.getClass().getInterfaces()));
//        //代理对象
//        System.out.println("proxy => " + exampleService);
//        //目标对象
//        System.out.println("target => " + AopProxyUtils.getSingletonTarget(exampleService));
//        //代理对象和目标对象是不是同一个
//        System.out.println("proxy == target => " + (exampleService == AopProxyUtils.getSingletonTarget(exampleService)));
//        //目标类的类型
//        System.out.println("targetClass => " + AopProxyUtils.getSingletonTarget(exampleService).getClass());
//        //目标类实现的接口
//        System.out.println("targetClass's interfaces => " + Arrays.asList(AopProxyUtils.getSingletonTarget(exampleService).getClass().getInterfaces()));

        System.out.println("----------------------------------------------------");

        //自己模拟的动态代理的测试
        Service target = new ServiceImpl();
        ProxyByJdkDynamic proxy = new ProxyByJdkDynamic(target);
        proxy.doNeedTx();
        System.out.println("-------");
        proxy.doNotneedTx();
        System.out.println("-------");
    }
}
