package arithmetic.exercise;

public class AliExercise {

    public static void main(String[] args) {
        AliExercise aliExercise = new AliExercise();
        aliExercise.runThread();
    }

    volatile int flag = 0;

    public void runThread() {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        t1.start();
        t2.start();
    }

    public class Thread1 implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (i <= 99) {
                if (flag == 0) {
                    System.out.println("t1=" + i + "flag=" + flag);
                    i += 2;
                    flag = 1;
                }
            }
        }

    }

    public class Thread2 implements Runnable {

        @Override
        public void run() {
            int i = 1;
            while (i <= 99) {
                if (flag == 1) {
                    System.out.println("t2=" + i + "flag=" + flag);
                    i += 2;
                    flag = 0;
                }
            }
        }
    }
}
