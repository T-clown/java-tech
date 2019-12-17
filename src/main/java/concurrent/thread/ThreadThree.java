package concurrent.thread;

import java.util.concurrent.FutureTask;

public class ThreadThree {

    public static void main(String[] args) {
        //创建Callable对象
        // ThridThread rt=new ThridThread();
        //先使用Lambda表达式创建Callable<Integer>对象
        //使用FutureTask来包装Callable对象
        FutureTask<Integer> task=new FutureTask<>(()->{
            int i=0;
            for(;i<100;i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            //call()方法的返回值
            return i;
        });
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"循环变量i的值: "+i);
            if(i==20){
                //实质是以Callable对象来创建并启动线程
                new Thread(task,"有返回值的线程:").start();
            }
        }
        try{
            //获取线程的返回值
            System.out.println("子线程的返回值:"+task.get());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}