package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.awt.Color;
import java.io.*;
import java.util.Random;
public class POOBkemon implements Serializable{
    private TreeMap<String, Pokemon> pokedex = new TreeMap<>(); // Pokemones sin movimientos, los movimientos los pone y se añaden al jugador
    private TreeMap<String, Trainer> entrenadores = new TreeMap<>();

    private TreeMap<String, Item> items = new TreeMap<>(); //los items necsrios
    private TreeMap<String, Movement> movements = new TreeMap<>(); //movimientos predefinidos qu epuede escoger el usuario

    protected Battle battle;
    

    /**
     * Default constructor for POOBkemon
     * Initializes a new POOBkemon game instance
     */
    public POOBkemon() {}

    public Battle getBattle(){
        return battle;
    }

    public boolean opponentIsAffected(){
        return battle.opponentIsAffected();
    }
    public boolean currentIsAffected(){
        return battle.currentIsAffected();
    }

    /**
     * Gets all the pokemons available in the game
     * @return TreeMap containing all pokemons, with names as keys
     */
    public TreeMap<String, Pokemon> getPokemons(){
        return pokedex;
    }

    /**
     * Gets all movements available in the game
     * @return TreeMap containing all movements, with names as keys
     */
    public TreeMap<String, Movement> getMovements(){
        return movements;
    }
    

    //-------------------------------------------------------------------------------------

    public ArrayList<String> getDeadCurrentPokemons(){
        return battle.getDeadCurrentPokemons();
    }

    public ArrayList<String>  getCurrentItems(){
        return battle.getCurrentItems();
    }
    public ArrayList<String>  getCurrentPokemons(){
        return battle.getCurrentPokemons();
    }
    public ArrayList<String> getItemsName(){
        return new ArrayList<>(items.keySet());
    }
    public ArrayList<String> getPokemonsName(){
        return new ArrayList<>(pokedex.keySet());
    }

    /**
     * Gets the PP (Power Points) of a specific movement in the current battle
     * @param name Name of the movement
     * @return The PP value of the specified movement
     * @throws PoobkemonException If the movement doesn't exist or cannot be performed
     */
    public int getPPInBattle(String name) throws PoobkemonException{
        return battle.getPPInBattle(name);
    }

    /**
     * Gets the list of movement names available for the current player's pokemon
     * @return ArrayList of movement names as strings
     */
    public ArrayList<String> getMovementsStringCurrent(){
        return battle.getMovementsStringCurrent();
    }

    /**
     * Gets the list of movement names available for the opponent's pokemon
     * @return ArrayList of movement names as strings
     */
    public ArrayList<String> getMovementsStringOponent(){
        return battle.getMovementsStringOponent();
    }
    
    /**
     * Executes a movement in the current battle
     * @param mov Name of the movement to perform
     * @throws PoobkemonException If the movement cannot be performed or doesn't exist
     */
    public void movementPerformed(String mov) throws PoobkemonException{
        battle.executeMovement(mov);
    }
    /**
     * Verify if the current pokemon is sacrificable
     *  @throws PoobkemonException If there is an issue finding a pokemon
     */
    public boolean isSacrificableCurrent() throws PoobkemonException{
        return battle.isSacrificable();
    }
    public void sacrificateCurentPokemon(String namePok) throws PoobkemonException{
        battle.sacrificateCurentPokemon(namePok);
    }

    /**
     * Changes the current pokemon during battle
     * @param pok Name of the pokemon to switch to
     * @throws PoobkemonException If the pokemon cannot be switched or doesn't exist
     */
    public void actionCambiar(String pok) throws PoobkemonException{
        battle.changePokemon(pok);
    }

    /**
     * Uses an item during battle
     * @param item Name of the item to use
     * @throws PoobkemonException If the item cannot be used or doesn't exist
     */
    public void actionUseItem(String item) throws PoobkemonException{
        battle.useItem(item);
    }

    public void actionUseItem(String pokemon,String item) throws PoobkemonException{
        battle.useItem(pokemon,item);
    }
    public ArrayList<String> getCurrentAlivePokemons(){
        return battle.getCurrentAlivePokemons();
    }
    public ArrayList<String> getCurrentAlivePokemonsWithoutCurrent(){
        return battle.getCurrentAlivePokemonsWithoutCurrent();
    }

