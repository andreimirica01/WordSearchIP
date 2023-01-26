package command;

import javax.swing.JOptionPane;
import wordsearch.BoardDisplay;

public class SuccessMessage implements MessageCommand{

    @Override
    public void showMessage(BoardDisplay UI, int level) {
        
        JOptionPane.showMessageDialog(null, "Felicitari ai castigat");
    }
    
}