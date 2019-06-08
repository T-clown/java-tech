package concurrent.thread;

public class ThreadTwo implements Runnable{
    private int count;

    @Override
    public void run() {
        for (;count<2000;count++){
            System.out.println(Thread.currentThread().getName()+"\t"+count);
        }
    }

    public static void main(String[] args) {
        ThreadTwo two=new ThreadTwo();
        new Thread(two,"线程一").start();
        new Thread(two,"线程二").start();

    }

}