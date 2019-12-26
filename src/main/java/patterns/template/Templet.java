package patterns.template;

/**
 * 模板设计模式就是将许多公用的常用的代码封装成一个模板，我们只需要实现不同的业务需求的代码，
 * 然后和模板组合在一起，那么就得到完整的逻辑。
 *
 * 在我们的日常开发中，常用的模板模式有两种实现方式：继承和接口回调
 *
 * 需要使用模板的类只需要继承这个模板类，并实现那个抽象方法，那么在调用doTemplet
 */
public abstract class Templet {
    public void doTemplet(){
        System.out.println("固定代码片段");

        //业务逻辑代码
        doSomething();

        System.out.println("固定代码片段");
    }

    public abstract void doSomething();
}
