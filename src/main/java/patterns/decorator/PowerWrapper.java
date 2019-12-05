package patterns.decorator;

public class PowerWrapper extends Wrapper {
    public PowerWrapper(Man man) {
        super(man);
    }
    @Override
    public void action() {
        super.action();
        power();
    }
    private void power(){
        System.out.println("无穷力量!");
    }
}
