package command;

import wordsearch.BoardDisplay;

public class ShowMessageCommand implements Command{
    private MessageCommand mes;
    BoardDisplay UI;
    int level;
    
    public ShowMessageCommand(MessageCommand mess, BoardDisplay UI, int level) {
        this.mes = mess;
        this.UI = UI;
        this.level = level;
    }
    
    @Override
    public void execute() {
        mes.showMessage(UI, level);
    }
}