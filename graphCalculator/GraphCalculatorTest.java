import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

/**
 * The test class GraphCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphCalculatorTest
{
    /**
     * Default constructor for test class GraphCalculatorTest
     */
    public GraphCalculatorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    @Test
    public void shouldCreateGraphs(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};    
        //assertEquals(3, new GraphCalculator(vertices,edges).vertices());
        //assertEquals(2, new GraphCalculator(vertices,edges).edges());
    } 
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
