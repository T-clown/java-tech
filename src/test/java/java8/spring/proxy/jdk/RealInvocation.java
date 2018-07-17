package java8.spring.proxy.jdk;

public class RealInvocation implements Invocation {
    @Override
    public void run() {
        System.out.println("you must keep running");
    }

    @Override
    public void sayHello(String str) {
        System.out.println("hello:" + str);
    }
}
