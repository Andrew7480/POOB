package domain;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Color;
import java.io.*;
/*
 * POOBkemon
 */
public class POOBkemon implements Serializable{
    private TreeMap<String, Pokemon> pokedex = new TreeMap<>(); // Pokemones sin movimientos, los movimientos los pone y se añaden al jugador
    private TreeMap<String, Trainer> entrenadores = new TreeMap<>();

    private TreeMap<String, Item> items = new TreeMap<>(); //los items necsrios
    private TreeMap<String, Movement> movements = new TreeMap<>(); //movimientos predefinidos qu epuede escoger el usuario

    private Battle battle;
    public POOBkemon() {}


    public TreeMap<String, Pokemon> getPokemons(){
        return pokedex;
    }
    public TreeMap<String, Movement> getMovements(){
        return movements;
    }
    //-------------------------------------------------------------------------------------
    public ArrayList<String> getMovementsStringCurrent(){
        return battle.getMovementsStringCurrent();
    }
    public ArrayList<String> getMovementsStringOponent(){
        return battle.getMovementsStringOponent();
    }
    
    public void movementPerformed(String mov) throws PoobkemonException{
        battle.executeMovement(mov);
    }

    public void actionCambiar(String pok) throws PoobkemonException{
        System.out.println(pok + " LLEGO A POOBKEMON CORRECTO?");
        battle.changePokemon(pok);
    }

    public void actionuseItem(String item) throws PoobkemonException{
        battle.useItem(item);
    }
    public void actuinHuir() throws PoobkemonException{
        battle.huir();
        //o resetiar batalla  battle = null para luego crear la nueva
    }
    
    public void inicializateBattle(String player1, String player2){ //mirar pues
        Trainer trainer1 = entrenadores.get(player1);
        Trainer trainer2 = entrenadores.get(player2);
        battle = new Battle(trainer1, trainer2);
        
    }
    public Color getCurrentColor(){
        return battle.getCurrentColor();
    }

    public void inicialTrainerPokemon(String trainer, String pokemon) throws PoobkemonException{
        Trainer trainer1 = entrenadores.get(trainer);
        trainer1.setPokemonInUse(pokemon);
    }
    public ArrayList<String> inicialTrainerMovements(String trainer){
        Trainer trainer1 = entrenadores.get(trainer);
        return trainer1.getPokemonInUse().getMovementsString();
    }
    public String getCurrentPokemonName(){
        return battle.getCurrentPokemonName();
    }
    public int getCurrentPokemonLevel(){
        return battle.getCurrentPokemonLevel();
    }
    public int getCurrentPokemonPs(){
        return battle.getCurrentPokemonPs();
    }
    public int getCurrentPokemonPokedexIndex(){
        return battle.getCurrentPokemonPokedexIndex();
    }
    public String getOponentPokemonName(){
        return battle.getOponentPokemonName();
    }
    public int getOponentPokemonLevel(){
        return battle.getOponentPokemonLevel();
    }
    public int getOponentPokemonPs(){
        return battle.getOponentPokemonPs();
    }
    public int getOponentPokemonPokedexIndex(){
        return battle.getOponentPokemonPokedexIndex();
    }
    public int getcurrentMaxPs(){
        return battle.getCurrentMaxPs();
    }
    public int getOponentMaxPs(){
        return battle.getOponentMaxPs();
    }

    //-------------------------------------------------------------------------------------
    public void addNewTrainer(String name, Color color) throws PoobkemonException{
        if (entrenadores.containsKey(name)) throw new PoobkemonException(PoobkemonException.TRAINER_EXIST);
        entrenadores.put(name, new PlayerTrainer(name,color));
    }

    public boolean GameIsOVer(){
        return battle.isOver();
    }
    public TreeMap<String, Item> getItems() {
        return items;
    }
    public void addItem(Item  item){
        items.put(item.getName(),item);
    }
    public void addMovement(Movement mov) throws PoobkemonException{
        if (movements.containsValue(mov)) throw new PoobkemonException(PoobkemonException.MOVEMENT_ALREADY_EXIST);
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
    public TreeMap<String,Trainer> getTrainers(){
        return entrenadores;
    }
    public String isTrainerIsed(String name) throws PoobkemonException{
        if(entrenadores.containsKey(name)) throw new PoobkemonException(PoobkemonException.TRAINER_EXIST);
        return name;
    }

    public void addNewPokemon(String entrenador, String pokemon,Movement m1,Movement m2, Movement m3, Movement m4)throws PoobkemonException{
        Pokemon pokemon1 = pokedex.get(pokemon).copy();
        pokemon1.setMovements(new Movement[]{m1,m2,m3,m4});
        entrenadores.get(entrenador).addPokemon(pokemon1);
    }

    public TreeMap<String,Movement> validMovements(Pokemon pokemon){
        TreeMap<String,Movement> movementsForPokemon = new TreeMap<>();
        for(Movement movement : movements.values()) {
            double multiplicador = movement.getMultiplicator(pokemon.getPrincipalType());

            if (multiplicador <= 1.0){
                movementsForPokemon.put(movement.getName(), movement);
            }
        }

        return movementsForPokemon;
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
