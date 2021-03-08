package concurrent;

public class SynchronizedApp {

    public synchronized void eat1(){

    }

    public static synchronized void eat2(){

    }



    public void eat3(){
        synchronized(this){

        }
    }

    public void eat4(){
        synchronized(SynchronizedApp.class){

        }
    }


}
