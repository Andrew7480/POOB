package domain;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class City implements Serializable{
    static private int SIZE=25;
    private Item[][] locations;

    /**Create a new City
     */
    public City() {
        locations=new Item[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                locations[r][c]=null;
            }
        }
        someItems();
    }

    //OPEN
    public City open(File file) throws CityException{
        if (!file.exists()) {
            System.err.println("Error: El archivo " + file.getName() + " no existe.");
            return null;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            City city= (City) in.readObject();
            System.out.println("Juego cargado exitosamente desde " + file.getName());
            return city;
        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            throw new CityException(CityException.OPEN_ERROR);
        }
    }
    //Save
    public void save(File fileName) throws CityException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            throw new CityException(CityException.SAVE_ERROR);
        }
    }

    //OPEN
    public City open01(File file) throws CityException{
        if (!file.exists()) {
            System.err.println("Error: El archivo " + file.getName() + " no existe.");
            return null;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            City city= (City) in.readObject();
            System.out.println("Juego cargado exitosamente desde " + file.getName());
            return city;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new CityException(CityException.OPEN_ERROR);
        }
    }
    //Save
    public void save01(File fileName) throws CityException{
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            throw new CityException(CityException.SAVE_ERROR);
        }
    }


    //OPEN
    public void open00(File fileName) throws CityException{
        throw new CityException(CityException.SAVE_CONSTRUCTION);
    }
    //Save
    public void save00(File fileName) throws CityException{
        throw new CityException(CityException.OPEN_CONSTRUCTION);
    }
    /**
     * @param file
     * do the function of export
     * */
    public void export(File file) throws CityException{
        try{
            BufferedWriter in = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < locations.length; i++){
                for (int j = 0; j < locations[0].length; j++){
                    if (locations[i][j] != null){
                        in.write(locations[i][j].getClass().getSimpleName() + " " + i + " " + j);
                        in.newLine();
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            throw new CityException(CityException.EXPORT_ERROR);
        }
    }
    /**
     * @param fileName
     * Do the function of import (mini-compiler) with a java function (reflection)
     * */
    public City importar(File fileName) throws CityException {
        int numeroDeLinea = 0;
        String linea = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            City newCity = new City();
            linea = in.readLine();
            while ((linea) != null) {
                numeroDeLinea++;
                linea = linea.trim();
                String[] division = linea.split(" ");
                String name = division[0];
                int posI = 0;
                int posJ = 0;
                try {
                    posI = Integer.parseInt(division[1]);
                    posJ = Integer.parseInt(division[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Linea " + numeroDeLinea + ": " + linea + " Posicion invalida o fuera del rango definido");
                }
                if (posI < 0 || posI >= SIZE || posJ < 0 || posJ >= SIZE) {
                    System.err.println("Linea " + numeroDeLinea + ": " + linea + " Posiciones fuera de rango");
                }
                if (newCity.getItem(posI, posJ) == null) {
                    try{
                        String fullName = "domain." + name;
                        Class<?> clasePrueba = Class.forName(fullName);
                        Object objPrueba = clasePrueba.getDeclaredConstructor(City.class, int.class,int.class).newInstance(newCity, posI, posJ);
                        newCity.setItem(posI, posJ, (Item) objPrueba);
                    }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        System.err.println("Linea " + numeroDeLinea + ": " + linea + "Clase no reconocida: ");
                    }
                }
                linea = in.readLine();
            }
            return newCity;
        } catch (IOException e) {
            throw new CityException(CityException.IMPORT_ERROR);
        }
    }
    /**
     * @param file
     * do the function of export
     * */
    public void export03(File file) throws CityException{
        try{
            BufferedWriter in = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < locations.length; i++){
                for (int j = 0; j < locations[0].length; j++){
                    if (locations[i][j] != null){
                        in.write(locations[i][j].getClass().getSimpleName() + " " + i + " " + j);
                        in.newLine();
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            throw new CityException(CityException.EXPORT_ERROR_COMPILADOR);
        }
    }
    /**
     * @param fileName
     * Do the function of import with a mini-compiler
     * */
    public City importar03(File fileName) throws CityException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String linea = in.readLine();
            City newCity = new City();
            int numeroDeLinea = 0;
            while ((linea) != null) {
                numeroDeLinea++;
                linea = in.readLine();
                linea = linea.trim();
                String[] division = linea.split(" ");
                String name = division[0];
                int posI = 0;
                int posJ = 0;
                try {
                    posI = Integer.parseInt(division[1]);
                    posJ = Integer.parseInt(division[2]);
                }catch (NumberFormatException e) {
                    System.err.println("Linea " + numeroDeLinea + ": " + linea + " Posicion invalida o fuera del rango definido");
                }
                if (posI < 0 || posI >= SIZE || posJ < 0 || posJ >= SIZE){
                    System.err.println("Linea " + numeroDeLinea + ": " + linea + " Posiciones fuera de rango");
                }
                if (newCity.getItem(posI, posJ) == null) {
                    if (name.equals("Person")) {
                        newCity.setItem(posI, posJ, (Item) new Person(newCity,posI,posJ));
                    } else if (name.equals("Road")) {
                        newCity.setItem(posI, posJ, (Item) new Road(newCity,posI,posJ));
                    } else if (name.equals("Schelling")) {
                        newCity.setItem(posI, posJ, (Item) new Schelling(newCity,posI,posJ));
                    } else if (name.equals("Slider")) {
                        newCity.setItem(posI, posJ, (Item) new Slider(newCity,posI,posJ));
                    } else if (name.equals("TrafficLight")) {
                        newCity.setItem(posI, posJ, (Item) new TrafficLight(newCity,posI,posJ));
                    } else if (name.equals("Walker")) {
                        newCity.setItem(posI, posJ, (Item) new Walker(newCity,posI,posJ));
                    } else {
                        System.err.println("Linea "+ numeroDeLinea + ": " + linea + "Clase no reconocida: ");
                    }
                }
                linea = in.readLine();
            }
            return newCity;
        } catch (IOException e) {
            throw new CityException(CityException.IMPORT_ERROR_COMPILADOR);
        }
    }

    /**
     * @param file
     * do the function of export
     * */
    public void export02(File file) throws CityException{
        try{
            BufferedWriter in = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < locations.length; i++){
                for (int j = 0; j < locations[0].length; j++){
                    if (locations[i][j] != null){
                        in.write(locations[i][j].getClass().getSimpleName() + " " + i + " " + j);
                        in.newLine();
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            throw new CityException(CityException.EXPORT_ERROR_COMPILADOR);
        }
    }
    /**
     * @param fileName
     * Do the function of import
     * */
    public City importar02(File fileName) throws CityException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String linea = in.readLine();;
            City newCity = new City();
            while ((linea) != null) {
                linea = linea.trim();
                String[] division = linea.split(" ");
                String name = division[0];
                int posI, posJ;
                posI = Integer.parseInt(division[1]);
                posJ = Integer.parseInt(division[2]);
                if (newCity.getItem(posI, posJ) == null) {
                    if (name.equals("Person")) {
                        newCity.setItem(posI, posJ, (Item) new Person(newCity,posI,posJ));
                    } else if (name.equals("Road")) {
                        newCity.setItem(posI, posJ, (Item) new Road(newCity,posI,posJ));
                    } else if (name.equals("Schelling")) {
                        newCity.setItem(posI, posJ, (Item) new Schelling(newCity,posI,posJ));
                    } else if (name.equals("Slider")) {
                        newCity.setItem(posI, posJ, (Item) new Slider(newCity,posI,posJ));
                    } else if (name.equals("TrafficLight")) {
                        newCity.setItem(posI, posJ, (Item) new TrafficLight(newCity,posI,posJ));
                    } else if (name.equals("Walker")) {
                        newCity.setItem(posI, posJ, (Item) new Walker(newCity,posI,posJ));
                    } else {
                        System.err.println("Clase no reconocida o posición ocupada en la línea: " + linea);
                    }
                }
                linea = in.readLine();
            }
            return newCity;
        } catch (IOException e) {
            throw new CityException(CityException.IMPORT_ERROR_COMPILADOR);
        }
    }



    /**
     * @param file
     * do the function of export a file (txt) in the format
     * */
    public void export01(File file) throws CityException{
        try{
            BufferedWriter in = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < locations.length; i++){
                for (int j = 0; j < locations[0].length; j++){
                    if (locations[i][j] != null){
                        in.write(locations[i][j].getClass().getSimpleName() + " " + i + " " + j);
                        in.newLine();
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            throw new CityException(CityException.EXPORT_ERROR);
        }
    }

    public City importar01(File fileName) throws CityException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String linea = in.readLine();;
            City newCity = new City();
            while (linea != null) {
                linea = linea.trim();
                String[] division = linea.split(" ");
                String name = division[0];
                int posI, posJ;
                posI = Integer.parseInt(division[1]);
                posJ = Integer.parseInt(division[2]);
                if (newCity.getItem(posI, posJ) == null) {
                    if (name.equals("Person")) {
                        newCity.setItem(posI, posJ, (Item) new Person(newCity,posI,posJ));
                    } else if (name.equals("Road")) {
                        newCity.setItem(posI, posJ, (Item) new Road(newCity,posI,posJ));
                    } else if (name.equals("Schelling")) {
                        newCity.setItem(posI, posJ, (Item) new Schelling(newCity,posI,posJ));
                    } else if (name.equals("Slider")) {
                        newCity.setItem(posI, posJ, (Item) new Slider(newCity,posI,posJ));
                    } else if (name.equals("TrafficLight")) {
                        newCity.setItem(posI, posJ, (Item) new TrafficLight(newCity,posI,posJ));
                    } else if (name.equals("Walker")) {
                        newCity.setItem(posI, posJ, (Item) new Walker(newCity,posI,posJ));
                    } else {
                        System.err.println("Clase no reconocida o posición ocupada en la línea: " + linea);
                    }
                }
                linea = in.readLine();
            }
            return newCity;
        } catch (IOException e) {
            throw new CityException(CityException.IMPORT_ERROR);
        }
    }

    public void export00(File fileName) throws CityException{
        throw new CityException(CityException.EXPORT_CONSTRUCTION);
    }

    public City import00(File fileName) throws CityException{
        throw new CityException(CityException.IMPORT_CONSTRUCTION);
    }

    /**
     * returns the size of the grid
       */
    public int  getSize(){
        return SIZE;
    }
    /**
     * get the item in the specific location
     * @param int r
     * @param int c
       */
    public Item getItem(int r,int c){
        return locations[r][c];
    }
    /**
     * set item in a location in range of the grid
     * @param int r
     * @param int c
     * @param Item e
       */
    public void setItem(int r, int c, Item e){
        if (isEmpty(r, c)){
            locations[r][c]=e;
        }
        else{
            System.out.println("Ya hay una en esa posicion?");
        }
    }
    /**
     * Change item position
     * @param int r;
     * @param int c;
     * @param int r1;
     * @param int c1;
     * @param Item e;
       */
    public void changeItemPosition(int r, int c,int r1, int c1, Item e){
        locations[r1][c1] = e;
        locations[r][c] = null;
    }
    /**
     * Calling constructors for creating instances of walkers,persons,traffic light, slider, road, schelling
       */
    public void someItems(){
          Person adan = new Person(this,10,10);
          Person eva = new Person(this,15,15);
          Walker messner = new Walker(this, 20,14);
          Walker kukuczka = new Walker(this, 5, 20);
          TrafficLight alarm = new TrafficLight(this,0,0);
          TrafficLight alert = new TrafficLight(this,0,24);
          Slider cardozo = new Slider(this,10,15 );
          Slider riaño = new Slider(this,15,3 );
          Road martinez = new Road(this,3,0 );
          Road sanchez = new Road(this,23,0);
          Schelling tulio = new Schelling(this,16,16 );
          Person tulio2 = new Person(this,15,0);
          Schelling tulio3 = new Schelling(this,16,0 );
          Schelling tulio4 = new Schelling(this,16,1 );
          Slider a = new Slider(this,0,1);
        
    }
    /**
     * Verify if the neighbors of the item are instance of Person
     * @param int r
     * @param int c
       */
    public int neighbors(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c] instanceof Person && locations[r+dr][c+dc] instanceof Person)) num++;
                }
            }
        }
        return num;
    }

    public void putPositionInMap(Item item, int i, int j){
        locations[i][j] = item;
    }

    /**
     * Verify if the neighbors of the item are equals.
     * @param int r
     * @param int c
       */
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c].getClass()==locations[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }   
    /**
     * return if the location in (r,c) is null
     * @param int r
     * @param int c
       */
    public boolean isEmpty(int r, int c){
        return (inLocations(r,c) && locations[r][c]==null);
    }    
    /**
     * verify if is in the range of the grid 
     * @param int r
     * @param int c
       */
    private boolean inLocations(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    /**
     * makes the functionality after click the tic tac bottom
     * change - decide
       */
    public void ticTac(){  
        ArrayList <Item> items = new ArrayList<>();
        
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (locations[i][j] != null){
                    items.add(locations[i][j]);
                }
                
            }
        }
        
        for (Item i: items){
            i.change();
            i.decide();
        }
    }

    

}
