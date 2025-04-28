package domain;
import java.util.*;

public class Ruta {
    private String nombre;
    private ArrayList<Estacion> estaciones;
    
    public Ruta(String newNombre){
        nombre = newNombre;
    }
    public String getName(){
        return nombre;
    }
    /**
     * add a new station
     */
    public void addEstacion(Estacion estation) throws TransmilenioException{
        if (estaciones.contains(estation)) throw new TransmilenioException(TransmilenioException.ALREADY_EXITS);
        estaciones.add(estation);
    }
    /**
     * calculate the number of stops from one station to another
     * @return int
     */
    public int calculoParadas(String parada1, String parada2) throws TransmilenioException{
        int inicio = -1;
        int fin = -1;
        for(int i = 0; i < estaciones.size(); i++){
            String nombre = estaciones.get(i).getName();
            if (nombre.equals(parada1)) inicio = i;
            if (nombre.equals(parada2)) fin = i;
        }
        if (inicio == -1 || fin == -1){
            throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        }
        return (fin - inicio);
    }

    public String getNombre(){
        return nombre;
    }
    public boolean stationIsInRoute(String station){
        for(Estacion e : estaciones){
            if (station == e.getName()) return true;
        }
        return false;
    }

}