    /**
     * Attempts to flee from the current battle
     * @throws PoobkemonException If escape is not possible
     */
    public void actuinHuir() throws PoobkemonException{
        battle.huir();
        //o resetiar batalla  battle = null para luego crear la nueva
    }
    
    /**
     * Initializes a new battle between two trainers
     * @param playerOne Name of the first trainer
     * @param playerTwo Name of the second trainer
     */
    private void inicializateBattle(Trainer playerOne, Trainer playerTwo){
        battle = new Battle(playerOne, playerTwo);
    }
    public void inicializateBattleMvsM(String machineOne, String machineTwo){
        inicializateBattle(entrenadores.get(machineOne).copy(), entrenadores.get(machineTwo).copy());
    }
    public void inicializateBattlePVsM(String playerOne, String machineTwo){
        inicializateBattle(entrenadores.get(playerOne), entrenadores.get(machineTwo).copy());
    }
    public void inicializateBattlePVsP(String playerOne, String machineTwo){
        inicializateBattle(entrenadores.get(playerOne), entrenadores.get(machineTwo));
    }
    /**
     * Gets the color of the current trainer
     * @return Color object representing the current trainer's color
     */
    public Color getCurrentColor(){
        return battle.getCurrentColor();
    }

    /**
     * Sets the initial pokemon for a trainer
     * @param trainer Name of the trainer
     * @param pokemon Name of the pokemon to set as initial
     * @throws PoobkemonException If the trainer or pokemon don't exist
     */
    public void inicialTrainerPokemon(String trainer, String pokemon) throws PoobkemonException{
        Trainer trainer1 = entrenadores.get(trainer);
        trainer1.setPokemonInUse(pokemon);
    }

    /**
     * Gets the movement names of the initial pokemon for a trainer
     * @param trainer Name of the trainer
     * @return ArrayList of movement names as strings
     */
    public ArrayList<String> inicialTrainerMovements(String trainer){
        Trainer trainer1 = entrenadores.get(trainer);
        return trainer1.getPokemonInUse().getMovementsString();
    }
    
    /**
     * Gets the name of the current pokemon in battle
     * @return Name of the current pokemon
     */
    public String getCurrentPokemonName(){
        return battle.getCurrentPokemonName();
    }

    /**
     * Gets the level of the current pokemon in battle
     * @return Level of the current pokemon
     */
    public int getCurrentPokemonLevel(){
        return battle.getCurrentPokemonLevel();
    }

    /**
     * Gets the current health points of the current pokemon in battle
     * @return Current health points of the current pokemon
     */
    public int getCurrentPokemonPs(){
        return battle.getCurrentPokemonPs();
    }

    /**
     * Gets the pokedex index of the current pokemon in battle
     * @return Pokedex index of the current pokemon
     */
    public int getCurrentPokemonPokedexIndex(){
        return battle.getCurrentPokemonPokedexIndex();
    }

    /**
     * Gets the name of the opponent's pokemon in battle
     * @return Name of the opponent's pokemon
     */
    public String getOponentPokemonName(){
        return battle.getOponentPokemonName();
    }

    /**
     * Gets the level of the opponent's pokemon in battle
     * @return Level of the opponent's pokemon
     */
    public int getOponentPokemonLevel(){
        return battle.getOponentPokemonLevel();
    }

    /**
     * Gets the current health points of the opponent's pokemon in battle
     * @return Current health points of the opponent's pokemon
     */
    public int getOponentPokemonPs(){
        return battle.getOponentPokemonPs();
    }

    /**
     * Gets the pokedex index of the opponent's pokemon in battle
     * @return Pokedex index of the opponent's pokemon
     */
    public int getOponentPokemonPokedexIndex(){
        return battle.getOponentPokemonPokedexIndex();
    }

    /**
     * Gets the maximum health points of the current pokemon in battle
     * @return Maximum health points of the current pokemon
     */
    public int getcurrentMaxPs(){
        return battle.getCurrentMaxPs();
    }

