package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * https://segmentfault.com/a/1190000022601190
 */
public class ConcurrentDemo {
    public static void main(String[] args) {
       // countDownLatch();
        cyclicBarrier();
    }

    private static void countDownLatch(){
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0 ; i < 3 ; i++) {
            int count = i;
            new Thread(() -> {
                System.out.println("Count down thread id : " + count);
                countDownLatch.countDown();
            }).start();
        }
        try {
            System.out.println("Main thread await!");
            countDownLatch.await();
            System.out.println("Main thread finish!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cyclicBarrier(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            int count = i;
            new Thread(() -> {
                try {
                    Thread.sleep(3L);
                    System.out.println("Thread id waiting : " + count);
                    cyclicBarrier.await();
                    cyclicBarrier.reset();
                    System.out.println("Thread id waiting : " + count);
                    cyclicBarrier.await();
                    System.out.println("Thread id finish : " + count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void semaphore(){
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0 ; i < 5 ; i++) {
            int count = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Thread got semaphore : " + count);
                    Thread.sleep(2L);
                    semaphore.release();
                    System.out.println("Thread release semaphore : " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
