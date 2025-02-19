import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Graph {
    private ArrayList<String> vertexGraph;
    private ArrayList<ArrayList<String>> aristas;
    private ArrayList <String> pathing = new ArrayList<>();
    //no duplicados inicio fin -->bien
    //vector de parejas  // no meteria cosas que no son tuplas :"
    //arcos de vertices que existen --> bien
    
    public Graph(String [] vertices, String[][] edges){
        vertexGraph = new ArrayList<>(Arrays.asList(vertices));
        aristas = new ArrayList<>();
        
        
            
        for (String [] edge : edges){
            if (edge.length==2){
                aristas.add(new ArrayList<>(Arrays.asList(edge)));
            }
            
        }
        
        
        reorganizeEdges(aristas);
        reorganizeVertexGraph(vertexGraph);
        upperCase();
        removeDuplicates();
            
        
        
        //System.out.println(vertexGraph + ""+aristas);
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
        boolean[] visited = new boolean[vertexGraph.size()];
        
        for (int i = 0; i < vertexGraph.size(); i++){
            if (vertexGraph.get(i) == start){
                pos = i;
            }
        }
        
        visited[pos] = true;
        q.add(start);
        // q.poll retorna la cabeza
        while (!(q.isEmpty())){
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
        return aristas.size();
    }    
    
    
    public boolean equals(Graph g){
        reorganizeEdges(g.aristas);
        reorganizeVertexGraph(g.vertexGraph);
        
        if (g.vertices() != vertices() || g.edges() != edges() ){
            return false;
        }
        
        
        for (int i = 0;i< g.vertices();i++){
            if (!(vertexGraph.get(i).equals(g.vertexGraph.get(i) ))){
                    return false;
            }
        }
        
        for (int i = 0;i< g.edges();i++ ){
            if (!(aristas.get(i) .equals( g.aristas.get(i) ))){
                return false;
            }
        }
  
        return true;
    }
    
    public boolean equals(Object g){
        return equals((Graph)g);
    }
    
    //Only arcs in space-separated tuples. The vertices are capitalized. The edges must always be in alphabetical order.
    //For example, (A, B) (C, D) 
    @Override
    public String toString() {
        String texto = "";
        System.out.println(aristas); //{{},{},{}}
        for (ArrayList<String> fila : aristas){
            
            texto += "("+fila.get(0)+", "+fila.get(1)+") ";
        }
        
        return texto.toString().trim(); //elimina los espacios en blanco
    }
    
    public void adjacencyMatrix(ArrayList<ArrayList<String>>aristas){
        for (ArrayList<String> fila : aristas){
            for (String val : fila){
                System.out.println(val + " ");
            }
        System.out.println();
        }
    }
    
    private boolean verifyVertexExist(String a,String b){
        
        if (vertexGraph.contains(a) && vertexGraph.contains(b)){
            return true;
        }
        return false;
    }
    
    
    private void addEdge(String a , String b){
        if (!verifyVertexExist(a, b)){
            return; //jopciotn
        }
        
        ArrayList<String> aux = new ArrayList<>();
        aux.add(a);
        aux.add(b);
        aristas.add(aux);        
        reorganizeEdges(aristas);
        reorganizeVertexGraph(vertexGraph);
    }
    
    private void reorganizeVertexGraph (ArrayList<String> vertex){
        Collections.sort(vertex);
    }
    
    private void reorganizeEdges (ArrayList<ArrayList<String>>aristas){
        Collections.sort(aristas, new Comparator<ArrayList<String>>(){ 
            @Override
            public int compare(ArrayList<String> one, ArrayList<String> two) {
                //one.get(1).compareTo(two.get(1));
                //one.get(0).compareTo(two.get(0));
                //System.out.println(one.get(0).compareTo(two.get(0)));
                if (one.get(0).compareTo(two.get(0))==0){
                    return one.get(1).compareTo(two.get(1));
                }
                return one.get(0).compareTo(two.get(0));
            }
        }
        );
    
    }
    
    private void upperCase(){
        for (ArrayList<String> fila : aristas){
            fila.set(0,fila.get(0).toUpperCase());
            fila.set(1,fila.get(1).toUpperCase());
            
            //{{12,2},{1,2}}
        }
        for (int i = 0; i < vertexGraph.size(); i ++){
        vertexGraph.set(i, vertexGraph.get(i).toUpperCase());        
        }
    }
    
    
    private void removeDuplicates() {
        ArrayList<String> newList = new ArrayList<>(); 
        
        for (String element : vertexGraph) { 
            if (!newList.contains(element)) {   
                newList.add(element); 
            } 
        }
        vertexGraph = newList;
        
        
        ArrayList<ArrayList<String>> prueba = new ArrayList<ArrayList<String>>();
        
        for (ArrayList<String> element : aristas) { // 
            if (!prueba.contains(element)) {   
                prueba.add(element); 
            } 
        }
        aristas = prueba;

    }
    
}
