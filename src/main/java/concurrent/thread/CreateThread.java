package concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程
 */
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建并启动第一个线程
        new ThreadOne().start();
        //创建并启动第二个线程
        new Thread(new ThreadTwo(), "线程一").start();
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
            System.out.println("创建线程方式一：继承Thread");
        }
    }

    public static class ThreadTwo implements Runnable {
        @Override
        public void run() {
            System.out.println("创建线程方式二：实现Runnable");
        }
    }

    public static class ThreadThree implements Callable<String> {
        @Override
        public String call() {
            System.out.println("创建线程方式三：实现Callable");
            return Thread.currentThread().getName();
        }
    }


}