    /**
     * Gets the maximum health points of the opponent's pokemon in battle
     * @return Maximum health points of the opponent's pokemon
     */
    public int getOponentMaxPs(){
        return battle.getOponentMaxPs();
    }

    /**
     * Checks if the current pokemon in battle is alive
     * @return true if the current pokemon is alive, false otherwise
     */
    public boolean isAliveCurrentPokemon(){
        return battle.isAliveCurrentPokemon();
    }

    /**
     * Checks if the opponent's pokemon in battle is alive
     * @return true if the opponent's pokemon is alive, false otherwise
     */
    public boolean isAliveOpponentPokemon(){
        return battle.isAliveOpponentPokemon();
    }

    /**
     * Adds a new trainer to the game
     * @param name Name of the new trainer
     * @param color Color associated with the trainer
     * @throws PoobkemonException If a trainer with the same name already exists
     */
    public void addNewTrainer(String name, Color color) throws PoobkemonException{
        if (entrenadores.containsKey(name)) throw new PoobkemonException(PoobkemonException.TRAINER_EXIST);
        entrenadores.put(name, new PlayerTrainer(name,color));
    }

    /**
     * Checks if the current battle is over
     * @return true if the battle is over, false otherwise
     */
    public boolean GameIsOVer(){
        return battle.isOver();
    }
        /**
     * Checks if the current battle is over
     * @return the winner
     */
    public String getWinner(){
        return battle.getWinner();
    }

    /**
     * Gets all items available in the game
     * @return TreeMap containing all items, with names as keys
     */
    public TreeMap<String, Item> getItems() {
        return items;
    }

    /**
     * Adds a new item to the game
     * @param item Item to add
     */
    public void addItem(Item item){
        items.put(item.getName(),item);
    }

    /**
     * Adds a new movement to the game
     * @param mov Movement to add
     * @throws PoobkemonException If a movement with the same name already exists
     */
    public void addMovement(Movement mov) throws PoobkemonException{
        if (movements.containsValue(mov)) throw new PoobkemonException(PoobkemonException.MOVEMENT_ALREADY_EXIST);
        movements.put(mov.getName(), mov);
    }

    /**
     * Adds a new pokemon to the pokedex
     * @param pokemon Pokemon to add
     */
    public void addPokemon(Pokemon pokemon) {
        pokedex.put(pokemon.getName(), pokemon);
    }

    /**
     * Adds a new trainer to the game
     * @param trainer Trainer to add
     */
    public void addTrainer(Trainer trainer) {
        entrenadores.put(trainer.getName(),trainer);
    }

    /**
     * Gets a pokemon by its name
     * @param name Name of the pokemon
     * @return Pokemon object, or null if not found
     */
    public Pokemon getPokemon(String name) {
        return pokedex.get(name);
    }

    /**
     * Gets a trainer by their name
     * @param name Name of the trainer
     * @return Trainer object, or null if not found
     */
    public Trainer getTrainer(String name) {
        return entrenadores.get(name);
    }

    /**
     * Gets the complete pokedex
     * @return TreeMap containing all pokemons, with names as keys
     */
    public TreeMap<String, Pokemon> getPokedex(){
        return pokedex;
    }
    public TreeMap<String, String> getPokedexNameIndex(){
        TreeMap<String, String> pokedexNameIndex = new TreeMap<>();
        for (String key : pokedex.keySet()) {
            pokedexNameIndex.put(key, String.valueOf(pokedex.get(key).getPokedexIndex()));
        }
        return pokedexNameIndex;
    }
    public ArrayList<String> getPokedexNames(){
        return new ArrayList<>(pokedex.keySet());
    }
    public String toolTipForPokemon(String name){
        if (pokedex.containsKey(name)){
            Pokemon pokemon = pokedex.get(name);
            return pokemon.createPokemonForToolTip();
        }
        return "";
    }
    public String toolTipForItem(String name){
        if (items.containsKey(name)){
            Item item = items.get(name);
            return item.createItemForToolTip();
        }
        return "";
    }

    /**
     * Gets all trainers in the game
     * @return TreeMap containing all trainers, with names as keys
     */
    public TreeMap<String,Trainer> getTrainers(){
        return entrenadores;
    }

