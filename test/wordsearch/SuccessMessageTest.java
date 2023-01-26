package wordsearch;

import command.SuccessMessage;
import java.awt.AWTException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class SuccessMessageTest {

    public SuccessMessageTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
      public void testShowMessage() throws AWTException {
        System.out.println("showMessage");
        BoardDisplay UI = new BoardDisplay(8,15);
        int level = 0;
        SuccessMessage instance = new SuccessMessage();
        instance.showMessage(UI, level);
    }

}