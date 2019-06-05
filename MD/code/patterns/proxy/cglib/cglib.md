package patterns.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before run...");
        System.out.println("superName " + methodProxy.getSuperName());
        methodProxy.invokeSuper(o, objects);
        System.out.println("after run...");
        return null;
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.proxy.cglib;

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
aaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.proxy.cglib;

public class Run {
    public void run() {
        System.out.println("你必须奔跑。。。");
    }

    public void sayGoodBye(String str) {
        System.out.println("hello " + str);
    }
}
