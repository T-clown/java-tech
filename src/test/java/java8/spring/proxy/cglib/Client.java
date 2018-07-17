package java8.spring.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Run run = getInstance(cglibProxy);
        run.run();
        run.sayGoodBye("美女");
    }

    public static Run getInstance(CglibProxy proxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Run.class);
        //回调方法的参数为代理类对象CglibProxy，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
        enhancer.setCallback(proxy);
        // 此刻，base不是单纯的目标类，而是增强过的目标类
        return (Run)enhancer.create();
    }
}
