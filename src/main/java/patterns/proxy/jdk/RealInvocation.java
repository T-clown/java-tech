package patterns.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealInvocation implements Invocation {
    @Override
    public void run(String name) {
        log.info("you must keep running，" + name);
    }

    @Override
    public String sayHello(String str) {
        return "hello:" + str;
    }
}
