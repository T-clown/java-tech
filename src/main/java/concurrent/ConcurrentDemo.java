package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ImageUtil;

public class ConcurrentDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentDemo.class);

    private static final Integer WORK_COUNT = 10;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //// 初始化计数器为 10 的 CountDownLatch
        //CountDownLatch countDownLatch = new CountDownLatch(1);
        //
        //for (int i = 0; i < WORK_COUNT; i++) {
        //    executorService.execute(new Worker(countDownLatch));
        //}
        //
        //// 主线程执行
        //doSomething();
        //// 主线程开启开关
        //countDownLatch.countDown();
        //
        //// 平滑地关闭 ExecutorService
        //executorService.shutdown();
        //cyclicBarrier();
        // semaphore();
        exchanger();
    }

    private static void exchanger() {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                String beforeObj = Thread.currentThread().getName();
                try {
                    String afterObj = exchanger.exchange(Thread.currentThread().getName());
                    System.out.println(String.format("currentThread %s , before exchange %s , after exchange %s",
                        Thread.currentThread().getName(), beforeObj, afterObj));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void doSomething() {
        // ...
        System.out.print("start..");
    }

    private static void countDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 3; i++) {
            int count = i;
            new Thread(() -> {
                logger.info("Count down thread id : " + count);
                countDownLatch.countDown();
            }).start();
        }
        try {
            logger.info("Main thread await!");
            countDownLatch.await();
            logger.info("Main thread finish!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            int count = i;
            new Thread(() -> {
                try {
                    Thread.sleep(3L);
                    System.out.println("Thread id waiting : " + count);
                    cyclicBarrier.await();
                    //cyclicBarrier.reset();
                    //System.out.println("Thread id waiting : " + count);
                    //cyclicBarrier.await();
                    System.out.println("Thread id finish : " + count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        cyclicBarrier.reset();
    }

    private static void semaphore() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            int count = i;
            new Thread(() -> {
                try {
                    semaphore.acquire(3);
                    System.out.println("Thread got semaphore : " + count);
                    Thread.sleep(2L);
                    semaphore.release();
                    System.out.println("Thread release semaphore : " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

class Worker implements Runnable {

    private final CountDownLatch countDownLatch;

    Worker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 所有执行线程在此处等待开关开启 [多个子线程同时执行]
            countDownLatch.await();
            // 子线程执行
            doWork();
        } catch (InterruptedException ignored) {
        }
    }

    private void doWork() {
        System.out.print("run..");
    }

}


