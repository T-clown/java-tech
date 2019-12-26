package patterns.decorator;

public abstract class Wrapper implements Man{
    private Man man;
    public Wrapper(Man man) {
        this.man = man;
    }
    @Override
    public void action() {
        man.action();
    }
}
