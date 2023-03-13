package patterns.proxy.jdk.proxy;

public interface Executor {
    void execute(String name);

    void execute(String name,Long count);
}
