package concurrent.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadLocalApp {
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalApp.class);

    /**
     * 维护线程私有变量
     */
    static class ThreadLocalUtil {
        private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "ThreadLocal初始值");

        private static String getValue() {
            return THREAD_LOCAL.get();
        }

        private static void setValue(String key) {
            THREAD_LOCAL.set(key);
        }

        private static void clear() {
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 维护线程私有变量
     * 子线程可以获取父线程的私有变量
     * 原理：每个线程都有一个InheritableThreadLocal类型的inheritableThreadLocals变量， 当创建子线程的时候，子线程会创建该类型
     * 变量，并把父进程的inheritableThreadLocals变量数据拷贝过来
     * 线程池下使用会有问题：线程会复用，所以多个任务会有相同的变量
     */
    static class InheritableThreadLocalUtil {
        //private static final ThreadLocal<String> INHERITABLE_THREAD_LOCAL = InheritableThreadLocal.withInitial(() -> "InheritableThreadLocal初始值");
        private static final ThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

        private static String getValue() {
            return INHERITABLE_THREAD_LOCAL.get();
        }

        private static void setValue(String value) {
            INHERITABLE_THREAD_LOCAL.set(value);
        }

        private static void clear() {
            INHERITABLE_THREAD_LOCAL.remove();
        }
    }

    /**
     * 维护线程私有变量
     * 子线程可以获取父线程的私有变量
     * 解决了InheritableThreadLocal线程池下使用的缺陷
     */
    static class TransmittableThreadLocalUtil {
        private static final ThreadLocal<String> TRANSMITTABLE_THREAD_LOCAL = new TransmittableThreadLocal<>();

        private static String getValue() {
            return TRANSMITTABLE_THREAD_LOCAL.get();
        }

        private static void setValue(String value) {
            TRANSMITTABLE_THREAD_LOCAL.set(value);
        }

        private static void clear() {
            TRANSMITTABLE_THREAD_LOCAL.remove();
        }
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
//        Thread t = new Thread(() -> test("abc", false));
//        t.start();
//        t.join();
//        System.out.println("--gc后--");
//        Thread t2 = new Thread(() -> test("def", true));
//        t2.start();
//        t2.join();
        //threadLocal();
        // inheritableThreadLocal();
    }


    private static void threadLocal() throws InterruptedException {
        logger.info("线程[{}]ThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), ThreadLocalUtil.getValue());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                logger.info("线程[{}]ThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), ThreadLocalUtil.getValue());
                ThreadLocalUtil.setValue(Thread.currentThread().getName());
                countDownLatch.countDown();
                logger.info("线程[{}]ThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), ThreadLocalUtil.getValue());
            }, "线程 - " + i);
            thread.start();
        }
        countDownLatch.await();
    }

    private static void inheritableThreadLocal() {
        logger.info("线程[{}]InheritableThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), InheritableThreadLocalUtil.getValue());
        InheritableThreadLocalUtil.setValue("父线程初始值");
        logger.info("线程[{}]InheritableThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), InheritableThreadLocalUtil.getValue());
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                //输出：父线程初始值
                logger.info("线程[{}]InheritableThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), InheritableThreadLocalUtil.getValue());
                //InheritableThreadLocalUtil.setValue(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //输出：父线程初始值
                logger.info("线程[{}]InheritableThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), InheritableThreadLocalUtil.getValue());
            }, "线程 - " + i);
            thread.start();
        }
        InheritableThreadLocalUtil.setValue("主线程重新赋值");
        //输出：主线程重新赋值
        logger.info("线程[{}]InheritableThreadLocalUtil获取的值:{}", Thread.currentThread().getName(), InheritableThreadLocalUtil.getValue());
    }
}