package patterns.singleton;

public class AppTest {
    public static void main(String[] args) {
        System.out.println(HungrySingleton.getInstance()== HungrySingleton.getInstance());

        System.out.println(StaticInnerSingleton.getInstance()== StaticInnerSingleton.getInstance());

        System.out.println(LazySingleton.getInstance()== LazySingleton.getInstance());

        System.out.println(DoubleCheckLazySingleton.getInstance()== DoubleCheckLazySingleton.getInstance());
    }
}