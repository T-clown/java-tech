package patterns.strategy;

public class DefaultActivity implements Promotion {
    @Override
    public void execute() {
        System.out.println("无任何优惠");
    }
}
