import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
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
        Graph g = new Graph();
        TreeMap<String,Graph> variablesP = new TreeMap<>();
        variablesP.put("grafo",g);
        GraphCalculator prueba = new GraphCalculator();
        prueba.create("grafo");
        assertEquals(variablesP.get("grafo"), prueba.getVariables().get("grafo"));
    }
    @Test
    public void shouldCreateGraphCalculatorAssign(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"},{"B","C"}};
        Graph g = new Graph(vertices,edges);
        TreeMap<String,Graph> variablesP = new TreeMap<>();
        variablesP.put("grafo",g);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.assign("grafo",vertices,edges);
        assertEquals(variablesP.get("grafo"), c.getVariables().get("grafo"));
    }
    @Test
    public void shouldAssignUnaryPlus(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C"};
        String[] vertices3 = {"B","C"};
        String[][] edges2 = {{"A","B"},{"B","C"}};
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // expected
        variablesP.put("grafo2",g1);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.assign("grafo", vertices, edges);
        c.assignUnary("grafo2", "grafo", '+', vertices3);
        assertEquals(variablesP.get("grafo2"),c.getVariables().get("grafo2"));
    }
    @Test
    public void shouldAssignUnaryMinus(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"},{"B","C"}};
        String[] vertices2 = {"A","B","C"};
        String[] vertices3 = {"B","C"};
        String[][] edges2 = {{"A","B"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafo2",g1);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.assign("grafo", vertices, edges);
        c.assignUnary("grafo2", "grafo", '-', vertices3);
        assertEquals(variablesP.get("grafo2"),c.getVariables().get("grafo2"));
    }
    @Test
    public void shouldAssignUnaryQuestionMark(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[] vertices3 = {"D","E","F"};
        String[][] edges2 = {{"A","B"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafo2",g1);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.assign("grafo", vertices, edges);
        c.assignUnary("grafo2", "grafo", '?', vertices3);
        assertEquals(variablesP.get("grafo2"),c.getVariables().get("grafo2"));
    }
    
    @Test
    public void shouldAssignBinaryUnion(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[][] edges2 = {{"A","B"},{"B","C"}};
        String[] vertices3 = {"A","B","C","D","E","F"};
        String[][] edges3 = {{"A","B"},{"B","C"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // was
        Graph g3 = new Graph(vertices3,edges3); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafofinal",g3);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.create("grafo3");
        c.assign("grafo2", vertices, edges);
        c.assign("grafo3",vertices2,edges2);
        c.assignBinary("grafo", "grafo2", 'u', "grafo3");
        assertEquals(variablesP.get("grafofinal"),c.getVariables().get("grafo"));
    }
    @Test
    public void shouldAssignBinaryIntersection(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[][] edges2 = {{"A","B"},{"B","C"}};
        String[] vertices3 = {"A","B","C"};
        String[][] edges3 = {{"A","B"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // was
        Graph g3 = new Graph(vertices3,edges3); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafofinal",g3);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.create("grafo3");
        c.assign("grafo2", vertices, edges);
        c.assign("grafo3",vertices2,edges2);
        c.assignBinary("grafo", "grafo2", 'i', "grafo3");
        assertEquals(variablesP.get("grafofinal"),c.getVariables().get("grafo"));
    }
    @Test
    public void shouldAssignBinaryDifference(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[][] edges2 = {{"A","B"},{"B","C"},{"E","F"}};
        String[] vertices3 = {};
        String[][] edges3 = {};
        String[] vertices4 = {"D","E","F"};
        String[][] edges4 = {{"E","F"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // was
        Graph g3 = new Graph(vertices3,edges3); // expected
        Graph g4 = new Graph(vertices4,edges4);
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafofinal",g3);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.create("grafo3");
        c.assign("grafo2", vertices, edges);
        c.assign("grafo3",vertices2,edges2);
        c.assignBinary("grafo", "grafo2", 'd', "grafo3");
        assertEquals(variablesP.get("grafofinal"),c.getVariables().get("grafo"));
        variablesP.put("grafofinal2",g4);
        c.create("grafo4");
        c.assignBinary("grafo4", "grafo3", 'd', "grafo2");
        assertEquals(variablesP.get("grafofinal2"),c.getVariables().get("grafo4"));
    }
    @Test
    public void shouldAssignBinaryJoin(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"B","A"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[][] edges2 = {{"D","A"},{"E","B"},{"F","A"}};
        String[] vertices3 = {"A","B","C","D","E","F"};
        String[][] edges3 = {{"A","C"},{"A","E"},{"B","A"},{"B","C"},{"B","D"},{"B","F"},{"C","D"},{"C","E"},{"C","F"},{"D","A"},{"D","E"},{"D","F"},{"E","B"},{"E","F"},{"F","A"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // was
        Graph g3 = new Graph(vertices3,edges3); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafofinal",g3);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.create("grafo3");
        c.assign("grafo2", vertices, edges);
        c.assign("grafo3",vertices2,edges2);
        c.assignBinary("grafo", "grafo2", 'j', "grafo3");
        assertEquals(variablesP.get("grafofinal"),c.getVariables().get("grafo")); 
    }
    @Test
    public void shouldToString(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.assign("grafo", vertices, edges);
        String data= "(DDYA, DOPO) (DDYA, MYSD)";
        assertEquals(data, c.toString("grafo"));
    }
    @Test
    public void ok(){
        String[] vertices = {"A","B","C"};
        String[][] edges = {{"A","B"}};
        String[] vertices2 = {"A","B","C","D","E","F"};
        String[][] edges2 = {{"A","B"},{"B","C"}};
        String[] vertices3 = {"A","B","C","D","E","F"};
        String[][] edges3 = {{"A","B"},{"B","C"}};
        Graph g = new Graph(vertices,edges); // was
        Graph g1 = new Graph(vertices2,edges2); // was
        Graph g3 = new Graph(vertices3,edges3); // expected
        TreeMap<String, Graph> variablesP = new TreeMap<>();
        variablesP.put("grafofinal",g3);
        GraphCalculator c = new GraphCalculator();
        c.create("grafo");
        c.create("grafo2");
        c.create("grafo3");
        c.assign("grafo2", vertices, edges);
        c.assign("grafo3",vertices2,edges2);
        c.assignBinary("grafo", "grafo2", 'x', "grafo3"); // el método ok va a ser falso ya que la operación x no esta definida
        assertFalse(c.ok());
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
