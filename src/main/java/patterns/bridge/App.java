package patterns.bridge;

public class App {
    public static void main(String[] args) {
        //圆形
        Shape square = new Circle(new White());
        square.draw();

        //长方形
        Shape rectange = new Rectangle(new Black());
        rectange.draw();
    }
}
