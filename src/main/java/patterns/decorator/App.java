package patterns.decorator;

/**
 * https://mp.weixin.qq.com/s/uJNWcJ9IZNVrR6SqGpe3ig
 */
public class App {
    public static void main(String[] args) {
        //new FlyWrapper(new CommonMan()).action();
        new PowerWrapper(new FlyWrapper(new CommonMan())).action();
    }
}
