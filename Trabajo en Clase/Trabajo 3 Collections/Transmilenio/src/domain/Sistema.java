package domain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Sistema{

    private TreeMap<String,Estacion> estaciones;
    private TreeMap<String,Troncal> troncales;
    private TreeMap<String,Ruta> rutas;

    public Sistema() throws TransmilenioException{
        estaciones =  new TreeMap<>();
        troncales =  new TreeMap<>();
        rutas =   new TreeMap<>();
        pruebas();
    }

    private void pruebas() throws TransmilenioException{
        addEstacion( new Estacion("Mandalay", "Media" ,10) );
        addEstacion(  new Estacion("Campin", "Media" ,10));
        addEstacion(  new Estacion("Zona Industrial", "Media" ,10));
        addEstacion(new Estacion("Banderas", "Alta", 15));
        addEstacion(new Estacion("Tunal", "Baja", 5));
        addEstacion(new Estacion("Usme", "Alta", 12));
        addEstacion(new Estacion("Suba", "Media", 7));
        addEstacion(new Estacion("Niza", "Baja", 6));
        addEstacion(new Estacion("Calle 80", "Alta", 20));
        addEstacion(new Estacion("Calle 26", "Alta", 18));
        addEstacion(new Estacion("Andes", "Media", 8));
        addEstacion(new Estacion("Centro", "Baja", 4));
        Troncal troncal = new Troncal("NQS-sur", 10);
        addTroncal(troncal);
        troncal.addEstacion(estaciones.get("Mandalay"));
        troncal.addEstacion(estaciones.get("Zona Industrial"));
        Ruta ruta1 = new Ruta("F-72");
        Ruta ruta2 = new Ruta("B-3");
        Ruta ruta3 = new Ruta("G-44");
        ruta1.addEstacion(estaciones.get("Mandalay"));
        ruta1.addEstacion(estaciones.get("Banderas"));
        ruta1.addEstacion(estaciones.get("Calle 26"));
        ruta2.addEstacion(estaciones.get("Mandalay"));
        ruta2.addEstacion(estaciones.get("Centro"));
        ruta3.addEstacion(estaciones.get("Suba"));
        ruta3.addEstacion(estaciones.get("Zona Industrial"));
        ruta3.addEstacion(estaciones.get("Campin"));
        ruta3.addEstacion(estaciones.get("Mandalay"));
        ruta3.addEstacion(estaciones.get("Banderas"));
        addRuta(ruta1);
        addRuta(ruta2);
        addRuta(ruta3);
    }

    /**
     * Returns the wait time of a specific station
     * @return int 
     */
    public int tiempoEsperaEstacion(String  name) throws TransmilenioException{
        if (!estaciones.containsKey(name)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        return estaciones.get(name).tiempoEspera();
    }
    
    /**
     * Name of the routes sorted alphabetically
     * @return
     */
    public ArrayList<String> rutasList(){
        ArrayList<String> rutas1 = new ArrayList<String>();
        for (String r: rutas.keySet()){
            rutas1.add(r);
        }
        return rutas1;
    } 

    /**
     * Name of the routes sorted alphabetically in String
     * @return
     */
    public String rutasString(){
        ArrayList<String> rutas = rutasList();
        String routes = "";
        for (String r: rutas){
            routes += "-> " + r;
        }
        return routes;
    }

    /**
     * Calculate the amount of stops from estation 1 to estation 2
     * @param ruta
     * @param estacion1
     * @param estacion2
     * @return
     * @throws TransmilenioException
     */
    public int numeroParadas(String ruta, String estacion1, String estacion2) throws TransmilenioException{
        if (!estaciones.containsKey(estacion1) || !estaciones.containsKey(estacion2) || !rutas.containsKey(ruta)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        return rutas.get(ruta).calculoParadas(estacion1,estacion2);
    }

    /**
     * 
     * @param estacion1
     * @param estacion2
     * @return
     * @throws TransmilenioException
     */
    public ArrayList<String> rutasSinTransbordo(String estacion1, String estacion2) throws TransmilenioException{
        if (!estaciones.containsKey(estacion1) || ! estaciones.containsKey(estacion2)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        ArrayList<String> rutas1 = new ArrayList<String>();
        for (String e : rutas.keySet()){
            try {
                int valor = numeroParadas(e,estacion1,estacion2);
                if (valor > 0) rutas1.add(e + "|" + valor);
            }
            catch (TransmilenioException i){
            }
        }
        //https://stackoverflow.com/questions/66667352/how-to-sort-using-collections-sort-when-having-several-fields
        Collections.sort(rutas1,(ruta1, ruta2) -> {
            int numParadas1 = Integer.parseInt(ruta1.split("\\|")[1]);
            int numParadas2 = Integer.parseInt(ruta2.split("\\|")[1]);

            if (numParadas1 != numParadas2) {
                return Integer.compare(numParadas1, numParadas2);
            }
            return ruta1.split("\\|")[0].compareTo(ruta2.split("\\|")[0]);
        });
        ArrayList<String> nombreRutas = new ArrayList<String>();
        for (String s: rutas1){
            nombreRutas.add(s.split("\\|")[0]);
        }
        return nombreRutas;
    }
    public String rutasSinTransbordoString(String estacion1, String estacion2) throws TransmilenioException{
        ArrayList<String> rutas1 = rutasSinTransbordo(estacion1,estacion2);
        String rutass = "";
        for(String i: rutas1){
            rutass+= "-> " + i;
        }
        return rutass;
    }

    /**
     * Imports a new route from a file of text. The file contains the name of the route and the name
     * of the stations what does it go through
     * @param nombreArchivo
     * @throws IOException
     */
    public Ruta importarRutaDesdeArchivo(String nombreArchivo) throws IOException {
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
                System.out.println(e.getMessage());
            }
        }
        archivos.close();
        return ruta1;
    }

    /**
     * Export a new file text with the routes that allowed to go from one estation to another without "transbordos".
     * The file only contains the name of the routes.
     * @param nombreArchivo
     * @param estacion1
     * @param estacion2
     * @throws TransmilenioException
     */
    public void exportarAArchivoTexto(String nombreArchivo,String estacion1,String estacion2) throws TransmilenioException{
        ArrayList<String> rutas = rutasSinTransbordo(estacion1, estacion2);
        if (rutas.size() == 0) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        try {
            BufferedWriter archivo = new BufferedWriter(new FileWriter(nombreArchivo));
            for (int i = 0; i < rutas.size();i++){
                archivo.write(rutas.get(i));
                archivo.newLine();
            }
            archivo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // AutoNorte
    // 30
    // [CAMPIN,CALLE 75,CASTELLANA,GUATOQUE...etc]
    // https://www.geeksforgeeks.org/serialization-in-java/
    public void salavaInformacionTroncalSerialization(String nombreTroncal) throws TransmilenioException{
        if (!troncales.containsKey(nombreTroncal)) throw new TransmilenioException(TransmilenioException.NOT_FOUND);
        Troncal troncal = troncales.get(nombreTroncal);
        String nombreArchivo = nombreTroncal + ".txt";
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            out.writeObject(troncal);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEstacion(Estacion estacion){
        estaciones.put(estacion.getName(),estacion);
    }
    private void addTroncal(Troncal troncal){
        troncales.put(troncal.getName(),troncal);
    }
    private void addRuta(Ruta route){
        rutas.put(route.getName(),route);
    }
}