    /**
     * Checks if a trainer name is already used
     * @param name Name to check
     * @return The name if it's not used
     * @throws PoobkemonException If the trainer name already exists
     */
    public String isTrainerIsed(String name) throws PoobkemonException{
        if(entrenadores.containsKey(name)) throw new PoobkemonException(PoobkemonException.TRAINER_EXIST);
        return name;
    }

    /**
     * Adds a new pokemon with specific movements to a trainer
     * @param entrenador Name of the trainer
     * @param pokemon Name of the pokemon to add
     * @param m1 First movement
     * @param m2 Second movement
     * @param m3 Third movement
     * @param m4 Fourth movement
     * @throws PoobkemonException If the trainer or pokemon don't exist
     */
    public void addNewPokemon(String entrenador, String pokemon,Movement m1,Movement m2, Movement m3, Movement m4)throws PoobkemonException{
        Pokemon pokemon1 = pokedex.get(pokemon).copyPokemon();
        pokemon1.setMovements(new Movement[]{m1,m2,m3,m4});
        entrenadores.get(entrenador).addPokemon(pokemon1);
    }
    public void addNewPokemon(String entrenador, String pokemon,String m1,String m2, String m3, String m4)throws PoobkemonException{
        Pokemon pokemon1 = pokedex.get(pokemon).copyPokemon();
        Movement mov1 = movements.get(m1);
        Movement mov2 = movements.get(m2);
        Movement mov3 = movements.get(m3);
        Movement mov4 = movements.get(m4);
        if (mov1 == null || mov2 == null || mov3 == null || mov4 == null) {
            throw new PoobkemonException(PoobkemonException.MOVEMENT_NOT_FOUND);
        }
        pokemon1.setMovements(new Movement[]{mov1, mov2, mov3, mov4});
        entrenadores.get(entrenador).addPokemon(pokemon1);
    }

    /**
     * Gets all valid movements for a specific pokemon
     * @param pokemon Pokemon to check movements for
     * @return TreeMap containing valid movements, with names as keys
     */
    public ArrayList<String> getValidMovementsFor(String pokemonName){
        ArrayList<String> movementsForPokemon = new ArrayList<>();
        ArrayList<Movement> movements = getValidMovements(pokedex.get(pokemonName));
        for(Movement movement : movements) {movementsForPokemon.add(movement.getName());}
        return movementsForPokemon;
    }

    public ArrayList<Movement> getValidMovements(Pokemon pokemon){
        ArrayList<Movement> movementsForPokemon = new ArrayList<>();
        for(Movement movement : movements.values()) {
            double multiplicador = movement.getMultiplicatorDebil(pokemon.getPrincipalType());
            if (multiplicador <= 1.0) movementsForPokemon.add(movement);
        }
        return movementsForPokemon;
    }

    public String getPokedexIndexByName(String name){
        return String.valueOf(pokedex.get(name).getPokedexIndex());
    }

    public void generateRandomSelectionPokemon(String trainerEscogido){
        Random random = new Random();
        ArrayList<String> generalLista = new ArrayList<>(pokedex.keySet());
        ArrayList<String> pokemonesEscogidos = new ArrayList<>();
        int count = 0;
        while (count < 6){
            int number = random.nextInt(generalLista.size());
            String pokemonSeleccionado = generalLista.get(number);
            try{
                ArrayList<Movement> p = generateRandomMovementForPokemon(pokedex.get(pokemonSeleccionado));
                addNewPokemon(trainerEscogido, generalLista.get(number), p.get(0), p.get(1), p.get(2), p.get(3));
                pokemonesEscogidos.add(pokemonSeleccionado);
                count++;
            }catch(PoobkemonException e){
                e.getMessage();
            }
        }
        try{
            inicialTrainerPokemon(trainerEscogido,pokemonesEscogidos.get(0));
        }catch (PoobkemonException h){
            h.getMessage();
        }
    }

    public void deleteActualListOfPokemons(String trainerEscogido){
        getTrainer(trainerEscogido).clearPokemons();        
    }


