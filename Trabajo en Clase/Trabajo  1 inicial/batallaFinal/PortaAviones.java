import java.util.ArrayList;

public class PortaAviones {
    private int numero;
    private int capacidad;
    private Posicion ubicacion;
    private ArrayList<Marino> marinos;
    private ArrayList<Avion> aviones;
    
    private static final int tripulantesMinimos = 5;
    private static int puntaje = 30;
    
    public ArrayList<Avion> getAviones(){
        return aviones;
    }
    public int getCapacidad(){
        return capacidad;
    }
    
    public Posicion getUbicacion(){
        return ubicacion;
    }
}
