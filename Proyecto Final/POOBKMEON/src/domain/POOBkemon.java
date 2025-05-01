package domain;
import java.util.TreeMap;
import java.io.*;
/*
 * POOBkemon
 */
public class POOBkemon {
    private TreeMap<String, Pokemon> pokedex = new TreeMap<>();
    private TreeMap<String, Trainer> entrenadores = new TreeMap<>();

    private TreeMap<String, Item> items = new TreeMap<>(); //items for serialization

    //turno actual
    private Trainer turn;
    //son los que estan en la batalla
    private Trainer trainerTurn1;
    private Trainer trainerTurn2;

    //idea 2 y un arreglo de trainser organizado de 2
    private int turno = 1;

    public POOBkemon() {
    }

    public void iniciarJuego(String jugabilidad){  // ya existen pokemones, items, movimientos y estados y/o ytaines defectos
        ///leer el archivo de juego
        /// 
        //while hastya que los todos pokemones de alguno mueran
    }

    public void movementPerformed(Movement mov, Pokemon target) throws PoobkemonException{
        turn.pokemonMovement(mov,target);
    }
    public void actiontrainerTurnoDelTrainerCambiar(Pokemon pok) throws PoobkemonException{
        turn.changePokemon(pok);
    }
    public void actiontrainerTurnoInventario(Item item) throws PoobkemonException{
        turn.useItem(item);
    }
    public void actuinHuir() throws PoobkemonException{
        //reset
    }

    public void iniciateGameDefault() { // esto seria tambien para serializar
        String fileName = "machineTrainer.txt";
        Trainer playerTrainer = new PlayerTrainer("Player");
        Trainer machineTrainer = new DefensiveTrainer("Machine");
        Trainer machineTrainerExpertTrainer = new ExpertTrainer("Machine");
        Trainer machineChangingTrainer = new ChangingTrainer("Machine");
        Trainer machineAttackingTrainer = new AttackingTrainer("Machine");
        entrenadores.put(playerTrainer.getName(), playerTrainer);
        entrenadores.put(machineTrainer.getName(), machineTrainer);
        entrenadores.put(machineTrainerExpertTrainer.getName(), machineTrainerExpertTrainer);
        entrenadores.put(machineChangingTrainer.getName(), machineChangingTrainer);
        entrenadores.put(machineAttackingTrainer.getName(), machineAttackingTrainer);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(playerTrainer);
            out.writeObject(machineTrainer);
            out.writeObject(machineTrainerExpertTrainer);
            out.writeObject(machineChangingTrainer);
            out.writeObject(machineAttackingTrainer);
            out.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deserializateGame(){
        String fileName = "machineTrainer.txt";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Trainer playerTrainer = (PlayerTrainer) in.readObject();
            Trainer machineTrainerDefensive = (DefensiveTrainer) in.readObject();
            Trainer machineTrainerExpertTrainer = (ExpertTrainer) in.readObject();
            Trainer machineChangingTrainer = (ChangingTrainer) in.readObject();
            Trainer machineAttackingTrainer = (AttackingTrainer) in.readObject();
            in.close();
            entrenadores.put(playerTrainer.getName(), playerTrainer);
            entrenadores.put(machineTrainerDefensive.getName(), machineTrainerDefensive);
            entrenadores.put(machineTrainerExpertTrainer.getName(), machineTrainerExpertTrainer);
            entrenadores.put(machineChangingTrainer.getName(), machineChangingTrainer);
            entrenadores.put(machineAttackingTrainer.getName(), machineAttackingTrainer);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public TreeMap<String, Item> getItems() {
        return items;
    }


    public void addPokemon(String name, Pokemon pokemon) {
        pokedex.put(name, pokemon);
    }
    public void addTrainer(String name, Trainer trainer) {
        entrenadores.put(name, trainer);
    }
    public Pokemon getPokemon(String name) {
        return pokedex.get(name);
    }
    public Trainer getTrainer(String name) {
        return entrenadores.get(name);
    }
    public TreeMap<String, Pokemon> getPokedex() {
        return pokedex;
    }

    public void iniciateItemsForSerialization(){
        String fileName = "itemsJuego.txt";
        DefensePotion hyperDefensePotion = new DefensePotion("Defense Potion", "Give a pokemon defense points", PotionType.HYPER_DEFENSE);
        AttackPotion hyperAttackPotion = new AttackPotion("Attack Potion", "", PotionType.HYPER_ATTACK);
        PsPotion hyperPsPotion = new PsPotion("Ps Potion", "", PotionType.HYPER_PS);

        DefensePotion superDefensePotion = new DefensePotion("Defense Potion", "Give a pokemon defense points", PotionType.SUPER_DEFENSE);
        AttackPotion superAttackPotion = new AttackPotion("Attack Potion", "", PotionType.SUPER_ATTACK);
        PsPotion superPsPotion = new PsPotion("Ps Potion", "", PotionType.SUPER_PS);

        HyperPotion hyperPotion = new HyperPotion("Hyper Potion", "", PotionType.HYPER);
        SuperPotion superPotion = new SuperPotion("Super Potion", "", PotionType.SUPER);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(hyperDefensePotion);
            out.writeObject(hyperAttackPotion);
            out.writeObject(hyperPsPotion);

            out.writeObject(superDefensePotion);
            out.writeObject(superAttackPotion);
            out.writeObject(superPsPotion);

            out.writeObject(hyperPotion);
            out.writeObject(superPotion);

            out.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       /* https://www.geeksforgeeks.org/serialization-in-java/*/ 
    }
    public void deserializateItems(){
        String fileName = "itemsJuego.txt";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            DefensePotion hyperDefensePotion = (DefensePotion) in.readObject();
            AttackPotion hyperAttackPotion = (AttackPotion) in.readObject();
            PsPotion hyperPsPotion = (PsPotion) in.readObject();

            DefensePotion superDefensePotion = (DefensePotion) in.readObject();
            AttackPotion superAttackPotion = (AttackPotion) in.readObject();
            PsPotion superPsPotion = (PsPotion) in.readObject();

            HyperPotion hyperPotion = (HyperPotion) in.readObject();
            SuperPotion superPotion = (SuperPotion) in.readObject();

            in.close();

            items.put(hyperDefensePotion.getName(), hyperDefensePotion);
            items.put(hyperAttackPotion.getName(), hyperAttackPotion);
            items.put(hyperPsPotion.getName(), hyperPsPotion);
            items.put(superDefensePotion.getName(), superDefensePotion);
            items.put(superAttackPotion.getName(), superAttackPotion);
            items.put(superPsPotion.getName(), superPsPotion);
            items.put(hyperPotion.getName(), hyperPotion);
            items.put(superPotion.getName(), superPotion);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
