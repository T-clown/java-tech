package patterns.command;

public class Invoker {
    private Command command;

    //接收命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //执行命令
    public void executeCommand() {
        command.execute();
    }
}
