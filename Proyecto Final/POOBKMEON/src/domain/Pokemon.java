package domain;
import java.io.Serializable;
import java.util.*;

public class Pokemon implements Serializable {
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
    private ArrayList<TributeEffect> tributeEffects;
    private StatusEffect statusEffect;
    //Por defecto todos los pokemones tienen este movimiento
    private Movement struggle = new Movement("Struggle","A desperate attack that also hurts the user",0,50,100,getPrincipalType(),0);

    // ATRIBUTO AFECTADO POR ALGO -> NO PUEDO HACER NINGUN MOVIMIENTO YO NO PUEDO HACER NINGÚN OTRO MOVIMIENTO
    // HASTA QUE EL TIEMPO DE EL ESTADO < 0

    

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
        tributeEffects = new ArrayList<>();
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
    
    public ArrayList<TributeEffect> getTributeEffects(){
        return tributeEffects;
    }

    public void setMovements( Movement[] newMovements){
        ArrayList<Movement> list = new ArrayList<>();
        for (Movement m:newMovements){
            list.add(m);
        }
        movements = list;
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
    public ArrayList<MovementTribute> gettTributeMovements() {
        ArrayList<MovementTribute> tributeMovements = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof MovementTribute) {
                tributeMovements.add((MovementTribute) movement);
            }
        }
        return tributeMovements;
    }
    
    public ArrayList<MovementTribute> getMovementsGiveDefense() {
        ArrayList<MovementTribute> stateMovements = gettTributeMovements();
        ArrayList<MovementTribute> deffenseGivers = new ArrayList<>();

        for (MovementTribute stateMov : stateMovements) {
            if (stateMov.getStateTo().containsKey("Defense")) {
                deffenseGivers.add(stateMov);
            }
        }
        return deffenseGivers;
    }

    public ArrayList<MovementTribute> getMovementsGiveAttack() {
        ArrayList<MovementTribute> stateMovements = gettTributeMovements();
        ArrayList<MovementTribute> attackGivers = new ArrayList<>();
        for (MovementTribute stateMov : stateMovements) {
            if (stateMov.getStateTo().containsKey("Attack")) {
                attackGivers.add((MovementTribute) stateMov);
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
    public void increaseStat(String stat, int amount ){
        //mirar pues
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
    public void gainSpecialDefense(int plusSpecialDefense){
        specialDefense += plusSpecialDefense;
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

    public void addEffect(TributeEffect effect) throws PoobkemonException{
        if(!tributeEffects.contains(effect)) throw new PoobkemonException(PoobkemonException.INVALID_EFFECT);
        tributeEffects.add(effect);
    }
    public void addEffect(StatusEffect effect) throws PoobkemonException{
        if(!statusEffect.isOver()) throw new PoobkemonException(PoobkemonException.INVALID_EFFECT);
        statusEffect = effect;
    }

    public void affectPokemonStatus(){
        for(TributeEffect st: tributeEffects){
            st.affectPokemon(this);
        }
    }
    
    public void useMovement(Movement movimiento, Pokemon target) throws PoobkemonException{
        if (!isAlive) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if (!movements.contains(movimiento)) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);
        if (statusEffect != null) throw new PoobkemonException(PoobkemonException.CANT_DO_MOVEMENT);
        if (dontHavePPForAllMovement()){actionF(target);}
        if (movimiento.getType().getTypeMov() == "Fisico")  movimiento.doAttackTo(this, target, attack, target.getDefense());
        else if (movimiento.getType().getTypeMov() == "Especial")  movimiento.doAttackTo(this, target, specialAttack,target.getSpecialDefense());
        else{
            movimiento.doAttackTo(this, target, attack, target.getDefense()); //serian los effectos?? movimientos de estado
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

    public ArrayList<Movement> specialsMovements(){
        ArrayList<Movement> temp = new ArrayList<>();
        for(Movement m: movements){
            if (m.canMakeMove() && m.getType().getTypeMov() == "Especial"){
                temp.add(m);
            }
        }
        return temp;
    }
    public Movement aleatoryMovement(Pokemon target){
        ArrayList<Movement> temp = movementsUsables();
        Random random = new Random();
        int ramdomNum = random.nextInt(temp.size());
        Movement aleatoryMovement = temp.get(ramdomNum);
        return aleatoryMovement;
    }


    public void limitOfTime(){ //cuando no hace algo en 20 s
        for(Movement m: specialsMovements()){
            try{m.losePP();}
            catch(PoobkemonException e){}
        }
    }

    public boolean dontHavePPForAllMovement(){
        for (Movement m : movements){
            if (m.getPP() > 0){return false;}
        }
        return true;
    }

    public void actionF(Pokemon target){
        struggle.AttackToStruggle(this,target);
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

