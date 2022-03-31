package patterns.command;

public abstract class Command {
    protected Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    //执行命令的方法
    abstract public void execute();

}
