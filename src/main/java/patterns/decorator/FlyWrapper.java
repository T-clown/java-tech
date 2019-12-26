package patterns.decorator;

public  class FlyWrapper extends Wrapper {

    public FlyWrapper(Man man) {
        super(man);
    }

    @Override
    public void action() {
        super.action();
        fly();
    }

    private void fly() {
        System.out.println("我会飞...");
    }
}
