package concurrent.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class CreateThread {
    private static final ThreadLocal<Integer> test = new ThreadLocal();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建并启动第一个线程
        try {
            ThreadOne threadOne = new ThreadOne();
            threadOne.start();
            threadOne.join();
        } catch (Exception e) {
           // e.printStackTrace();
        }

        //创建并启动第二个线程
        new Thread(new ThreadTwo(1, () -> test()), "线程一").start();
        //创建并启动第三个线程
        FutureTask<String> threadName = new FutureTask<>(new ThreadThree());
        //实质是以Callable对象来创建并启动线程
        new Thread(threadName, "有返回值的线程").start();
        System.out.println(threadName.get());
        //或者````````
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        });
        new Thread(task, "返回1的线程:").start();
        System.out.println(task.get());
    }

    public static class ThreadOne extends Thread {
        @Override
        public void run() {
            int a = 1 / 0;
            System.out.println("创建线程方式一：继承Thread");
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class ThreadTwo implements Runnable {

        private int a;
        private ExecuteService executeService;

        @Override
        public void run() {
            test.set(a);
            System.out.println("创建线程方式二：实现Runnable");
            executeService.execute();
        }
    }

    public static void test() {
        System.out.println(Thread.currentThread().getName() + "\t" + test.get());
    }

    public static class ThreadThree implements Callable<String> {
        @Override
        public String call() {
            System.out.println("创建线程方式三：实现Callable");
            return Thread.currentThread().getName();
        }
    }


}
