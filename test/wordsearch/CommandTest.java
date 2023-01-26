package wordsearch;



import command.Command;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CommandTest {

    public CommandTest() {
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
     * Test of execute method, of class Command.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        Command instance = new CommandImpl();
        instance.execute();
    }

    public class CommandImpl implements Command {

        public void execute() {
        }
    } 

}