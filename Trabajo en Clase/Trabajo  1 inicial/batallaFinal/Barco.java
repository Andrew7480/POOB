import java.util.Collection;

public class Barco {

    private int numero;
    private static final int tripulantesMinimos = 4;
    private static int puntaje = 20;
    
    private Posicion ubicacion;
    private Collection<Marino> marinos;
    
    public Posicion getUbicacion(){
        return ubicacion;
    }
}
