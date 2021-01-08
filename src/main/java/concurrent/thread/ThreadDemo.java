package concurrent.thread;

public class ThreadDemo {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
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

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //创建线程B
        Thread threadB = new Thread(() -> {
            try {
                //获取recourceB共享资源的监视器锁
                synchronized (resourceA) {
                    //唤醒线程
                    resourceA.notify();
                    System.out.println("threadB获取resourceA的锁");
                    System.out.println("threadB尝试获取resourceB的锁");
                    synchronized (resourceB) {
                        //获取recourceB共享资源的监视器锁
                        System.out.println("threadB获取resourceB的锁");
                        //threadB阻塞，并释放resourceA的锁
                        System.out.println("threadB释放resourceA的锁");
                        resourceA.wait();
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
