package domain;
import java.io.Serializable;
import java.util.*;

public class Pokemon implements Serializable {
    private String name;
    private int level;
    private int pokedexIndex;
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
    private Movement struggle = new PhysicalMovement("Struggle","A desperate attack that also hurts the user",0,50,100,getPrincipalType(),0);

    // ATRIBUTO AFECTADO POR ALGO -> NO PUEDO HACER NINGUN MOVIMIENTO YO NO PUEDO HACER NINGÚN OTRO MOVIMIENTO
    // HASTA QUE EL TIEMPO DE EL ESTADO < 0

    

    public Pokemon(String newName, int newLevel, int newPs, int newAttack, int newSpecialAttack, int newDefense,int newSpecialDefense, int newVelocity, PokemonType newPrincipalType, PokemonType newSecondaryType,int index) {
        name = newName;
        level = newLevel;
        pokedexIndex = index;
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
    public Integer getPokedexIndex(){
        return pokedexIndex;
    }

    public int getPs(){
        return ps;
    }
    public int getMaxPs(){
        return maxPs;
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
    public ArrayList<String> getMovementsString(){
        ArrayList<String> movementNames = new ArrayList<>();
        for (Movement movement : movements) {
            movementNames.add(movement.getName());
        }
        return movementNames;
    }

    public ArrayList<TributeEffect> getTributeEffects(){
        return tributeEffects;
    }

    //AÑADIR MOVIMIENTOS QUE SEAN DIFERENTES A LA DEBILIDAD DEL POKEMON?
    /*public void onlyAddValidMovements(){
        HashSet<Movement> movementsDontValid = new HashSet<>(movementsWeaksForMe());
        for (int i = 0; i < movements.size(); i++){
            if(!movementsDontValid.contains(movements.get(i))){
                addMovement(movements.get(i));
            }
        }
    }*/

    public void setMovements( Movement[] newMovements){
        ArrayList<Movement> list = new ArrayList<>();
        for (Movement m : newMovements){
            try {
                if (!movements.contains(m)) {
                    list.add(m);
                }
            } catch(Exception e){
                LogPOOBKEMON.record(e);
            }
        }
        movements = list;
    }

    
    public void addMovement(Movement mov) throws PoobkemonException{
        HashSet<Movement> movementsDontValid = new HashSet<>(movementsWeaksForMe());
        if (movements.contains(mov) || movementsDontValid.contains(mov)) {
            throw new PoobkemonException(PoobkemonException.CANT_ADD_MOVEMENT);
        }
        movements.add(mov);
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


    public void revivedByItem(double recover) throws PoobkemonException{
        if ( isAlive || usedReviveItem) throw new PoobkemonException(PoobkemonException.THE_POKEMON_IS_ALIVE_OR_REVIVE_CANT_BE_USE);
        ps = (int) (maxPs*recover);
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
        if(!tributeEffects.contains(effect)) throw new PoobkemonException(PoobkemonException.INVALID_EFFECT_TRIBUTE_EFFECT);
        tributeEffects.add(effect);
    }
    public void addEffect(StatusEffect effect) throws PoobkemonException{
        if(!statusEffect.isOver()) throw new PoobkemonException(PoobkemonException.INVALID_EFFECT_STATUS_EFFECT);
        statusEffect = effect;
    }

    public void affectPokemonStatus() throws PoobkemonException{
        for(TributeEffect st: tributeEffects){
            st.affectPokemon(this);
        }
    }
    
    public void useMovement(Movement movimiento, Pokemon target) throws PoobkemonException{
        if (!isAlive) throw new PoobkemonException(PoobkemonException.POKEMON_DIE);
        if (!movements.contains(movimiento)) throw new PoobkemonException(PoobkemonException.POKEMON_DONT_HAVE_THESE_MOVEMENT);
        //if (statusEffect != null) throw new PoobkemonException(PoobkemonException.CANT_DO_MOVEMENT);
        //statusEffectVerify();
        if (dontHavePPForAllMovement()){actionF(target);}
        System.out.println(movimiento);
        movimiento.doAttackTo(this, target);
    }
    public void useMovement(String movimiento, Pokemon target) throws PoobkemonException{
        for (Movement m : movements){
            if(m.getName().equals(movimiento)) {
                useMovement(m, target);
                return;
            }
        }
        throw new PoobkemonException(PoobkemonException.MOVEMENT_NOT_FOUND);
    }

    public void statusEffectVerify() throws PoobkemonException{
        if (statusEffect == null) throw new PoobkemonException(PoobkemonException.NOT_STATUS_EFFECT);
        statusEffect.affectPokemon(this);
    }
    public void removeStatusEffect(){
        statusEffect = null;
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

    /*public ArrayList<Movement> specialsMovements(){
        ArrayList<Movement> temp = new ArrayList<>();
        for(Movement m: movements){
            if (m.canMakeMove() && m.getClass().getSimpleName().equals("SpecialMovement")){
                temp.add(m);
            }
        }
        return temp;
    }*/

   public void limitOfTime(){
    for (Movement move: movements){
        move.limitOfTime();
    }
   }


    public Movement aleatoryMovement(Pokemon target){
        ArrayList<Movement> temp = movementsUsables();
        Random random = new Random();
        int ramdomNum = random.nextInt(temp.size());
        Movement aleatoryMovement = temp.get(ramdomNum);
        return aleatoryMovement;
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

    public boolean equals(Pokemon pokemon){  //mirar caso null
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

    
    public ArrayList<Movement> movementsWeaksForMe(){
        ArrayList<Movement> movementsWeak = new ArrayList<>();
        for (int i = 0; i < movements.size(); i++){
            if (movements.get(i).getMultiplicator(principalType) > 1.0){
                movementsWeak.add(movements.get(i));
            }
        }
        return movementsWeak;
    }

    public Pokemon copy(){
        return new Pokemon(name,level,ps,attack,specialAttack,defense,specialDefense,velocity,principalType,secondaryType,pokedexIndex);
    }
    public ArrayList<PokemonType> getTypes(){
        ArrayList<PokemonType> temp = new ArrayList<>();
        temp.add(principalType);
        if (secondaryType != null) temp.add(secondaryType);
        return temp;
    }
    public  String createPokemonForToolTip() {
        return "<html>" +
                "<b style='font-size:12px; color:blue;'>" + name + "</b><br>" +
                "Type: " + getTypes() + "<br>" +
                "Level: " + level + "<br>" +
                "HP: " + ps + "<br>" +
                "Attack: " + attack + "<br>" +
                "Defense: " + defense + "<br>" +
                "Velocity: " +velocity+"<br>"+
                "Special Attack: " +specialAttack+"<br>"+
                "Special Defense: " +specialDefense+"<br>"+
                "</html>";
    }

}

