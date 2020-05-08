package concurrent.thread;

import java.util.concurrent.CountDownLatch;

import lombok.Getter;
import lombok.Setter;

public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String getName() {
        return threadLocal.get();
    }

    private void setName(String name) {
        threadLocal.set(name);
    }

    @Getter
    @Setter
    public String threadName;



    public static void main(String[] args) {
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        // threadDemo();
        //threadLocalDemo();
    }

    private static void threadLocalDemo(){
        int threads = 9;
        ThreadLocalDemo demo = new ThreadLocalDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                demo.setName(Thread.currentThread().getName());
                System.out.println(demo.getName());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();
        }
    }

    private static void threadDemo(){
        ThreadLocalDemo demo = new ThreadLocalDemo();
        int threads = 200;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                demo.setThreadName(Thread.currentThread().getName());
                System.out.println(demo.getThreadName());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();
        }

    }
}