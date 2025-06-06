package domain;
public class SpecialMovement extends Movement {
    
    /**
     * Constructor for creating a new SpecialMovement
     * 
     * @param newName The name of the movement
     * @param newDescription A description of what the movement does
     * @param newPP The initial Power Points (PP) available for this movement
     * @param newPower The base power of the movement
     * @param newPrecision The accuracy percentage of the movement
     * @param newType The elemental type of the movement
     * @param newPriority The priority level of the movement in battle
     */
    public SpecialMovement(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newType, int newPriority){
        super(newName, newDescription, newPP, newPower, newPrecision, newType, newPriority);
        typeOfMovement = "Special";
    }

    /**
     * Executes the special attack from one Pokemon to another.
     * The damage calculation uses the attacker's Special Attack and the target's Special Defense.
     * Applies type effectiveness multipliers and random variance to the damage.
     * Reduces PP by one after use.
     * 
     * @param attacker The Pokemon performing the attack
     * @param target The Pokemon receiving the attack
     * @return The amount of damage dealt
     * @throws PoobkemonException CANT_DO_THE_MOVE if PP is zero, MISSED_MOVEMENT if accuracy check fails
     */
    public int doAttackTo(Pokemon attacker, Pokemon target) throws PoobkemonException{
        if (!canMakeMove()) throw new PoobkemonException(PoobkemonException.CANT_DO_THE_MOVE);
        if (Math.random() * 100 > precision) {
            BattleLog.getInstance().addMessage("No se ha hecho el especial por precision.");
            losePP();
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        
        double levelFactor = (2.0 * attacker.getLevel()) / 5.0 + 2.0;
        double attackDefenseRatio = (double) attacker.getSpecialAttack() / target.getSpecialDefense();
        double damage = ((levelFactor * power * attackDefenseRatio) / 50.0) + 2.0;
        damage *= getMultiplicatorAtacck(target.getPrincipalType());
        damage *= 0.85 + (Math.random() * 0.15);
        target.losePS(damage);
        losePP();
        BattleLog.getInstance().addMessage(attacker.getName()+ " ha usado un movimiento especial: "+name+" ha realizado "+(int) damage+ " de daño a " +target.getName());
        BattleLog.getInstance().addDamage((int)damage);
        return (int)damage;
    }

    /**
     * Creates a copy of this SpecialMovement with the same properties
     * 
     * @return A new SpecialMovement object with identical properties
     */
    @Override
    public Movement copy(){
        return new SpecialMovement(name, description, PP, power, precision, type, priority);
    }


    /**
     * Handles the reduction of PP over time
     * Calls the parent class's losePP method and logs any exceptions
     * This method is typically called at regular intervals during gameplay
     */
    @Override
    public void limitOfTime(){
        try{
            super.losePP();
        }catch (PoobkemonException e){
            LogPOOBKEMON.record(e);
        }
    }

    /**
     * Creates an HTML-formatted tooltip description of the Movement
     * 
     * @return HTML string with Movement details for tooltip display
     */
    public String createMovementForToolTip() {
        return "<html>" +
                "<b style='font-size:12px; color:blue;'>" + name + "</b><br>" +
                "Description: " + description + "<br>" +
                "PP: " + PP + "<br>" +
                "Power: " + power + "<br>" +
                "Precision: " + precision + "<br>" +
                "Elemental Type: " + type + "<br>" +
                "Priority: " +priority+"<br>"+
                "Type of Movement: " +typeOfMovement+"<br>"+
                "</html>";
    }


}