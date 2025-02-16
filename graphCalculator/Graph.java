import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexGraph;
    private ArrayList<ArrayList<String>> aristas;
    private ArrayList <String> pathing = new ArrayList<>();
    //no duplocados inicio fin
    //vector de parejas 
    //arcos de vertices que existen
    
    public Graph(String [] vertices, String[][] edges){
        vertexGraph = new ArrayList<>(Arrays.asList(vertices));
        aristas = new ArrayList<>();
        for (String [] edge : edges){
            aristas.add(new ArrayList<>(Arrays.asList(edge)));
        }
    
    }
    /**
     * Determine if a vertex is in the graph
       */
    public boolean contains(String vertex){
        for (int i = 0; i < vertexGraph.size(); i++){
            if (vertexGraph.get(i) == vertex){
                return true;
            }
        }
        return false;
    }
    /**
     * Determine path of a graph given a start to end
       */
    
    public Graph path(String start, String end){
        // Aplicamos BFS
        int pos = 0;
        Queue<String> q = new LinkedList();
        boolean[] visited = new boolean[aristas.size()];
        for (int i = 0; i < vertexGraph.size(); i++){
            if (vertexGraph.get(i) == start){
                pos = i;
            }
        }
        visited[pos] = true;
        q.add(start);
        // q.poll retorna la cabeza
        while (!q.isEmpty()){
            String current = q.poll();
            pathing.add(current);
            for (String x : aristas.get(aristas.indexOf(current))){
                if(!visited[aristas.indexOf(x)]){
                    visited[aristas.indexOf(x)] = true;
                    q.add(x);
                }
            }
        }
        System.out.println(pathing);
        return null;
    }

    public Graph union (Graph g){
        return null;
    }
    /**
     * Calculates the amount of vertices in a graph
       */
    
    public int vertices(){
        return vertexGraph.size();
    }
    
    /**
     * Calculates the amount of edges in a graph
       */   
    public int edges(){
        return aristas.get(0).size();
    }    
    
    
    public boolean equals(Graph g){
        return false;
    }
    
    public boolean equals(Object g){
        return equals((Graph)g);
    }
    
    //Only arcs in space-separated tuples. The vertices are capitalized. The edges must always be in alphabetical order.
    //For example, (A, B) (C, D) 
    public String toString() {
      return "";
    }
    public void adjacencyMatrix(ArrayList<ArrayList<String>>aristas){
        for (ArrayList<String> fila : aristas){
            for (String val : fila){
                System.out.println(val + " ");
            }
        System.out.println();
        }
    }
    private void addEdge(ArrayList<ArrayList<String>> aristas, int i, int j){
        aristas.get(i).set(j, "1");
    }
}
