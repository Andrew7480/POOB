public class Avion {
    private String placa;
    private boolean enAire;
    private Posicion ubicacion;
    private Marino piloto;
    private Marino copiloto;
    
    private static final int tripulanteMinimos = 2;
    private static int puntaje = 10;
    
    
    public String getPlaca(){
        return placa;
    }
    public boolean getEnAire(){
        return enAire;
    }
    public Posicion getUbicacion(){
        return ubicacion;
    }
}
