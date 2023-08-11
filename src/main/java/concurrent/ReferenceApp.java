package concurrent;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceApp {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceApp.class);

    public static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "THREAD_LOCAL");
    //public ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "THREAD_LOCAL");

    public static final ReferenceApp reference = new ReferenceApp();

    static int value;

    static {
        value = 2;
    }

    private ReferenceApp referenceApp;

    public ReferenceApp() {
    }

    public ReferenceApp(ReferenceApp referenceApp) {
        this.referenceApp = referenceApp;
    }

    public static void main(String[] args) {
//        System.out.println(new ReferenceApp().THREAD_LOCAL);
//        System.out.println(new ReferenceApp().THREAD_LOCAL);
        //softReference();
        weakReference();
        // weakReference2();
        //phantomReference();
        ReferenceApp app = null;
        System.out.println(app.value);
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
    }

    /**
     * SoftReference<T> :T的软引用，不会影响T的回收
     * 常用于实现缓存，可防止内存溢出
     */
    private static void softReference() {
        //当被回收时，对象会被放到此队列中
        ReferenceQueue<ReferenceApp> queue = new ReferenceQueue<>();
        //软引用
        //SoftReference<ReferenceApp> softReference = new SoftReference<>(referenceApp);
        SoftReference<ReferenceApp> softReference = new SoftReference<>(new ReferenceApp(), queue);

        logger.info("软引用:{},软引用对象:{},队列元素:{}", softReference, softReference.get(), queue.poll());
        //主动让出发GC
        System.gc();
        //给GC留点时间，保证GC执行完成
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReferenceApp softVal = softReference.get();
        if (softVal != null) {
            // 对象仍然存在
            logger.info("软引用:{},软引用对象仍然存在:{}", softReference, softReference.get());
        } else {
            // 对象已被回收
            Reference<? extends ReferenceApp> ref = queue.poll();
            logger.info("软引用:{},软引用对象已被回收:{}", ref, ref.get());
        }
    }

    /**
     *
     */
    private static void weakReference2() {

        //弱引用
        WeakReference<ReferenceApp> weakReference = new WeakReference<>(new ReferenceApp());
        //logger.info("弱引用:{},弱引用对象:{},threadlocal:{}", weakReference, weakReference.get(), weakReference.get().THREAD_LOCAL);
        logger.info("弱引用:{},弱引用对象:{}", weakReference, weakReference.get());
        //主动让出发GC
        System.gc();
        //给GC留点时间，保证GC执行完成
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //在使用弱引用时，需要在调用get()方法之后检查对象是否存在，并在必要时重新创建它
        ReferenceApp weakVal = weakReference.get();
        if (weakVal != null) {
            // 对象仍然存在
            logger.info("弱引用:{},弱引用对象:{}", weakReference, weakReference.get());
            //logger.info("弱引用:{},弱引用对象:{},threadlocal:{}", weakReference, weakReference.get(), weakReference.get().THREAD_LOCAL);
        } else {
            // 对象已被回收
            logger.info("弱引用:{},弱引用对象:{}", weakReference, weakReference.get());
            //logger.info("弱引用:{},弱引用对象:{},threadlocal:{}", weakReference, weakReference.get(), new ReferenceApp().THREAD_LOCAL);
        }


    }

    /**
     * WeakReference<T> :T的弱引用，不会影响T的回收
     */
    private static void weakReference() {
        //弱引用
        //WeakReference<ReferenceApp> weakReference = new WeakReference<>(referenceApp);

        //当被回收时，对象会被放到此队列中
        ReferenceQueue<ReferenceApp> queue = new ReferenceQueue<>();
        WeakReference<ReferenceApp> weakReference = new WeakReference<>(new ReferenceApp(), queue);
        logger.info("弱引用:{},弱引用对象:{},队列元素:{}", weakReference, weakReference.get(), queue.poll());
        //主动让出发GC
        System.gc();
        //给GC留点时间，保证GC执行完成
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //在使用弱引用时，需要在调用get()方法之后检查对象是否存在，并在必要时重新创建它
        ReferenceApp weakVal = weakReference.get();
        if (weakVal != null) {
            // 对象仍然存在
            logger.info("弱引用对象仍然存在:{}", weakVal);
        } else {
            // 对象已被回收
            Reference<? extends ReferenceApp> ref = queue.poll();
            logger.info("队列弱引用元素:{},弱引用对象已被回收:{}", ref, ref.get());
        }


    }

    /**
     * 这里把ReferenceApp的引用弄成弱引用，不会影响ReferenceApp的回收
     * 但是如果ReferenceApp不会被回收，则能一直获取值
     * 当一个类继承自WeakReference，它表示这个类是一个弱引用，持有的ReferenceApp引用是一个弱引用，不会影响ReferenceApp的回收
     */
    @Getter
    @Setter
    static class Entry extends WeakReference<ReferenceApp> {
        private Object value;

        public Entry(ReferenceApp key, Object value) {
            super(key);
            this.value = value;
        }

    }

    private static void phantomReference() {
        //虚引用
        /**
         * 在GC时，如果该对象被标记为可回收对象，则会将该虚引用对象放入ReferenceQueue队列中。
         * 在使用PhantomReference时，通常需要在单独的线程中定期检查ReferenceQueue队列中的虚引用对象，因为GC只会在内存不足时才会触发，而此时可能会导致内存泄漏。
         * PhantomReference通常用于一些需要在对象被回收前进行特定操作的场景，例如清除一些外部资源的操作
         */
        ReferenceQueue<ReferenceApp> queue = new ReferenceQueue<>();
        //对象直接被加到queue里面去了
        PhantomReference<ReferenceApp> phantomReference = new PhantomReference<>(new ReferenceApp(), queue);
        logger.info("虚引用:{}虚引用对象:{},队列元素:{},{}", phantomReference, phantomReference.get(), queue.poll(), queue.poll().get());

        //主动让出发GC
        System.gc();
        //给GC留点时间，保证GC执行完成
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reference<? extends ReferenceApp> ref = queue.poll();
        if (ref != null) {
            // 进行必要的处理操作
            logger.info("虚引用:{},虚引用对象已被回收:{}", ref, ref.get());
        }
    }
}
