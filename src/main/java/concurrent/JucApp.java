package concurrent;

import java.util.concurrent.locks.LockSupport;

public class JucApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("LockSupport.park");
            LockSupport.park();
            System.out.println("LockSupport.unpark");
        });
        LockSupport.unpark(t);
        t.start();
        t.join();
        System.out.println("main thread unpark t");
        LockSupport.unpark(t);
    }
}
