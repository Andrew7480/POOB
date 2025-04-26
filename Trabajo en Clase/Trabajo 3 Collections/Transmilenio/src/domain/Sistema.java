package domain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sistema{

    private TreeMap<String,Estacion> estaciones;
    private ArrayList<Troncal> troncales;
    private TreeMap<String,Ruta> rutas;



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

    public void importarRutaDesdeArchivo(String nombreArchivo) throws IOException {
        BufferedReader archivos = new BufferedReader(new FileReader(nombreArchivo));
        String[] nombreRuta = archivos.readLine().split(" ");
        Ruta ruta1; 
        if(rutas.containsKey(nombreRuta[0])) ruta1 = rutas.get(nombreRuta[0]);
        else{
            ruta1 = new Ruta(nombreRuta[0]);
            rutas.put(nombreRuta[0], ruta1);
        }

        for (int i = 0; i<  Integer.parseInt(nombreRuta[1]); i++){
            String estacion = archivos.readLine().trim();
            Estacion nueva;
            if (estaciones.containsKey(estacion)) nueva = estaciones.get(estacion);
            else{
                nueva = new Estacion( estacion);
                estaciones.put(estacion, nueva);
            }
            try{
                ruta1.addEstacion(nueva);
            }
            catch(TransmilenioException e){
                //tulio incell
            }
        }
        archivos.close();
    }
    
}