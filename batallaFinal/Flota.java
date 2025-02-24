import java.util.ArrayList;

public class Flota {
    private Tablero tablero;
    private String nombre;
    public final int codigo;
    
    private ArrayList <Avion> aviones;
    private ArrayList <PortaAviones> portaAviones;
    private ArrayList <Barco> barcos;
    private ArrayList <Marino> marinos;
    
    public Flota (int codigoFlota){
        codigo = codigoFlota;
    }
    public Flota (int codigoFlota, Tablero tab){
        codigo = codigoFlota;
        tablero = tab;
    }
    
    
    
    public String getNombre(){
        return nombre;
    }
    
    public ArrayList<Avion> getAviones(){
        return aviones;
    }
    /*
     * Consulta el número de flotas que tienen su mismo nombre
     */
    public int alias(){
        ArrayList <Flota> flotasEnemigas = new ArrayList<Flota>();
        if (tablero.getFlotas().isEmpty()){
            return 0;
            }
        for (int i = 0; i < tablero.getFlotasSize(); i ++){
            if (tablero.getFlotas().get(i).getNombre() == nombre){
                flotasEnemigas.add(tablero.getFlotas().get(i));
            }
        }
        
        return flotasEnemigas.size();
    }
    
    public int disponibilidadEnPortaaviones(){
        int totalCapacidadPortaaviones = 0;
        if (portaAviones == null){
            return 0;
            }
        for(PortaAviones pa: portaAviones){
            int capacidadActual = pa.getCapacidad();
            ArrayList <Avion> avionesDelPortaaviones = pa.getAviones();
            capacidadActual -=avionesDelPortaaviones.size();
            totalCapacidadPortaaviones += capacidadActual;   
        }
        return totalCapacidadPortaaviones;
    }
    /**
     * Consulta la placa de los aviones enemigos que están en el aire
     */
    public ArrayList<String> enAire(){
        ArrayList <Flota> flotasEnemigas = listFlotasEnemies();
        ArrayList <String> avionesEnAire = new ArrayList<String>();
        if (flotasEnemigas.isEmpty()){
            return null;
            }
        for(Flota flotaEnemiga : flotasEnemigas){
            
            for (Avion a: flotaEnemiga.getAviones()){
                if (a.getEnAire()){
                    avionesEnAire.add(a.getPlaca());
                }
            }
        }
        return avionesEnAire;
    }
    /**
     * returns a list
     */
    public ArrayList <Flota> listFlotasEnemies(){
        ArrayList <Flota> flotasEnemigas = new ArrayList<Flota>();
        if (tablero.getFlotasSize()==0){
            return new ArrayList<>();
            }
        for (int i = 0; i < tablero.getFlotasSize(); i ++){
            if (tablero.getFlotas().get(i).getNombre() != nombre){
                flotasEnemigas.add(tablero.getFlotas().get(i));
            }
        }
        return flotasEnemigas;
    }
    
    public ArrayList<PortaAviones>  listPortaavionesEnemies(ArrayList<Flota> flotasEnemigas){
        return portaAviones;
    }
    
