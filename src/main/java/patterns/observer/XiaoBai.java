package patterns.observer;

public class XiaoBai implements Student {
    private String name = "小白";

    public XiaoBai() {
    }

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了老师打过来的电话，电话内容是：" + s);
    }

}
