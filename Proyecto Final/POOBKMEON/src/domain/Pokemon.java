package domain;
import java.util.*;

public class Pokemon{
    private String name;
    private int level;
    private final int maxPs;
    private int ps;
    private int attack;
    private int specialAttack;
    private int specialDefense;
    private int defense;
    private int velocity;
    private PokemonType principalType;
    private PokemonType secondaryType;

    private boolean isAlive = true;
    private boolean usedReviveItem = false;

    private ArrayList<Movement> movements;
    private ArrayList<StatusEffect> effects;

    public Pokemon(String newName, int newLevel, int newPs, int newAttack, int newSpecialAttack, int newDefense,int newSpecialDefense, int newVelocity, PokemonType newPrincipalType, PokemonType newSecondaryType) {
        name = newName;
        level = newLevel;
        ps = newPs;
        maxPs = newPs;
        attack = newAttack;
        specialAttack = newSpecialAttack;
        defense = newDefense;
        velocity = newVelocity;
        principalType = newPrincipalType;
        secondaryType = newSecondaryType;
        specialDefense = newSpecialDefense;
        movements = new ArrayList<>();
        effects = new ArrayList<>();
    }
    public int getPs(){
        return ps;
    }
    
    public int getVelocity(){
        return velocity;
    }

    public int getSpecialAttack(){
        return specialAttack;
    }

    public String getName() {
        return name;
    }
    public PokemonType getPrincipalType(){
        return principalType;
    }
    public PokemonType getSecondaryType(){
        return secondaryType;
    }    
    public int getLevel(){
        return level;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    public int getSpecialDefense(){
        return specialDefense;
    }
    public ArrayList<Movement> getMovements(){
        return movements;
    }

    public ArrayList<MovementState> getStateMovements() {
        ArrayList<MovementState> stateMovements = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof MovementState) {
                stateMovements.add((MovementState) movement);
            }
        }
        return stateMovements;
    }
    public ArrayList<MovementState> getStateMovementsGiveDefense() {
        ArrayList<MovementState> stateMovements = getStateMovements();
        ArrayList<MovementState> deffenseGivers = new ArrayList<>();

        for (MovementState stateMov : stateMovements) {
            if (stateMov.getStatus() instanceof EffectDefense) {
                deffenseGivers.add((MovementState) stateMov);
            }
        }
        return deffenseGivers;
    }

    public ArrayList<MovementState> getStateMovementsGiveAttack() {
        ArrayList<MovementState> stateMovements = getStateMovements();
        ArrayList<MovementState> attackGivers = new ArrayList<>();
        for (MovementState stateMov : stateMovements) {
            if (stateMov.getStatus() instanceof EffectAttack) {
                attackGivers.add((MovementState) stateMov);
            }
        }
        return attackGivers;
    }


    public void revivedByItem() throws PoobkemonException{
        if ( isAlive || usedReviveItem) throw new PoobkemonException(PoobkemonException.ITEM_NOT_USABLE);
        ps = (int) maxPs/2;
        isAlive = true;
        usedReviveItem = true;
    }
    public boolean haveUsedReviveItem(){
        return usedReviveItem;
    }

    public void losePS(double losePs){
        ps -= (int)losePs;
        if (ps<=0) pokemonKO();
    }
    private void  pokemonKO(){
        ps = 0;
        isAlive = false;
    }
    
    public void gainPS(int gainPs){
        ps += gainPs; 
    }
    

    public void gainAttack(int newAttack){  // pociones
        attack += newAttack;
    }
    public void loseAttack(int newAttack) throws PoobkemonException{ //estados
        if (newAttack < 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        if (attack - newAttack <= 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        attack -= newAttack;
    }


    public void gainSpecialAttack(int plusSpecialAttack){
        specialAttack += plusSpecialAttack;
    }
    public void loseSpecialAttack(int minusSpecialAttack) throws PoobkemonException{
        if (minusSpecialAttack < 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        if (specialAttack - minusSpecialAttack <= 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        specialAttack -= minusSpecialAttack;
    }
    public void gainDefense(int plusDefense){
        defense += plusDefense;
    }
    public void loseDefense(int minusDefense) throws PoobkemonException{
        if (minusDefense < 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        if (defense - minusDefense <= 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        defense -= minusDefense;
    }
    public void gainVelocity(int plusVelocity){
        velocity += plusVelocity;
    }
    public void loseVelocity(int minusVelocity) throws PoobkemonException{
        if (minusVelocity < 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        if (velocity - minusVelocity <= 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        velocity -= minusVelocity;
    }

    public void addEffect(StatusEffect Effect) throws PoobkemonException{
        if(!effects.contains(Effect)) throw new PoobkemonException(PoobkemonException.INVALID_EFFECT);
        effects.add(Effect);
    }


    public void prepare(){
        for(StatusEffect st: effects){
            st.affectPokemon(this);
        }
    }
    
    public void useMovement(Movement movimiento, Pokemon target) throws PoobkemonException{
        if (!isAlive) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if (!movements.contains(movimiento)) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);

        if (movimiento.getType().getTypeMov() == "Fisico")  movimiento.doAttackTo(this, target, attack);
        else if (movimiento.getType().getTypeMov() == "Especial")  movimiento.doAttackTo(this, target, specialAttack);
        else{
            movimiento.doAttackTo(this, target, attack); //serian los effectos??
        }
    }
    public ArrayList<Movement> movementsUsables(){
        ArrayList<Movement> temp = new ArrayList<>();
        for(Movement m: movements){
            if (m.canMakeMove()){
                temp.add(m);
            }
        }
        return temp;
    }


    public void useAleatoryMovement(Pokemon target){
        ArrayList<Movement> temp = movementsUsables();
        Random random = new Random();
        int ramdomNum = random.nextInt(temp.size());

        Movement aleatoryMovement = temp.get(ramdomNum);

        try {useMovement(aleatoryMovement, target);}
        catch(PoobkemonException e){
            //nse
        }
    }
    @Override
    public boolean equals(Object ob){
        return equals((Pokemon) ob);
    }

    public boolean equals(Pokemon pokemon){
        return name.equals(pokemon.getName()) &&
           level == pokemon.getLevel() &&
           ps == pokemon.getPs() &&
           attack == pokemon.getAttack() &&
           specialAttack == pokemon.getSpecialAttack() &&
           defense == pokemon.getDefense() &&
           velocity == pokemon.getVelocity() &&
           principalType.equals(pokemon.getPrincipalType()) &&
           (secondaryType == null ? pokemon.getSecondaryType() == null : secondaryType.equals(pokemon.getSecondaryType()));
    }
}

