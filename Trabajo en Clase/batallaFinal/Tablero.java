import java.util.ArrayList;

public class Tablero {
    private ArrayList <Flota> flotas = new ArrayList <Flota>();
    private int longitud;
    private int latitud;
    public Tablero (){
        longitud=100;
        latitud=100;
        
    }
    
    /*
     * return all the flotas
     */
    public ArrayList<Flota> getFlotas(){
        return flotas;
    }
    public void addFlota(Flota a){
        getFlotas().add(a);
    }
    public int getFlotasSize(){
        return flotas.size();
    }
    
    public int getLongitud(){
        return longitud;
    }
    public int getLatitud(){
        return latitud;
    }
}
