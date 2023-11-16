package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockApp {

    private static ReentrantLock lock = new ReentrantLock(false);

    private static final int BUFFER_SIZE = 5;
    private static Queue<Integer> buffer = new LinkedList<>();

    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();

    private static Condition available = lock.newCondition();

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) throws InterruptedException {
        Thread producerThread = new Thread(() -> {

            lock.lock();
            try {
                log.info("线程[{}]获取锁", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                //内部会将当前线程加入到条件队列，然后释放锁，然后阻塞
                //当被唤醒后，会重新去竞争锁，拿到锁后继续向下执行
                available.await();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
                log.info("线程[{}]释放锁", Thread.currentThread().getName());
            }
        });

        TimeUnit.SECONDS.sleep(2);

        Thread consumerThread = new Thread(() -> {
            lock.lock();
            try {
                log.info("线程[{}]获取锁", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                available.signal();
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
                log.info("线程[{}]释放锁", Thread.currentThread().getName());
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void producerConsumer() {
        //ArrayBlockingQueue
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    Integer value = consume();
                    log.info("获取值:{}", value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void produce(int value) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            log.info("线程[{}]获取锁", Thread.currentThread().getName());
            while (buffer.size() == BUFFER_SIZE) {
                //等待队列非满
                log.info("队列已满，线程[{}]等待", Thread.currentThread().getName());
                notFull.await();
            }
            buffer.offer(value);
            notEmpty.signal();
        } finally {
            lock.unlock();
            //log.info("线程[{}]释放锁", Thread.currentThread().getName());
        }
    }

    private static Integer consume() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            log.info("线程[{}]获取锁", Thread.currentThread().getName());
            while (buffer.isEmpty()) {
                //等待队列非空
                log.info("队列为空，线程[{}]等待", Thread.currentThread().getName());
                notEmpty.await();
            }
            notFull.signal();
            return buffer.poll();
        } finally {
            lock.unlock();
            //log.info("线程[{}]释放锁", Thread.currentThread().getName());
        }
    }
}
