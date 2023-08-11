package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class SemaphoreApp {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreApp.class);

    public static void main(String[] args) {
        semaphore();
    }

    /**
     * 信号量
     * 用于控制并发线程数
     */
    private static void semaphore() {
        int permits = 3;
        Semaphore semaphore = new Semaphore(permits);
        for (int i = 1; i < 5; i++) {
            new Thread(() -> {
                try {
                    int tokens = ThreadLocalRandom.current().nextInt(1,4);
                    logger.info("线程[{}]申请获取[{}]个令牌", Thread.currentThread().getName(), tokens);
                    //获取n个令牌，获取不到会阻塞（不传为1）
                    semaphore.acquire(3);
                    logger.info("线程[{}]成功获取了[{}]个令牌,开始执行", Thread.currentThread().getName(), tokens);
                    Thread.sleep(2L);
                    //释放n个令牌（不传为1）
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程-" + i).start();
        }
    }
}
