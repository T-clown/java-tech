package concurrent.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadOne extends Thread{
    private static AtomicInteger count=new AtomicInteger();

    @Override
    public void run(){
        Thread currentThread=  Thread.currentThread();
        currentThread.setName("线程\t" +count.incrementAndGet());
        System.out.println(currentThread.getName());
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            //调用Thread类的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i==5){
                //创建并启动第一个线程
                new ThreadOne().start();
                //创建并启动第二个线程
                new ThreadOne().start();
            }
        }
    }
}