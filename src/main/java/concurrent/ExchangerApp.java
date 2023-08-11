package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ExchangerApp {

    private static final Logger logger = LoggerFactory.getLogger(ExchangerApp.class);


    public static void main(String[] args) {
        exchanger();
    }

    /**
     * 两个线程直接交换对象
     * 一个线程调用Exchanger的exchange()方法时，它会被阻塞，直到另一个线程也调用了exchange()方法。
     * 然后，Exchanger将两个线程传递的对象交换，并使两个线程继续执行
     */
    private static void exchanger() {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread t1 = new Thread(() -> {
            try {
                String message = "线程一信息";
                String receivedMessage = exchanger.exchange(message);
                logger.info("线程一交换到的信息: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程一");

        Thread t2 = new Thread(() -> {
            try {
                String message = "线程二信息";
                String receivedMessage = exchanger.exchange(message);
                logger.info("线程二交换到的信息: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程二");
        t1.start();
        t2.start();
    }

}



