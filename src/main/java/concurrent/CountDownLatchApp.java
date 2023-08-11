package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaThreadPoolCreation"})
public class CountDownLatchApp {
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchApp.class);

    public static void main(String[] args) throws InterruptedException {
        //用法一
        //countDownLatch(3);
        //用法二
        countDownLatch2();
    }

    private static final Integer WORK_COUNT = 10;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 等待所有子线程就绪后统一执行
     */
    private static void countDownLatch2() {
        // 初始化计数器为 10 的 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < WORK_COUNT; i++) {
            executorService.execute(new Worker(countDownLatch));
        }
        // 主线程执行
        logger.info("主线程执行");
        // 主线程开启开关
        countDownLatch.countDown();
    }

    static class Worker implements Runnable {

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
                logger.info("子线程执行");
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * @param threadCount 线程数量
     * @throws InterruptedException
     * 主线程等待所有子线程执行完毕后再执行
     */
    private static void countDownLatch(int threadCount) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < 3; i++) {
            logger.info("锁数量:{}", countDownLatch.getCount());
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("[{}]执行完毕 ", Thread.currentThread().getName());
                //线程执行完成则调用countDown()
                countDownLatch.countDown();
            }, "线程-" + i).start();
        }
        logger.info("主线程阻塞");
        //线程阻塞：当threadCount个线程调用countDown()后，会唤醒所有调用await()方法的线程
        countDownLatch.await();
        //线程阻塞：时间到或者满足条件后执行
        //boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        logger.info("主线程执行完毕");

    }

}
