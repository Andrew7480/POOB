import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

/** GraphCalculator.java
 * 
 * @author ESCUELA 2025-01
 */
    
public class GraphCalculator{
    private TreeMap<String,Graph> variables = new TreeMap<String, Graph>();
    private boolean lastActionWasSuccess = true;
    public GraphCalculator(){
    }
    /**
     * Create a new variable 
     * @param String nombre
       */
    //Create a new variable
    public void create(String nombre){
        if (nombre == null){
            lastActionWasSuccess = false;
        }
        else{
            variables.put(nombre, new Graph());
            lastActionWasSuccess = true;
        }
    }
     
    //Assign a graph to an existing variable
    //a := graph
    /**
     * Assign a graph to an existing variable
     * @param String graph
     * @param String[] vertices
     * @param String[][] edges
       */
    public void assign(String graph, String[] vertices, String[][] edges){
        Graph g = new Graph(vertices, edges);
        if (variables.containsKey(graph)){
            variables.put(graph, g);
            lastActionWasSuccess = true;
        }
        else{
            lastActionWasSuccess = false;
        }
    }    
    //Assigns the value of a binary operation to a variable
    // a = b op v*
    //The operator characters are: '+' adding a edge between two vertices, '-' removing a edge between two vertex
    // '?' checking if a graph contains the given vertices
    // 'p' return the graph with a path that passes through all the vertices in the indicated order
    /**
     * Assigns the values of a binary operation to a variable
     * @param String a
     * @param String b
     * @param char op
     * @param String[] vertices
       */
    public void assignUnary(String a, String b, char op, String[] vertices){ // ["A","B"] ?     // ["A","C","D","F"]
        Graph g1 = new Graph();
        g1 = variables.get(b);
        if(vertices.length == 2){
            Graph clone = new Graph(g1);
            if (op == '+'){
                clone.addEdge(vertices[0], vertices[1]);
                assign(a, clone.getVertexGraph(), clone.getAristas());
                lastActionWasSuccess = true;
            }
            if (op == '-'){
                clone.removeEdge(vertices[0], vertices[1]);
                assign(a, clone.getVertexGraph(), clone.getAristas());
                lastActionWasSuccess = true;
            }
        }
        if (op == '?'){
            Graph clone = new Graph(g1);
            clone.addVertices(vertices);
            assign(a, clone.getVertexGraph(), clone.getAristas());
            lastActionWasSuccess = true;
        }
        if (op == 'p'){
            Graph clone = new Graph(g1);
            int lengthV = vertices.length;
            clone.path(vertices[0], vertices[lengthV - 1]);
            assign(a, clone.getVertexGraph(), clone.getAristas());
            lastActionWasSuccess = true;
        }
        
        else{
            lastActionWasSuccess = false;
        
        }
    }
      
    
    //Assigns the value of a binary operation to a variable
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference, 'j' join
    /**
     * Assigns the value of a binary operation to a variable
     * @param String a
     * @param String b
     * @param char op
     * @param String c
       */
    public void assignBinary(String a, String b, char op, String c){
        Graph g = new Graph();
        Graph g1 = new Graph();
        Graph g3 = new Graph();
        g = variables.get(b);
        g1 = variables.get(c);
        if (op == 'u'){
            g3 = g.union(g1);
            assign(a, g3.getVertexGraph(), g3.getAristas());
            lastActionWasSuccess = true;
        }
        if (op == 'i'){
            g3 = g.intersection(g1);
            assign(a, g3.getVertexGraph(), g3.getAristas());
            lastActionWasSuccess = true;
        }
        if (op == 'd'){
            g3 = g.difference(g1);
            assign(a, g3.getVertexGraph(), g3.getAristas());
            lastActionWasSuccess = true;
        }
        if (op == 'j'){
            g3 = g.join(g1);
            assign(a, g3.getVertexGraph(), g3.getAristas());
            lastActionWasSuccess = true;
        }
        else{
            lastActionWasSuccess = false;
        }
    }
    public void assignComplement(String a, String b){
        Graph g3 = new Graph();
        Graph g1 = new Graph();
        g1 = variables.get(a);
        g3 = g1.complement();
        assign(b, g3.getVertexGraph(), g3.getAristas());
        lastActionWasSuccess = true;
    }
    //Returns the graph with the edges in uppercase in alphabetical order.
    /**
     * Returns the graph with the edges in uppercase in alpahbetical order
     * @param String graph
       */
    public String toString(String graph){
        return variables.get(graph).toString();
    }
    //If the last operation was successfully completed
    /**
     * If the last operation was successfully completed
       */
    public boolean ok(){
        return lastActionWasSuccess;
    }
    public TreeMap<String,Graph> getVariables(){
        return variables;
    }
}



