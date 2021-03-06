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
