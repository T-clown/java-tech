package patterns.strategy;

public class ByOneGetTwo implements  Promotion {
    @Override
    public void execute() {
        System.out.println("买一送一");
    }
}
