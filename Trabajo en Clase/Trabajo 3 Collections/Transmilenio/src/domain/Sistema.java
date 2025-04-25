package domain;
import java.util.*;

public class Sistema{

    private TreeMap<String,Estacion> estaciones;
    private ArrayList<Troncal> troncales;


    public int tiempoEsperaEstacion(String  name){
        try {
            return estaciones.get(name).tiempoEspera();
        } catch (Exception e) {
            return 0;
        }
    }
    public String rutas(){
        String rutas1 = "";
        for (Estacion e : estaciones.values()){
            rutas1 += " | " + e.rutas();
        }
        return rutas1;
    }
    public int numeroParadas(String ruta, String estacion1, String estacion2) throws TransmilenioException{
        if (!estaciones.containsKey(estacion1) || ! estaciones.containsKey(estacion2)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        if (! estaciones.get(estacion1).existeRuta(ruta)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        return estaciones.get(estacion1).paradas(estacion2, ruta);
    }
    public String rutasSinTransbordo(String estacion1, String estacion2) throws TransmilenioException{
        if (!estaciones.containsKey(estacion1) || ! estaciones.containsKey(estacion2)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        String rutas1 = "";
        for (Estacion e : estaciones.values()){
            rutas1 += " | " + e.rutas();
        }

        return rutas1;
    }
}