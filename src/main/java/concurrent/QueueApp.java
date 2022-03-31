package concurrent;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import util.DateUtil;

public class QueueApp {

    public static void main(String[] args) throws InterruptedException {
        delayQueue();
        //priorityBlockingQueue();
    }

    private static void arrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.add(1);
        queue.put(2);
        boolean add = queue.offer(1);
        System.out.println(add);
        System.out.println(queue.remove());
        queue.poll();
        queue.take();
        queue.element();
        queue.peek();
        queue.forEach(System.out::println);
    }

    private static void linkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);
        queue.add(1);
        queue.put(2);
        boolean add = queue.offer(1);
        System.out.println(add);
        System.out.println(queue.remove());
        queue.poll();
        queue.take();
        queue.element();
        queue.peek();
        queue.forEach(System.out::println);
    }

    private static void delayQueue() throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        System.out.println(DateUtil.format(new Date()));
        new Thread(() -> {

            delayQueue.offer(new DelayTask("task1",5));
            delayQueue.offer(new DelayTask("task2",11));
            delayQueue.offer(new DelayTask("task3",11));
            delayQueue.offer(new DelayTask("task4",20));
            delayQueue.offer(new DelayTask("task5",26));
            delayQueue.offer(new DelayTask("task6",33));
            delayQueue.offer(new DelayTask("task7",55));

        }).start();

        while (true) {
            Delayed take = delayQueue.take();
            System.out.println(take);
        }
    }

    @Data
    static class DelayTask implements Delayed {
        private String name ;
        private long start = System.currentTimeMillis();
        //second
        private long delayTime;

        public DelayTask(String name, long delayTime) {
            this.name = name;
            this.delayTime = delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert((start+TimeUnit.SECONDS.toMillis(delayTime)) - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            return (int) (this.getDelay(TimeUnit.SECONDS) - delayed.getDelay(TimeUnit.SECONDS));
        }

        @Override
        public String toString() {
            return "DelayeTask[ " +
                    "name='" + name + '\'' +
                    ", delayTime=" + delayTime +"s,任务加入时间:"+DateUtil.format(new Date(start))+",执行时间"+DateUtil.format(new Date())+
                    ']';
        }
    }

    private static void priorityBlockingQueue() throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.add(1);
        queue.put(2);
        boolean add = queue.offer(1);
        System.out.println(add);
        System.out.println(queue.remove());
        queue.poll();
        queue.take();
        queue.element();
        queue.peek();
        queue.forEach(System.out::println);
    }

    private static void concurrentLinkedQueue() throws InterruptedException {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(1);
        boolean add = queue.offer(1);
        System.out.println(add);
        System.out.println(queue.remove());
        queue.poll();
        queue.element();
        queue.peek();
        queue.forEach(System.out::println);
    }
}
