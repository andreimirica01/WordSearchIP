package command;

public class MessageInvoker {
    public Command command;

    public MessageInvoker(Command c){
        this.command = c;
    }

    public void execute(){
        this.command.execute();
    }
}