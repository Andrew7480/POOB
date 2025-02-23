import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private ArrayList<String> vertexGraph;
    private ArrayList<ArrayList<String>> aristas;
    private ArrayList<ArrayList<String>> adjacencyMatrix = new ArrayList<ArrayList<String>>();
    //no duplicados inicio fin -->bien
    //vector de parejas  // no meteria cosas que no son tuplas :"
    //arcos de vertices que existen --> bien
    public Graph(Graph g){
        vertexGraph = g.vertexGraph;
        aristas = g.aristas;
        reorganizeEdges(aristas);
        reorganizeVertexGraph(vertexGraph);
        upperCase();
        removeDuplicates();
        createAdjacencyMatrix();
    }
    public Graph(){
    }
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
        createAdjacencyMatrix();
    }
    
    public Graph(ArrayList<String> vertices, ArrayList<ArrayList<String>> edges){
        vertexGraph = vertices;
        aristas = edges;
        reorganizeEdges(aristas);
        reorganizeVertexGraph(vertexGraph);
        upperCase();
        removeDuplicates();
        createAdjacencyMatrix();
        
        
        
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
     * @param String start
     * @param String end
       */
    public Graph path(String start, String end){
        boolean[] visited = new boolean[vertexGraph.size()];
        Arrays.fill(visited, false);
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int startIdx = vertexGraph.indexOf(start);
        int endIdx = vertexGraph.indexOf(end);
        visited[startIdx] = true;
        queue.add(startIdx);
        parentMap.put(startIdx, -1);
    
        boolean found = false;
    
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == endIdx) {
                found = true;
                break;
            }
    
            for (int neighbor = 0; neighbor < vertexGraph.size(); neighbor++) {
                if (adjacencyMatrix.get(current).get(neighbor).equals("1") && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        if (!found) {
            return null;
        }
        List<Integer> pathNodes = new ArrayList<>();
        for (int at = endIdx; at != -1; at = parentMap.get(at)) {
            pathNodes.add(at);
        }
        Collections.reverse(pathNodes);   //idea sacada de internet y ha sido acomodada para que funcione a esta clase
        ArrayList<String> auxiliarVertices= new ArrayList <String>(); // [0,1,3,2]  -> [A,B,D,C]
        for(int a: pathNodes){
            auxiliarVertices.add(vertexGraph.get(a));
        }
        ArrayList<ArrayList<String>> aristasGrafo= new ArrayList<ArrayList<String>>();
        for (int i = 0; i < auxiliarVertices.size()-1; i++){
            ArrayList arista = new ArrayList<String>();
            arista.add(auxiliarVertices.get(i));
            arista.add(auxiliarVertices.get(i+1));
            aristasGrafo.add(arista);
            }
        Graph grafo2 = new Graph(auxiliarVertices,aristasGrafo);
        return grafo2;
    }
    
    /**
     * Calculate the union of graphs
     * @param Graph g
       */
    public Graph union (Graph g){
        ArrayList<String> verticesA = new ArrayList<>();
        for (int i = 0; i < vertexGraph.size(); i++){
            verticesA.add(vertexGraph.get(i));
        }
        for (int j = 0; j < g.vertexGraph.size(); j++){
            if (verticesA.contains(g.vertexGraph.get(j))){
                continue;
            }
            verticesA.add(g.vertexGraph.get(j));
        }
        ArrayList<ArrayList<String>> aristasA = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> a : aristas){
            aristasA.add(a);
        }
        for (ArrayList<String> b: g.aristas){
            if (aristasA.contains(b)){
                continue;
            }
            aristasA.add(b);
        }
        Graph gUnion = new Graph(verticesA, aristasA);
        return gUnion;
    }
    /**
     * Calculate the intersection of graphs
     * @param Graph g
       */
    public Graph intersection(Graph g){
        ArrayList<String> cloone = (ArrayList<String>) vertexGraph.clone();
        cloone.retainAll(g.vertexGraph); // Obtener la intersección entre listas.
        ArrayList<ArrayList<String>> clooneEdges = (ArrayList<ArrayList<String>>) aristas.clone();
        clooneEdges.retainAll(g.aristas);
        Graph gIntersection = new Graph(cloone, clooneEdges);
        return gIntersection;
    }
    /**
     * Calculate the difference of graphs
     * @param Graph g
       */
    public Graph difference(Graph g){
        Graph gDifference;
        ArrayList<String> cloone = (ArrayList<String>) vertexGraph.clone();
        ArrayList<ArrayList<String>> clooneEdges = (ArrayList<ArrayList<String>>) aristas.clone();
        cloone.removeAll(g.vertexGraph);
        if (cloone.size() == 1){
            clooneEdges.clear();
            gDifference = new Graph(cloone, clooneEdges);
        }
        else{
            clooneEdges.removeAll(g.aristas);
            clooneEdges = removeEdgesRelational(cloone, clooneEdges);
            gDifference = new Graph(cloone, clooneEdges);
        }
        return gDifference;
    }
    /**
     * Remove edges that already exits 
     * @param ArrayList<String> clone
     * @param ArrayList<ArrayList<String>> cloneEdges
     */
    private ArrayList<ArrayList<String>> removeEdgesRelational(ArrayList<String> cloone, ArrayList<ArrayList<String>> clooneEdges){
        for (int i = 0; i < clooneEdges.size(); i++){
            if(!cloone.contains(clooneEdges.get(i).get(0)) || !cloone.contains(clooneEdges.get(i).get(1))){
                clooneEdges.remove(i);
            }
        }
        return clooneEdges;
    }
    /**
     * Calculate the join of graphs
     * @param Graph g
       */
    public Graph join(Graph g){
        Graph grafo = new Graph(vertexGraph,aristas).union(g);
        for (int i = 0; i < grafo.vertexGraph.size(); i++){
            for (int j = i + 1; j < grafo.vertexGraph.size(); j ++){
                ArrayList<String> a = new ArrayList<>();
                a.add(grafo.vertexGraph.get(j));
                a.add(grafo.vertexGraph.get(i));
                if(!grafo.aristas.contains(a)){
                    grafo.addEdge(grafo.vertexGraph.get(i), grafo.vertexGraph.get(j));
                }
            }
        }
        grafo = removeDuplicateGraph2(grafo);
        return grafo;
    }
    /**
     * Calculate the complement of the graph
       */
    public Graph complement(){
        ArrayList<ArrayList<String>> aristasC = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < vertexGraph.size(); i++){
            for (int j = i + 1; j < vertexGraph.size(); j++){
                ArrayList<String> a = new ArrayList<>(); // A - B - B - C B - D A - C A - D
                a.add(vertexGraph.get(i));
                a.add(vertexGraph.get(j));
                if (!aristas.contains(a)){
                    aristasC.add(a);
                }
            }
        }
        Graph grafo = new Graph(vertexGraph,aristasC);
        return grafo;
    }
    /**
     * Calculate the complete of the graph
       */
    public Graph complete(){
        
        Graph grafo = new Graph(vertexGraph,aristas);
        
        for (int i = 0; i < vertexGraph.size(); i++){
            for (int j = i + 1; j < vertexGraph.size(); j ++){ 
                ArrayList<String> a = new ArrayList<>();
                a.add(vertexGraph.get(i));
                a.add(vertexGraph.get(j));
                if(!grafo.aristas.contains(a)){
                    grafo.addEdge(vertexGraph.get(i), vertexGraph.get(j));
                }
            }
        }
        grafo = removeDuplicateGraph2(grafo);
        
        return grafo;
    }
    /**
     * remove duplicates from a Graph
     * @param Graph g
     */
    private Graph removeDuplicateGraph2(Graph g){
        ArrayList<ArrayList<String>> prueba = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> element : g.aristas) { // 
            if (!prueba.contains(element)) {   
                prueba.add(element); 
            }
        }
        g.aristas = prueba;
        return g;
    }
    /**
     * Calculates the amount of vertices in a graph
    */
    public int vertices(){
        if (vertexGraph == null){
            return 0;
        }
        return vertexGraph.size();
    }
    
    /**
     * Calculates the amount of edges in a graph
    */   
    public int edges(){
        if (aristas == null){
            return 0;
        }
        return aristas.size();
    }    
    
    /**
     * determinate if two graph are equal
     */
    public boolean equals(Graph g){
        if (g.vertices() != vertices() || g.edges() != edges() ){
            return false;
        }
        reorganizeVertexGraph(g.vertexGraph);
        reorganizeEdges(g.aristas);
        
        for (int i = 0;i< g.vertices();i++){
            if (!(vertexGraph.get(i).equals(g.vertexGraph.get(i) ))){
                    return false;
            }
        }
        
        for (int i = 0;i< g.edges();i++ ){
            if (!(aristas.get(i).equals( g.aristas.get(i) ))){
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
        for (ArrayList<String> fila : aristas){
            texto += "("+fila.get(0)+", "+fila.get(1)+") "; //
        }
        
        return texto.toString().trim(); //elimina los espacios en blanco
    }
    /**
     * get the path in String
     */
    public String toStringToPath() {
        String texto = "";
        for (ArrayList<String> fila : aristas){
            texto += fila.get(0)+" "; //
        }
        texto += aristas.get(edges()-1).get(1);
        
        return texto.toString().trim(); //elimina los espacios en blanco
    }
    //create asjacencyMAtrix
    private void createAdjacencyMatrix(){
        adjacencyMatrix.clear();
        for (int i = 0; i < vertexGraph.size(); i++){
            ArrayList<String> matriz = new ArrayList<>();
            for (int j = 0; j < vertexGraph.size(); j++){
                matriz.add("0");
            }
            adjacencyMatrix.add(matriz);
        }
       startAdjacencyMatrix();
    }
    //start the adjacencyMatrix
    private void startAdjacencyMatrix(){
        int indice = 0;
        int indice1 = 0;
        for (ArrayList<String> c : aristas){
            for (int i = 0; i < vertexGraph.size(); i++){
                if (vertexGraph.get(i).equals(c.get(0))){
                    indice = i;
                }
                if (vertexGraph.get(i).equals(c.get(1))){
                    indice1 = i;
                }
            }
            adjacencyMatrix.get(indice).set(indice1, "1");
            indice = 0;
            indice1 = 0;
        }

    }
    /**
     * Checking if vertex exist
     * @param String a
     * @param String b
       */
    
    public boolean verifyVertexExist(String a,String b){
        
        if (vertexGraph.contains(a) && vertexGraph.contains(b)){
            return true;
        }
        return false;
    }
    /**
     * add edges to the graph
     * @param String a
     * @param String b
       */
    
    public void addEdge(String a , String b){
        if (!verifyVertexExist(a, b)){
            return; //jopciotn
        }
        
        ArrayList<String> aux = new ArrayList<>();
        aux.add(a);
        aux.add(b);
        aristas.add(aux);        
        reorganizeEdges(aristas);
        reorganizeVertexGraph(vertexGraph);
        createAdjacencyMatrix();
    }
    /**
     * remove edges of the graph
     * @param String a
     * @param String b
       */
    public void removeEdge(String a, String b){
        if(!verifyVertexExist(a,b)){
            return;
        }
        ArrayList<String> aux = new ArrayList<>();
        aux.add(a);
        aux.add(b);
        for (int i = 0; i < aristas.size(); i++){
            if (aristas.get(i).equals(aux)){
                    aristas.remove(i);
            }
        }
    }
    /**
     * add vertices to the graph
     * @param String[] vertices
       */
    public void addVertices(String [] vertices){
        for (int i = 0; i < vertices.length; i++){
            vertexGraph.add(vertices[i]);
        }
        removeDuplicates();
    }
    //reorganize vertex
    private void reorganizeVertexGraph (ArrayList<String> vertex){
        if (vertex == null){
            return;
        }
        Collections.sort(vertex);
    }
    //reorganize aristas
    private void reorganizeEdges (ArrayList<ArrayList<String>>aristas){
        if (aristas == null){
            return;
        }
        Collections.sort(aristas, new Comparator<ArrayList<String>>(){ 
            @Override
            // Esto lo sacamos de internet y lo modificamos ejemplo:
            // Si es {{A, C},{A, B}}
            // Entonces compara la siguiente letra es decir
            // C - B
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
    //convert all aristas in uppercase
    private void upperCase(){
        for (ArrayList<String> fila : aristas){
            fila.set(0,fila.get(0).toUpperCase());
            fila.set(1,fila.get(1).toUpperCase());
        }
        for (int i = 0; i < vertexGraph.size(); i ++){
        vertexGraph.set(i, vertexGraph.get(i).toUpperCase());        
        }
    }
    
    //remove duplicate in vertex and aristas
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
    
    public String[] getVertexGraph(){
        String [] a = new String[vertices()];
        for (int i = 0; i < vertices(); i++){
            a[i] = vertexGraph.get(i);
        }
        return a;
    }
    public String[][] getAristas(){
        String[][] b = new String[edges()][];
        for (int i = 0; i < edges(); i ++){
            b[i] = aristas.get(i).toArray(new String[0]);
        }
        return b;
    }
}