    public ArrayList<Barco>  listBarcosEnemies(ArrayList<Flota> flotasEnemigas){
        return barcos;
    }
    /**
     * Verifica si la ubicación para un ataque en agua es adecuado
     */
    public boolean esBuenAtaque(int longitud, int latitud){
        ArrayList <Flota> flotasEnemigas = listFlotasEnemies();
        ArrayList<PortaAviones> portaAvionesEnemies = listPortaavionesEnemies(flotasEnemigas);
        ArrayList<Barco> barcosEnemies = listBarcosEnemies(flotasEnemigas);
        
        ArrayList<PortaAviones> portaAvionesEnemiesInPos = new ArrayList<PortaAviones>();
        ArrayList<Barco> barcosEnemiesInPos = new ArrayList<Barco>();
        if (portaAvionesEnemies != null ){
            
            for (PortaAviones pA:portaAvionesEnemies){
                Posicion ubicacionAtaque = new Posicion(longitud,latitud);
                
                if (ubicacionAtaque.equals(pA.getUbicacion())){
                    portaAvionesEnemiesInPos.add(pA);
                }
            }
            }
            
        if (barcosEnemies != null){
            for (Barco barc:barcosEnemies){
                Posicion ubicacionAtaque = new Posicion(longitud,latitud);
                
                if (ubicacionAtaque.equals(barc.getUbicacion())){
                    barcosEnemiesInPos.add(barc);
                }
            }
        }
        
        if (portaAvionesEnemiesInPos.size()==0 || barcosEnemiesInPos.size()==0){
            return false;
        }
        
        return true;     
    }
    /**
     * Mueve todos los barcos la distancia definida, si es posible.
     */
    public void muevase(int deltaLongitud, int deltaLatitud){
        boolean isPosible = true;
        
        int longitudTablero = tablero.getLongitud();
        int latitudTablero =tablero.getLatitud();
        
        if(aviones==null && barcos == null && portaAviones== null){
            return;
        }
        
        if (aviones != null){
            for (Avion a : aviones){
                if(a.getUbicacion().getLongitud()+deltaLongitud >longitudTablero){
                    isPosible = false;
                
                }
                if(a.getUbicacion().getLatitud()+deltaLatitud>latitudTablero){
                    isPosible = false;
                    
                }
            }
        }
        if (portaAviones !=null){
            for (PortaAviones pA : portaAviones){
                if(pA.getUbicacion().getLongitud()+deltaLongitud >longitudTablero){
                    isPosible = false;
                    
                }
                if(pA.getUbicacion().getLatitud()+deltaLatitud>latitudTablero){
                    isPosible = false;
                    
                }
            }
        }
        if(barcos !=null){
            for (Barco barc : barcos){
                if(barc.getUbicacion().getLongitud()+deltaLongitud >longitudTablero){
                    isPosible = false;
                    
                }
                if(barc.getUbicacion().getLatitud()+deltaLatitud>latitudTablero){
                    isPosible = false;
        
                }
            }
        }
        if (isPosible == true){
            move( deltaLongitud,  deltaLatitud);
        }
        else{
            System.out.println("No se puede mover esa cantidad ya que sale del tablero.");
        }   
    }
    private void move(int deltaLongitud, int deltaLatitud){
        
        if (aviones != null){
            for (Avion a : aviones){
                
                a.getUbicacion().setLongitud(a.getUbicacion().getLongitud()+deltaLongitud);
                a.getUbicacion().setLatitud(a.getUbicacion().getLatitud()+deltaLatitud);
                    
                
            }
        }
        if (portaAviones != null){
            for (PortaAviones pA : portaAviones){
                pA.getUbicacion().setLongitud(pA.getUbicacion().getLongitud()+deltaLongitud);
                pA.getUbicacion().setLatitud(pA.getUbicacion().getLatitud()+deltaLatitud);
            }
        }  
        if (barcos != null){
            for (Barco barc : barcos){
                barc.getUbicacion().setLongitud(barc.getUbicacion().getLongitud()+deltaLongitud);
                barc.getUbicacion().setLatitud(barc.getUbicacion().getLatitud()+deltaLatitud);
            }
        }
    }
    public int numeroMaquinas(){
        if (barcos != null && aviones != null && portaAviones != null){
            return barcos.size()+aviones.size()+portaAviones.size(); 
        }
        else{
            return 0;
        }
    }
    /**
     * Consulta si cuenta con suficientes marinos para conducir sus máquinas.
     */
    public boolean suficientesMarinos(){
        if (marinos == null){
            return false;
        }
        int cantidadMarinos = marinos.size();
        int marinosNecesarios=0;
        if (aviones != null){
            for (Avion a : aviones){
                marinosNecesarios+=2;
            }
        }
        if (portaAviones != null){
            for (PortaAviones pA : portaAviones){
               marinosNecesarios+=5; 
            }
        }  
        if (barcos != null){
            for (Barco barc : barcos){
                marinosNecesarios+=4;
            }
        }
        if (cantidadMarinos <marinosNecesarios){
            return false;
        }
        else{
            return true;
        }
    }
}
