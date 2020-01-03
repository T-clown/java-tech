package patterns.decorator;

public class CommonMan implements Man {
    @Override
    public void action() {
        System.out.println("我会跑步...");
    }
}
