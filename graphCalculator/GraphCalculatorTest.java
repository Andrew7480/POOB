import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import java.util.TreeMap;

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
    public void shouldCreateGraphCalculator(){    
        assertEquals(0, new GraphCalculator().getVariables().size());
    }
    @Test
    public void shouldCreateANewVariableInTheTreeMap(){
        /*
        Graph g = new Graph();
        TreeMap<String,Graph> variablesP = new TreeMap<>();
        variablesP.put("hola",g);
        assertEquals(variablesP, new GraphCalculator().create("hola"));
        */
    }
    @Test
    public void shouldCreateGraphCalculatorAssign(){
        /*
        String[] vertices = {};
        String[][] edges = {};
        Graph g = new Graph();
        TreeMap<String,Graph> variablesP = new TreeMap<>();
        variablesP.put("hola",g);
        GraphCalculator g1 = new GraphCalculator();
        g1.assign("hola",vertices,edges);
        assertEquals(variablesP, g1.getVariables());
        */
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
