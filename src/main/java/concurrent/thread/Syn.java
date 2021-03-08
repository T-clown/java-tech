package concurrent.thread;

import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Syn {
    private static Object resource = new Object();
    private static final Logger logger = LoggerFactory.getLogger(Syn.class);


    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            logger.info("[{}]阻塞。。。", Thread.currentThread().getName());
            LockSupport.park();
            logger.info("[{}]执行。。。", Thread.currentThread().getName());
            LockSupport.park();
            logger.info("[{}]执行完毕。。。", Thread.currentThread().getName());
        },"线程t");
        t.start();
        Thread.sleep(5000);
        logger.info("主线程执行");
        LockSupport.unpark(t);

    }



    public static synchronized void syn1() {

    }

    public synchronized void syn2() {
        LockSupport.park(this);
    }

    public static void syn3() {
        synchronized (resource) {

        }
    }

    public void syn4() {
        synchronized (resource) {

        }
    }

    public void syn5() {
        synchronized (Syn.class) {

        }
    }
}