    public String getFirstPokemonOfThelist(ArrayList<String> pokemonesEscogidos){
        return pokemonesEscogidos.get(0);
    }
    public ArrayList<String> generateRandomMovementForPokemon(String pok) throws PoobkemonException{
        if (!pokedex.containsKey(pok)) throw new PoobkemonException(PoobkemonException.POKEMON_NOT_FOUND);
        ArrayList<Movement> movements = generateRandomMovementForPokemon(pokedex.get(pok));
        ArrayList<String> movementNames = new ArrayList<>();
        for (Movement m : movements) {
            movementNames.add(m.getName());
        }
        return movementNames;
    }

    public ArrayList<Movement> generateRandomMovementForPokemon(Pokemon pok) throws PoobkemonException{
        Random random = new Random();
        ArrayList<Movement> listaMovimientos = getValidMovements(pok);
        ArrayList<Movement> listRandom = new ArrayList<>();
        while (listRandom.size() < 4){
            int number = random.nextInt(listaMovimientos.size());
            if (!listRandom.contains(listaMovimientos.get(number)))listRandom.add(listaMovimientos.get(number));
        }
        if (listRandom.size()!=4) throw new PoobkemonException(PoobkemonException.CANT_ADD_MOVEMENT);
        return listRandom;
    }

    public HashMap<String, ArrayList<String>> infoTrainer(String nameTrainer){
    	HashMap<String,ArrayList<String>> informacion = new HashMap<>();
    	Trainer trainer = getTrainer(nameTrainer);
    	Inventory invent = trainer.getInventory();
    	ArrayList<Pokemon> lista = invent.getAlivePokemons();
    	for (int i = 0; i < lista.size(); i++) {
    		informacion.put(lista.get(i).getName(), prueba(lista.get(i).getMovements()));
    	}
    	return informacion;
    }
    
    public ArrayList<String> prueba(ArrayList<Movement> movimientos){
    	ArrayList<String> p = new ArrayList<>();
    	for (int i = 0; i < movimientos.size(); i++) {
    		p.add(movimientos.get(i).getName());
    	}
    	return p;
    }

    public ArrayList<String> getPokemonAlives(String nameTrainer){
    	Trainer trainer = getTrainer(nameTrainer);
    	Inventory invent = trainer.getInventory();
    	ArrayList<Pokemon> lista = invent.getAlivePokemons();
    	ArrayList<String> listaVivos = new ArrayList<>();
    	for (int i = 0; i < lista.size(); i++) {
    		listaVivos.add(lista.get(i).getName());
    	}
    	return listaVivos;
    }

    public void startTurnTimer(){
        battle.inicializateTime();
    }
    public void reduceTimeBattle(){
        battle.reduceTimeBattle();
    }
    public int getTurnTimer() {
        return battle.getTurnTimer();
    }

    public void endBattle(){
        battle.endBattle();
        BattleLog.getInstance().clear();
        battle = null;
    }
    public String getLastMessage() {
        return BattleLog.getInstance().getLastMessage();
    }
    public int getLastDamage(){
        return BattleLog.getInstance().getLastDamage();
    }

    /**
     * Saves the current game state to a file
     * @param fileName Name of the file to save to
     */
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
    
    
    /**
     * Saves the current game state to the default file
     */
    public void serializateGame(){
        String fileName = "gameData";
        serializateGame(fileName);
    }

    public Battle deserializateBattle(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("El archivo " + fileName + " no existe.");
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object deserializedObject = in.readObject();
            if (deserializedObject instanceof Battle) {
                battle = (Battle) deserializedObject;
                System.out.println("Juego cargado exitosamente desde " + fileName);
                return battle;
            } else {
                System.err.println("El archivo no contiene un objeto de tipo battle.");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase no encontrada durante la deserialización: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Loads a game state from a file
     * @param fileName Name of the file to load from
     * @return Loaded POOBkemon object, or null if loading fails
     */
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

    /**
     * Loads a game state from the default file
     * @return Loaded POOBkemon object, or null if loading fails
     */
    public POOBkemon deserializateGame(){
        String fileName = "gameData";
        return deserializateGame(fileName);
    }
}