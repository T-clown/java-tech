package concurrent.thread;

public class ThreadTwo implements Runnable {
    private volatile String lastName = "线程二";

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int count = "线程一".equals(threadName) ? 1 : 2;
        for (; count <= 100; count += 2) {
            if (lastName.equals(threadName)) {
                Thread.yield();
            }
            System.out.println(threadName + ":" + count);
            lastName = threadName;
            Thread.yield();;
        }
    }

    public static void main(String[] args) {
        ThreadTwo two = new ThreadTwo();
        new Thread(two, "线程一").start();
        new Thread(two, "线程二").start();

    }

}