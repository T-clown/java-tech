package basics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import lombok.Data;

public class QueueApp {

    public static void main(String[] args) throws InterruptedException {
        arrayBlockingQueue();

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
        DelayQueue<A> queue = new DelayQueue<>();
        queue.add(new A());
        queue.put(new A());
        boolean add = queue.offer(new A());
        System.out.println(add);
        System.out.println(queue.remove());
        queue.poll();
        queue.take();
        queue.element();
        queue.peek();
        queue.forEach(System.out::println);
    }

    @Data
    static class A implements Delayed {
        private int i;

        @Override
        public long getDelay(TimeUnit unit) {
            return 1000;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
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
