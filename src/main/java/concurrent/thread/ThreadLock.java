package concurrent.thread;

/**
 *
 */
public class ThreadLock {


    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            try {
                ThreadCount.inc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }, "threadOne");

        Thread threadTwo = new Thread(() -> {
            System.out.println(ThreadCount.getValue());
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }, "threadTwo");

        threadOne.start();
        threadTwo.start();

        System.out.println("main thread over");
    }

    public static class ThreadCount {

        private static long value;

        public static synchronized long getValue() {
            System.out.println(Thread.currentThread().getName() + "获得了getValue方法的锁");
            return value;
        }

        public static synchronized void inc() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "获得了inc方法的锁");
            Thread.sleep(1000 * 20);
            ++value;
        }
    }


}
