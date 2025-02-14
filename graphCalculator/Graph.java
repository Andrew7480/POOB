import java.util.ArrayList;

public class Graph {
    private ArrayList <String> vertex = new ArrayList <>();
    private ArrayList <ArrayList <String >> edges = new ArrayList <ArrayList<String>>();
    
    //no duplocados inicio fin
    //vector de parejas 
    //arcos de vertices que existen
    
    public Graph(String[] vertices, String[][] edges){
    }
    

    public boolean contains(String vertex){
        return false;
    }
    
    
    public Graph path(String start, String end){
        return null;
    }

    public Graph union (Graph g){
        return null;
    }

    
    public int vertices(){
        return 0;
    }
    
   
    public int edges(){
        return 0;
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
}
