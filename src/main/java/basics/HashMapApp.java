package basics;

import concurrent.thread.ThreadPoolExecutorApp;

import java.util.HashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * HashMap在多线程环境下的问题:
 * 1.不是线程安全的：HashMap不是线程安全的数据结构，多个线程同时对HashMap进行读写操作可能会导致数据不一致或意外的结果。如果多个线程同时对HashMap进行修改，可能会引发并发修改异常（ConcurrentModificationException）或导致数据损坏。
 * computeIfAbsent方法会导致并发修改异常
 * <p>
 * 2.可能导致死循环：在并发环境下，如果多个线程同时对HashMap进行扩容或进行节点迁移操作，可能会导致链表中的节点形成环形结构，进而导致死循环。
 * 在扩容阶段，在jdk1.7之前，hashmap在扩容到2倍新容器时，由于采用的是头插法「头插法就是总是把新增结点插在头部」，会造成链表翻转形成闭环，也就是形成死循环，jdk1.8之后就不再采用头插法了，而是直接插入链表尾部，因此不会形成环形链表形成死循环
 * <p>
 * 3.可能丢失数据：当一个线程在进行扩容的时候，另一个线程put数据，从而导致数据丢失。
 * <p>
 * 4.可能引发无限扩容：当多个线程同时向HashMap插入元素时，可能会导致多个线程同时检测到需要进行扩容的条件，从而触发多次扩容操作，导致无限扩容的问题。
 * <p>
 * 扩容流程：
 * 1.创建一个新的、更大容量的数组，新容量通常是原容量的两倍。例如，如果当前容量为16，则新容量为32。
 * <p>
 * 2.遍历原数组中的每个元素，重新计算每个元素的哈希值，并根据新容量计算它们在新数组中的位置。
 * <p>
 * 3.将元素存储到新数组的对应位置上。如果多个元素计算出的位置相同（即发生了哈希碰撞），则使用链表或红黑树（Java 8+）来解决冲突，保持元素的顺序。
 * <p>
 * 4.将新数组设为HashMap的存储数组，原数组会被垃圾回收。
 */
public class HashMapApp {
    private static HashMap<Integer, Integer> INSTANCE = new HashMap<>();
    //private static final Map<Integer, Integer> INSTANCE = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //装饰器模式，每个方法都采用synchronized同步
        //Collections.synchronizedMap(INSTANCE);
        //Hashtable<Integer, Integer> integerIntegerHashTable = new Hashtable<Integer, Integer>();

        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            ThreadPoolExecutorApp.EXECUTOR.execute(() -> put(finalI, finalI));
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println(ThreadPoolExecutorApp.EXECUTOR.getQueue().size());
        System.out.println(ThreadPoolExecutorApp.EXECUTOR.getTaskCount());
        System.out.println(ThreadPoolExecutorApp.EXECUTOR.getCompletedTaskCount());
        System.out.println(INSTANCE.size());
    }

    private static void put(int key, int value) {

        //此方法会产生ConcurrentModificationException
        //INSTANCE.computeIfAbsent(key, x -> value);
        INSTANCE.putIfAbsent(key, value);
        //INSTANCE.remove(ThreadLocalRandom.current().nextInt(INSTANCE.size()));
    }
}
