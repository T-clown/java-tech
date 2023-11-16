package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class CyclicBarrierApp {
    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierApp.class);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        cyclicBarrier();
    }


    private static void cyclicBarrier() {
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, () -> {
            logger.info("所有线程到达屏障点");
        });
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    logger.info("线程[{}]执行到达屏障点", Thread.currentThread().getName());
                    //计数器减一，当计数器为0时，执行屏障方法，然后唤醒所有线程继续执行
                    cyclicBarrier.await();
                    logger.info("线程[{}]继续执行", Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程-" + i).start();
        }
        //重置屏障，可以继续使用了
        cyclicBarrier.reset();
    }
}
