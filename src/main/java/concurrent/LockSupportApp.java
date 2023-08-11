package concurrent;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class LockSupportApp {
    private static final Logger logger = LoggerFactory.getLogger(LockSupportApp.class);

    public static void main(String[] args) throws InterruptedException {
        lockPark();
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void lockPark() throws InterruptedException {
        Thread thread = new Thread(() -> {
            logger.info("[{}]阻塞。。。", Thread.currentThread().getName());
            //唤醒线程，可以提前给自己许可证，下次park则不阻塞(只能提前给一张许可证)
            //LockSupport.unpark(Thread.currentThread());
            //阻塞当前线程，直到当前线程被中断或者被 unpark() 方法唤醒
            //LockSupport.park();
            logger.info("[{}]执行。。。", Thread.currentThread().getName());
            //阻塞当前线程1000纳秒，直到时间到期或者当前线程被中断或者被 unpark() 方法唤醒
            //LockSupport.parkNanos(1000);
            //阻塞当前线程，blocker参数一般传当前this对象（代表谁调park方法阻塞了此线程），方便排查问题用的
            LockSupport.park("blocker");
            logger.info("[{}]执行完毕。。。", Thread.currentThread().getName());
        }, "线程一");
        thread.start();
        Thread.sleep(3000);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        logger.info(JSON.toJSONString(LockSupport.getBlocker(thread)));

        logger.info("主线程执行");
        Thread.sleep(3000);
        //唤醒thread线程
        LockSupport.unpark(thread);
    }
}
