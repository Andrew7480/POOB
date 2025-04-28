package domain;
import java.util.*;

public class Estacion {
    private String nombre;
    private String nivelOcupacion;
    private int tiempo;

    private HashMap<String, Integer> ocupacion = new HashMap<>();
    

    public Estacion(){
        ocupacion.put("Alta", 40);
        ocupacion.put("Media", 20);
        ocupacion.put("Baja", 5);
        nivelOcupacion = "Baja";
        tiempo = 0;
    }
    public Estacion(String newNombre){
        this();
        nombre = newNombre;
    }
    public Estacion(String newNombre, String nivel, int newTiempo) throws TransmilenioException{
        this();
        nombre = newNombre;
        if (nivel.equals("Alta") || nivel.equals("Media") || nivel.equals("Baja")) {
            nivelOcupacion = nivel;
        } else {
            throw new TransmilenioException(TransmilenioException.INVALID_OCUPATION);
        }        tiempo = newTiempo;
    }

    public int tiempoEspera() throws TransmilenioException{
        if (nivelOcupacion.equals("Alta")) return ocupacion.get("Alta");
        if (nivelOcupacion.equals("Media")) return ocupacion.get("Media");
        if (nivelOcupacion.equals("Baja")) return ocupacion.get("Baja");
        throw new TransmilenioException(TransmilenioException.INVALID_OCUPATION);
    }

    public String getName(){
        return nombre;
    }
    
}
