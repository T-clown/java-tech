package patterns.command;

/**
 * https://mp.weixin.qq.com/s/1OSBJvivw_6KU88ITvZE1A
 */
public class CommandClient {
    public static void main(String[] args) {
        //定义接收者
        Receiver receiver = new Receiver();
        //定义一个发送给接收者的命令
        Command command = new ConcreteCommand(receiver);
        //声明调用者
        Invoker invoker = new Invoker();

        //把命令交给调用者执行
        invoker.setCommand(command);
        //执行命令
        invoker.executeCommand();
    }

}
