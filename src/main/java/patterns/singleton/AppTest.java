package patterns.singleton;

public class AppTest {
    public static void main(String[] args) {
        System.out.println(SingletonOne.getInstance()==SingletonOne.getInstance());

        System.out.println(SingletonTwo.getInstance()==SingletonTwo.getInstance());

        System.out.println(SingletonThree.getInstance()==SingletonThree.getInstance());

        System.out.println(SingletonFour.getInstance()==SingletonFour.getInstance());
    }
}