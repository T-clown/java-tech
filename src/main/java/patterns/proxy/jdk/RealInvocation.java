package patterns.proxy.jdk;

public class RealInvocation implements Invocation {
    @Override
    public void run(String name) {
        System.out.println("you must keep running，"+name);
    }

    @Override
    public void sayHello(String str) {
        System.out.println("hello:" + str);
    }
}
