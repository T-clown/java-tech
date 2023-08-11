package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SynchronizedApp {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizedApp.class);

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) throws InterruptedException {
        SynchronizedApp app = new SynchronizedApp();
        new Thread(() -> {
            app.lockInstance();
            app.lockBlockWithClassLock();
        }, "线程一").start();

        Thread.sleep(1000);

        new Thread(() -> {
            app.lockInstance();
            app.lockBlockWithClassLock();
        }, "线程二").start();
    }


    public synchronized void lockInstance() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("线程[{}]获取实例锁：lockInstance", Thread.currentThread().getName());
    }

    public static synchronized void lockClass() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("线程[{}]获取类锁：lockClass", Thread.currentThread().getName());
    }


    public void lockBlockWithInstanceLock() {
        synchronized (this) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("线程[{}]获取实例锁：lockBlockWithInstanceLock", Thread.currentThread().getName());
        }
    }

    public void lockBlockWithClassLock() {
        synchronized (SynchronizedApp.class) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("线程[{}]获取类锁：lockBlockWithClassLock", Thread.currentThread().getName());
        }
    }

}
