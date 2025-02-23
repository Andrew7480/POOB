
public class Posicion {
    private int longitud;
    private int latitud;
    
    Posicion(int a, int b){
        longitud = a;
        latitud = b;
    }
    public int getLongitud(){
        return longitud;
    }
    public int getLatitud(){
        return latitud;
    }
    
    public void setLongitud(int lon){
         longitud =lon;
    }
    public void setLatitud(int lat){
        latitud = lat;
    }
    
    public boolean equals(Posicion pos) {
        if(pos.longitud != longitud || pos.latitud != latitud){
            return false;
        }
        return true;
    }
    
    
    public boolean equals(Object g){
        return equals((Posicion)g);
    }
}
