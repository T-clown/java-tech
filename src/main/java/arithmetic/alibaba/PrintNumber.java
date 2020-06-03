package arithmetic.alibaba;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印奇偶数
 */
public class PrintNumber {

    private static volatile boolean flag = true;

    private static AtomicInteger num = new AtomicInteger();

    private static final Integer TOTAL = 100;

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        long start =  System.currentTimeMillis();
        Thread jsThread = new Thread(() -> {
            while(num.get() <= TOTAL-1){
                if(!flag){
                    System.out.println(Thread.currentThread().getName()+"打印: " + num.getAndIncrement());
                    flag = true;
                }
            }
            latch.countDown();
        });
        jsThread.setName("奇数线程");
        Thread osThread = new Thread(() -> {
            while(num.get() <= TOTAL){
                if(flag){
                    System.out.println(Thread.currentThread().getName()+ "打印 " + num.getAndIncrement());
                    flag = false;
                }
            }
            latch.countDown();
        });
        osThread.setName("偶数线程");
        osThread.start();
        jsThread.start();
        latch.await();
        long end =  System.currentTimeMillis();
        System.out.println("一共耗时："+(end - start) + "ms");

    }

}
