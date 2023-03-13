package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SynchronizedApp {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizedApp.class);

    public static void main(String[] args) throws InterruptedException {
        //volatileTest();
        lockPark();
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

    public static void lockPark() {
        Thread t = new Thread(() -> {
            logger.info("[{}]阻塞。。。", Thread.currentThread().getName());
            //LockSupport.unpark(Thread.currentThread());
            LockSupport.park();
            LockSupport.park(null);
            logger.info("[{}]执行。。。", Thread.currentThread().getName());
            logger.info("[{}]执行完毕。。。", Thread.currentThread().getName());
        }, "线程t");
        t.start();
        LockSupport.unpark(t);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("主线程执行");
        //LockSupport.unpark(t);
    }

    public synchronized void lockInstance() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("实例锁：lockInstance");
    }

    public static synchronized void lockClass() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("类锁：lockClass");
    }


    public void lockBlockWithInstanceLock() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(3000);
            System.out.println("实例锁：lockBlockWithInstanceLock");
        }
    }

    public void lockBlockWithClassLock() throws InterruptedException {
        synchronized (SynchronizedApp.class) {
            Thread.sleep(3000);
            System.out.println("类锁：lockBlockWithClassLock");
        }
    }


}
