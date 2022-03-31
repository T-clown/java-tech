package patterns.bridge;

public class Rectangle extends Shape{
    public Rectangle(Color color){
        super(color);
    }

    @Override
    public void draw() {
        color.paint("长方形");
    }

}