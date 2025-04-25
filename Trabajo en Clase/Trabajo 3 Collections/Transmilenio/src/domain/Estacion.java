package domain;
import java.util.*;

public class Estacion {
    private String nombre;
    private String nivelOcupacion;
    private int tiempo;
    private TreeMap<String,Ruta> rutas;

    private HashMap<String, Integer> ocupacion = new HashMap<>();
    

    public Estacion(){
        ocupacion.put("Alta", 40);
        ocupacion.put("Media", 20);
        ocupacion.put("Baja", 5);
    }

    public int tiempoEspera() throws TransmilenioException{
        if (nivelOcupacion.equals("Alta")) return ocupacion.get("Alta");
        if (nivelOcupacion.equals("Media")) return ocupacion.get("Media");
        if (nivelOcupacion.equals("Baja")) return ocupacion.get("Baja");
        throw new TransmilenioException(TransmilenioException.INVALID_OCUPATION);
    }
    public String rutas(){
        String rutas1 = "";
        for (String r: rutas.keySet()){
            rutas1 += "-" + r;
        }
        return rutas1;
    }
    public boolean existeRuta(String Ruta1){
        return rutas.containsKey(Ruta1);
    }
    public int paradas(String estacion, String ruta){
        try {
            return rutas.get(ruta).calculoParadas(nombre, estacion);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getName(){
        return nombre;
    }
    
}
