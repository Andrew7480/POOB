import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    //private ArrayList<String> vertexGraph;
    //private ArrayList<ArrayList<String>> aristas;
    //private ArrayList <String> pathing = new ArrayList<>();
    //no duplocados inicio fin
    //vector de parejas 
    //arcos de vertices que existen
    private String[] vertex1;
    private String[][] aristas;
    private ArrayList<Integer>[] adjList;
    
    public Graph(String [] vertices, String[][] edges){
        /*
        vertexGraph = new ArrayList<>(Arrays.asList(vertices));
        aristas = new ArrayList<>();
        for (String [] edge : edges){
            aristas.add(new ArrayList<>(Arrays.asList(edge)));
        }
        */
       vertex1 = vertices;
       aristas = edges;    
    }
    /**
     * Determine if a vertex is in the graph
       */
    public boolean contains(String vertex){
        for (int i = 0; i < vertex1.length; i++){
            if (vertex1[i] == vertex){
                return true;
            }
        }
        return false;
    }
    /**
     * Determine path of a graph given a start to end
       */
    
    public Graph path(String start, String end){
        boolean [] isVisited = new boolean[vertex1.length];
        return null;
    }

    public Graph union (Graph g){
        return null;
    }
    /**
     * Calculates the amount of vertices in a graph
       */
    
    public int vertices(){
        return vertex1.length;
    }
    
    /**
     * Calculates the amount of edges in a graph
       */   
    public int edges(){
        return aristas[0].length;
    }    
    
    
    public boolean equals(Graph g){
        return false;
    }
    
    public boolean equals(Object g){
        return equals((Graph)g);
    }
    
    //Only arcs in space-separated tuples. The vertices are capitalized. The edges must always be in alphabetical order.
    //For example, (A, B) (C, D)
    @Override
    public String toString() {
      return "";
    }
    private void initAdjList(){
        adjList = new ArrayList[vertex1.length];
        for (int i = 0; i < vertex1.length; i++){
            adjList[i] = new ArrayList<>();
        }
    }
    public void addEdge(String a, String b){
        //adjList[].add(b);
    }
}
