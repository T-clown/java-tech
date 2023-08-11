package concurrent.thread;

import java.sql.Driver;
import java.sql.DriverManager;

@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadApp {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader appClassLoader = ThreadApp.class.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        System.out.println(appClassLoader.getName());
        System.out.println(appClassLoader.getParent().getName());
        System.out.println(appClassLoader.getParent().getParent());
       // System.out.println(classLoader.getClass().getClassLoader());

    }
    public static void interupt(){
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断当前线程是否被中断，如果被中断会清楚中断标识
            System.out.println(Thread.interrupted());
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });

        t.start();
        //中断t线程，仅仅是设置中断标识，t线程还是会继续执行
        t.interrupt();
        //判断t线程是否被中断
        System.out.println(t.isInterrupted());
    }


    public static void synchronize() throws InterruptedException {
        //创建线程A
        Thread threadA = new Thread(() -> {
            try {
                //获取recourceA共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA获取resourceA的锁");
                    synchronized (resourceB) {
                        //获取recourceB共享资源的监视器锁
                        System.out.println("threadA获取resourceB的锁");
                        System.out.println("threadA释放resourceA的锁");
                        //threadA阻塞，并释放resourceA的锁
                        resourceA.wait();

                        System.out.println("threadA被唤醒");
                        resourceA.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "");

        //创建线程B
        Thread threadB = new Thread(() -> {
            try {
                //获取recourceB共享资源的监视器锁
                synchronized (resourceA) {
                    //唤醒线程
                    resourceA.notify();

                    resourceA.wait();
                    System.out.println("threadB获取resourceA的锁");
                    System.out.println("threadB尝试获取resourceB的锁");
                    synchronized (resourceB) {
                        //获取recourceB共享资源的监视器锁
                        System.out.println("threadB获取resourceB的锁");
                        //threadB阻塞，并释放resourceA的锁
                        System.out.println("threadB释放resourceA的锁");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
        //等待两个线程执行结束
        threadA.join();
        threadB.join();

        System.out.println("执行完毕");
    }
}
