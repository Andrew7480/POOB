import java.util.ArrayList;

public class Tablero {
    private ArrayList <Flota> flotas = new ArrayList <Flota>();
    private int longitud;
    private int latitud;
    Tablero(){
    }
    /*
    Tablero (int i){
        longitud=100;
        latitud=100;
        for (int j =0;j<i;j++ ){
            Flota f = new Flota(j,this);
            flotas.add(f);
        }
    }*/
    
    /*
     * return all the flotas
     */
    public ArrayList<Flota> getFlotas(){
        return flotas;
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
