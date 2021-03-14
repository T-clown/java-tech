package concurrent.thread;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class ThreadLocalApp {
    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "初始值");

    private String getName() {
        return threadLocal.get();
    }

    private void setName(String name) {
        threadLocal.set(name);
    }

    @Getter
    @Setter
    public String threadName;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Thread t = new Thread(() -> test("abc", false));
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join();
    }

    private static void test(String s, boolean isGC) {
        try {
            new ThreadLocal<>().set(s);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void threadLocalDemo() {
        int threads = 9;
        ThreadLocalApp demo = new ThreadLocalApp();
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

    private static void threadDemo() {
        ThreadLocalApp demo = new ThreadLocalApp();
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