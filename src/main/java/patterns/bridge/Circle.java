package patterns.bridge;

public class Circle extends Shape{

    @Override
    public void draw() {
        color.paint("圆形");
    }
}
