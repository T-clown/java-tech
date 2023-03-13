package patterns.proxy.jdk.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestExecutor implements Executor {

    @Override
    public void execute(String name) {
        log.info(name);
    }

    @Override
    public void execute(String name, Long count) {
        log.info(name + count);
    }

    public static void main(String[] args) {
        Executor executor=new TestExecutor();
        ExecutorInterceptor interceptor=new ExecutorInterceptor();
        Executor proxy = (Executor) interceptor.plugin(executor);
        proxy.execute("a");
        proxy.execute("a",1L);
    }
}
