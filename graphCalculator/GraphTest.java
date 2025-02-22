import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class GraphTest{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    
     @Test
    public void shouldCreateEmptyGraph(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(0, new Graph(vertices,edges).vertices());
        assertEquals(0, new Graph(vertices,edges).edges());
    }    
   
    @Test
    public void shouldCreateGraphs(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }    
    
    @Test
    public void shouldNotHaveDuplicateVerticesEdges(){
        String [] vertices ={"DDYA","MYSD","DOPO","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }
    
    @Test
    public void shouldConvertToString(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};  
        String data= "(DDYA, DOPO) (DDYA, MYSD)";
        assertEquals(data, new Graph(vertices,edges).toString());
    }

    @Test
    public void shouldValityEquality(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(new Graph(vertices,edges),new Graph(vertices,edges));
        
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"DDYA","DOPO"}};    
        String [] verticesB ={"Ddya","MYSD","DOPO","dopo"};
        String [][] edgesB = {{"DDYA","Mysd"},{"ddya","dopo"},{"DDya","doPo"}}; 
        
        assertEquals(new Graph(verticesA,edgesA),new Graph(verticesB,edgesB));
    }
    @Test
    public void shouldShowThePathOfTheGraph(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"MYSD","DOPO"}};  
        Graph g1 = new Graph(vertices, edges);
        assertEquals(new Graph(vertices,edges).path("DDYA", "DOPO"), g1.path("DDYA", "DOPO"));
    }
    @Test
    public void shouldMakeTheUnionOfGraphs(){
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"MYSD","DOPO"}};
        String [] verticesB = {"DDYA","MYSD","ABCD"};
        String [][] edgesB = {{"MYSD","ABCD"}};
        String [] verticesAB = {"DDYA","MYSD","DOPO","ABCD"};
        String [][] edgesAB = {{"DDYA","MYSD"},{"MYSD","DOPO"},{"MYSD","ABCD"}};
        assertEquals(new Graph(verticesAB, edgesAB), new Graph(verticesA, edgesA).union(new Graph(verticesB,edgesB)));
        
    }
    @Test
    public void shoudlMakeTheIntersectionOfGraphs(){
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"MYSD","DOPO"}};
        String [] verticesB = {"DDYA","MYSD","ABCD"};
        String [][] edgesB = {{"DDYA","MYSD"},{"MYSD","ABCD"}};
        String [] verticesAIntersectionB = {"DDYA","MYSD"};
        String [][] edgesAIntersectionB = {{"DDYA","MYSD"}};
        assertEquals(new Graph(verticesAIntersectionB, edgesAIntersectionB), new Graph(verticesA,edgesA).intersection(new Graph(verticesB, edgesB)));
    }
    @Test
    public void shouldMakeTheDifferenceBetweenGraphs(){
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"MYSD","DOPO"}};
        String [] verticesB = {"DDYA","MYSD","ABCD"};
        String [][] edgesB = {{"DDYA","MYSD"},{"MYSD","ABCD"}};
        String [] verticesADifferenceB = {"DOPO"};
        String [][] edgesADifferenceB = {};
        String [] verticesC ={"A","B","C","D"};
        String [][] edgesC = {{"A","B"},{"C","B"},{"C","D"}};
        String [] verticesD = {"C","D","E","F"};
        String [][] edgesD = {{"D","E"},{"C","D"}};
        String [] verticesCDifferenceD = {"A","B"};
        String [][] edgesCDifferenceD = {{"A","B"}};
        assertEquals(new Graph(verticesADifferenceB, edgesADifferenceB), new Graph(verticesA,edgesA).difference(new Graph(verticesB, edgesB)));
        assertEquals(new Graph(verticesCDifferenceD, edgesCDifferenceD), new Graph(verticesC,edgesC).difference(new Graph(verticesD, edgesD)));
    }
    @Test
    public void shouldMakeTheJoinOfGraphs(){
        String [] verticesA ={"A","B","C","D"};
        String [][] edgesA = {{"A","B"},{"C","B"},{"C","D"}};
        String [] verticesB = {"C","D","E","F"};
        String [][] edgesB = {{"D","E"},{"C","D"}};
        String [] verticesAJoinB = {"A","B","C","D","E","F"};
        String [][] edgesAJoinB = {{"A","B"},{"A","C"},{"A","D"},{"A","E"},{"A","F"},{"B","D"},{"B","E"},{"B","F"},{"C","B"},{"C","D"},{"C","E"},{"C","F"},{"D","E"},{"D","F"},{"E","F"}};
        assertEquals(new Graph(verticesAJoinB, edgesAJoinB), new Graph(verticesA,edgesA).join(new Graph(verticesB, edgesB)));
    }
    @Test
    public void shouldPass(){
        assertEquals(0,0);
    }
    @Test
    public void shouldFail(){
        fail();
    }
    @Test
    public void shouldErr(){
        assertEquals(0,10/0);
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
