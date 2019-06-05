package patterns.adapter;

public interface Action {
    void run();
}
aaaaaaaaaaaaaaaaaaaaaa
package patterns.adapter;

public class Airplane {

    public void fly() {
        System.out.println("I can fly");
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.adapter;

public class AirplaneAdapter implements Action {
    private Airplane airplane;

    public AirplaneAdapter(){
        airplane=new Airplane();
    }

    @Override
    public void run() {
        airplane.fly();
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.adapter;

public class App {
    public static void main(String[] args) {
        Person person=new Person(new AirplaneAdapter());
        person.run();
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.adapter;

public class Person {
    private Action action;

    public Person() {}

    public Person(Action action) {
        this.action = action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void run() {
        action.run();
    }
}
