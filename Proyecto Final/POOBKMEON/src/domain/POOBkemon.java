package domain;
import java.util.TreeMap;
import java.io.*;
/*
 * POOBkemon
 */
public class POOBkemon implements Serializable{
    private TreeMap<String, Pokemon> pokedex = new TreeMap<>(); // Pokemones sin movimientos, los movimientos los pone y se añaden al jugador
    private TreeMap<String, Trainer> entrenadores = new TreeMap<>();

    private TreeMap<String, Item> items = new TreeMap<>(); //los items necsrios
    private TreeMap<String, Movement> movements = new TreeMap<>(); //movimientos predefinidos qu epuede escoger el usuario

    //turno actual
    private Trainer turn;
    //son los que estan en la batalla
    private Trainer trainerTurn1;
    private Trainer trainerTurn2;

    //idea 2 y un arreglo de trainser organizado de 2
    //private int turno = 1;

    public POOBkemon() {
    }


    public void iniciarJuego(String jugabilidad){  // ya existen pokemones, items, movimientos y estados y/o ytaines defectos
        ///leer el archivo de juego
        /// 
        //while hastya que los todos pokemones de alguno mueran
    }
    public TreeMap<String, Pokemon> getPokemons(){
        return pokedex;
    }
    public TreeMap<String, Movement> getMovements(){
        return movements;
    }
    public void actionOrderPM(Movement mov){
        Movement mov1 = turn.decide(trainerTurn2.getPokemonInUse());
    }

    public void actionOrderMM(){
        Movement mov1 = trainerTurn1.decide(trainerTurn2.getPokemonInUse());
        Movement mov2 = trainerTurn2.decide(trainerTurn1.getPokemonInUse());
        if (mov1.getPriority() > mov2.getPriority()) {
            movementPerfomrmed(trainerTurn1, trainerTurn2, mov1, mov2);
        } 
        else if (mov1.getPriority() == mov2.getPriority()) {
            if (trainerTurn1.getPokemonInUse().getVelocity() > trainerTurn2.getPokemonInUse().getVelocity()) {
                movementPerfomrmed(trainerTurn1, trainerTurn2, mov1, mov2);
            } else {
                movementPerfomrmed(trainerTurn2, trainerTurn1, mov2, mov1);
            }
        }
    }

    private void movementPerfomrmed(Trainer one, Trainer two, Movement movement1, Movement movement2 ){
        try{
            one.getPokemonInUse().useMovement(movement1, two.getPokemonInUse());
        }
        catch(PoobkemonException e){
            System.out.println(e.getMessage());
        }
        try{
            two.getPokemonInUse().useMovement(movement2, one.getPokemonInUse());
        }
        catch(PoobkemonException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void movementPerformed(Movement mov, Pokemon target) throws PoobkemonException{
        turn.pokemonMovement(mov,target);
        turn = (turn.equals(trainerTurn1)) ? trainerTurn2 : trainerTurn1;
    }

    public void actiontrainerTurnoDelTrainerCambiar(Pokemon pok) throws PoobkemonException{
        turn.changePokemon(pok);
        turn = (turn.equals(trainerTurn1)) ? trainerTurn2 : trainerTurn1;
    }
    public void actiontrainerTurnoInventario(Item item) throws PoobkemonException{
        turn.useItem(item);
        turn = (turn.equals(trainerTurn1)) ? trainerTurn2 : trainerTurn1;
    }
    public void actuinHuir() throws PoobkemonException{
        //reset o metodo acabar batalla, que seria guardar los estados del pokemon items etc?
    }

    public boolean theGameIsOVer(){
        return (trainerTurn1.canStillFighting() && trainerTurn2.canStillFighting());
    }


    public TreeMap<String, Item> getItems() {
        return items;
    }
    public void addItem(Item  item){
        items.put(item.getName(),item);
    }
    public void addMovement(Movement mov) throws PoobkemonException{
        if (movements.containsValue(mov)) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);
        movements.put(mov.getName(), mov);
        
    }
    public void addPokemon(Pokemon pokemon) {
        pokedex.put(pokemon.getName(), pokemon);
    }
    public void addTrainer(Trainer trainer) {
        entrenadores.put(trainer.getName(),trainer);
    }
    public Pokemon getPokemon(String name) {
        return pokedex.get(name);
    }
    public Trainer getTrainer(String name) {
        return entrenadores.get(name);
    }
    public TreeMap<String, Pokemon> getPokedex(){
        return pokedex;
    }
    public void addNewPokemon(String entrenador, Pokemon pokemon,Movement m1,Movement m2, Movement m3, Movement m4)throws PoobkemonException{
        pokemon.setMovements(new Movement[]{m1,m2,m3,m4});
        entrenadores.get(entrenador).addPokemon(pokemon);
    }

    private void serializateGame(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameData"));
            out.writeObject(this);
            out.close();
        } catch (NotSerializableException e) {
            System.out.println("Error al serializar: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al guardar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public void serializateGame(){
        String fileName = "gameData";
        serializateGame(fileName);
    }

    private POOBkemon deserializateGame(String fileName) {
        File file = new File(fileName);
    if (!file.exists()) {
        System.err.println("El archivo " + fileName + " no existe.");
        return null;
    }

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        Object deserializedObject = in.readObject();
        if (deserializedObject instanceof POOBkemon) {
            POOBkemon poobkemon = (POOBkemon) deserializedObject;
            System.out.println("Juego cargado exitosamente desde " + fileName);
            //System.out.println("Pokédex: " + poobkemon.getPokedex());
            return poobkemon;
        } else {
            System.err.println("El archivo no contiene un objeto de tipo POOBkemon.");
            return null;
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.err.println("Error: Clase no encontrada durante la deserialización: " + e.getMessage());
    }
    return null;
    }


    public POOBkemon deserializateGame(){
        String fileName = "gameData";
        return deserializateGame(fileName);
    }

    

    


    
    
}
