package concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorApp {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorApp.class);

    public static final ThreadPoolExecutor EXECUTOR =
            new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(), new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);
                private static final String NAME_PREFIX = "Thread-";

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
                    //t.setUncaughtExceptionHandler((t1, e) -> {
                    //    System.out.println(t1.getName() + "\t" + e.getMessage());
                    //});
                    return t;
                }
            }, new CallerRunsPolicy());

    /**
     * 这个代码的作用，是当进程关闭时，我们将线程池中已经添加的任务继续执行完毕，然后关闭线程池。
     * 作用是防止已添加的任务丢失（kill -9 则不会执行）
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(EXECUTOR::shutdown));
    }

    public static void main(String[] args) throws InterruptedException {

        //int cpu = Runtime.getRuntime().availableProcessors();
        //System.out.println(cpu);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            EXECUTOR.execute(() -> execute(finalI));
        }
        //EXECUTOR.submit()
        //EXECUTOR.shutdown();
//        Thread.sleep(100);
//        List<Runnable> works=EXECUTOR.shutdownNow();


        //Future<String> future = EXECUTOR.submit(() -> submit("clown"));
        //
        //try {
        //    System.out.println(future.get());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}

    }

    private static String submit(String str) {
        return "submit执行：" + str;
    }

    private static void execute(int count) {
        int a = count / 0;
        logger.info("线程[{}]执行：{}", Thread.currentThread().getName(), count);
    }
}
