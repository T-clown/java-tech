package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class VolatileApp {
    private static final Logger logger = LoggerFactory.getLogger(VolatileApp.class);

    static volatile int value = 0;

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) throws InterruptedException {
        //volatileTest();
        new Thread(() -> {
            int val = value;
            while (val < 5) {
                if (val != value) {
                    logger.info("[{}]读取到的旧值value：{},新值value:{}", Thread.currentThread().getName(), val, value);
                    val = value;
                }
            }
        }, "线程二").start();

        new Thread(() -> {
            int val = value;
            while (val < 5) {
                logger.info("[{}]修改后的value：{}", Thread.currentThread().getName(), val += 1);
                value = val;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程一").start();

    }

    /**
     * init_value的最大值
     */
    final static int MAX = 5;
    /**
     * init_value的初始值
     */
    static volatile int initValue = 0;

    @SuppressWarnings({"AlibabaAvoidManuallyCreateThread"})
    public static void volatileTest() {
        /**
         * 启动一个Reader线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
         */
        new Thread(() ->
        {
            int localValue = initValue;
            while (localValue < MAX) {
                if (initValue != localValue) {
                    logger.info("线程一，initValue已被改成:{}", initValue);
                    /**
                     * 对localValue进行重新赋值
                     */
                    localValue = initValue;
                }
            }
        }, "Reader").start();
        /**
         * 启动Updater线程，主要用于对init_value的修改，当local_value >= 5 的时候则退出生命周期
         */
        new Thread(() ->
        {
            int localValue = initValue;
            while (localValue < MAX) {
                /**
                 * 修改init_value
                 */
                logger.info("线程二，initValue 会被改成:{}", localValue += 1);
                initValue = localValue;
                try {
                    /**
                     * 短暂休眠，目的是为了使Reader线程能够来得及输出变化内容
                     */
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }

}
