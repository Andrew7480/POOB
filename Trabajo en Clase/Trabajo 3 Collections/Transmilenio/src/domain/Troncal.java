package domain;
import java.util.*;

public class Troncal {
    private String nombre;
    private int velocidadProm;
    private HashMap<String,Estacion> estaciones;
    
    private HashMap<String, Integer> tramos; //?? digamos estacion1-estacion2 -> 10 metros

    public Troncal(String newNombre, int velocity){
        nombre = newNombre;
        velocidadProm = velocity;
        estaciones = new HashMap<>();
    }
    /**
     * get name of the troncal
     * @return
     */
    public String getName(){
        return nombre;
    }
    /**
     * add a new station
     */
    public void addEstacion(Estacion estation) throws TransmilenioException{
        if (estaciones.containsKey(estation.getName())) throw new TransmilenioException(TransmilenioException.ALREADY_EXITS);
        estaciones.put(estation.getName(),estation);
    }

    /**
     * get average velocity of troncal
     * @return
     */
    public int getVelocidadProm(){
        return velocidadProm;
    }
    /**
     * get arrayList of stations
     * @return
     */
    public String getStringEstaciones(){
        String estacionesString = "";
        for (String e : estaciones.keySet()){
            estacionesString += "-" + e;
        }
        return estacionesString;
    }
} 
