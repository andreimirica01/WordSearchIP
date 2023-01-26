/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearch;

import command.ShowMessageCommand;
import command.MessageCommand;
import java.awt.AWTException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrei
 */
public class ShowMessageCommandTest {
    
    public ShowMessageCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ShowMessageCommand.
     */
    @Test
    public void testExecute() throws AWTException {
        System.out.println("execute");
        ShowMessageCommand instance = new ShowMessageCommand(new MessageCommand() {
            @Override
            public void showMessage(BoardDisplay UI, int level) {
                
            }
        }, new BoardDisplay(8, 15), 0);
        instance.execute();
    }
    
}