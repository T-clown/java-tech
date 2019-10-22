package patterns.strategy;

public class ReturnCash implements Promotion {
    @Override
    public void execute() {
        System.out.println("返现5元");
    }
}
