package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class PhaserApp {
    private static final Logger logger = LoggerFactory.getLogger(PhaserApp.class);

    /**
     * 用于控制多个线程分阶段执行任务。它将任务分为多个阶段，并在每个阶段的末尾等待所有线程完成任务，
     * 然后进入下一个阶段。Phaser可以被看作是CountDownLatch和CyclicBarrier的组合，但具有更高的灵活性和可重用性
     *
     * @param args
     */
    public static void main(String[] args) {
        //线程数
        final int threads = 2;
        //阶段数
        final int phases = 3;
        final Phaser phaser = new Phaser(threads);

        for (int i = 1; i <= threads; i++) {
            Thread thread = new Thread(() -> {
                //注册自己
                phaser.register();
                for (int phase = 1; phase <= phases; phase++) {
                    logger.info("线程[{}] - 阶段[{}]", Thread.currentThread().getName(), phase);
                    //线程执行完后通知Phaser，如果都执行完则继续进入下一阶段，否则阻塞
                    //phaser.arriveAndAwaitAdvance();
                    ///执行完后注销自己，不参与下一阶段任务
                    phaser.arriveAndDeregister();
                    logger.info("线程[{}]继续执行 - 阶段[{}]", Thread.currentThread().getName(), phase);
                }
            }, "线程-" + i);
            thread.start();
        }
    }

}
