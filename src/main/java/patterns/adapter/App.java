package patterns.adapter;

public class App {
    public static void main(String[] args) {
        Person person=new Person(new AirplaneAdapter());
        person.run();
    }
}