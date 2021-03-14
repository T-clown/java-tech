package patterns.proxy.jdk;

public interface Invocation {
    void run(String name);

    String sayHello(String str);
}