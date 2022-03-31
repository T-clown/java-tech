package patterns.visitor;

public class App {
    public static void main(String[] args) {
        Student student = new Student("张三", "", "");
        student.accept(new Parent());
        student.accept(new Principal());
    }
}
