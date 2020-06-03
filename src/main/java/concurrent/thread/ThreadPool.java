package concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    public static final ThreadPoolExecutor EXECUTOR =
        new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            private static final String NAME_PREFIX = "Thread";

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(Thread.currentThread().getThreadGroup(), r,
                    NAME_PREFIX + threadNumber.getAndIncrement(),
                    0);
                //是否守护进程
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY) {
                    t.setPriority(Thread.NORM_PRIORITY);
                }
                t.setUncaughtExceptionHandler((t1, e) -> {
                    System.out.println(t1.getName() + "\t" + e.getMessage());
                });
                return t;
            }
        }, new AbortPolicy());

    /**
     * 这个代码的作用，是当进程关闭时，我们将线程池中已经添加的任务继续执行完毕，然后关闭线程池。
     * 作用是防止已添加的任务丢失
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(EXECUTOR::shutdown));
    }



    public static void main(String[] args) {

        int cpu = Runtime.getRuntime().availableProcessors();

        EXECUTOR.execute(() -> execute("clown"));

        Future<String> future = EXECUTOR.submit(() -> task("clown"));

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static String task(String str) {
        int a = 1 / 0;
        return "submit执行：" + str;
    }

    private static void execute(String name) {
        int a = 1 / 0;
        System.out.println("execute执行：" + name);
    }
}
