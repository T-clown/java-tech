package patterns.strategy;

public class DefaultActivity implements Promotion<String, Boolean> {
    @Override
    public Boolean execute(String type) {
        System.out.println("无任何优惠");
        return true;
    }
}
