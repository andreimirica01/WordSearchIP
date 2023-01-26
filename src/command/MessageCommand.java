package command;

import wordsearch.BoardDisplay;

public interface MessageCommand {
    void showMessage(BoardDisplay UI, int level);
}