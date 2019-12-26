package patterns.observer;

public class XiaoLi implements Student {
    private String name = "小李";

    public XiaoLi() {
    }

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了老师打过来的电话，电话内容是：" + s);
    }

}
