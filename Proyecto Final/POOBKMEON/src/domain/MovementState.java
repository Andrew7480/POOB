package domain;
public class MovementState extends SpecialMovement{
    private StatusEffect state;
    private int damageBase;

    /**
     * Constructor for creating a new MovementState
     * 
     * @param newName The name of the movement
     * @param newDescription A description of what the movement does
     * @param newPP The initial Power Points (PP) available for this movement
     * @param newPower The base power of the movement
     * @param newPrecision The accuracy percentage of the movement
     * @param newPT The elemental type of the movement
     * @param estado The status effect that will be applied to the target
     * @param newpPriority The priority level of the movement in battle
     * @param newDamageBase The base damage dealt by this move (typically low)
     */
    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, StatusEffect estado, int newpPriority, int newDamageBase){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT, newpPriority);
        state = estado;
        damageBase = newDamageBase;
        typeOfMovement = "State";
    }

    /**
     * Gets the status effect associated with this movement
     * 
     * @return The StatusEffect object that this movement applies
     */
    public Effect getStatus(){
        return state;
    }

    /**
     * Executes the status attack from one Pokemon to another.
     * Unlike regular attacks, this primarily applies a status effect to the target
     * rather than dealing significant damage.
     * The attack has a chance to miss based on precision.
     * 
     * @param attacker The Pokemon performing the attack
     * @param target The Pokemon receiving the attack
     * @return The base damage value (usually minimal)
     * @throws PoobkemonException MISSED_MOVEMENT if accuracy check fails
     */
    @Override
    public int doAttackTo(Pokemon attacker, Pokemon target) throws PoobkemonException{
        if (Math.random() * 100 > precision) {  //tiene prob de ocurri o no 
            BattleLog.getInstance().addMessage("No se ha aplicado el efecto por probabilidad.");
            losePP();
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        target.addEffect(state);
        target.losePS(damageBase);
        BattleLog.getInstance().addMessage(attacker.getName()+" ha aplicado un estado a :" +target.getName()+ "Daño: "+ damageBase);
        BattleLog.getInstance().addDamage(damageBase);
        losePP();
        return damageBase;
    }
    
    /**
     * Creates a copy of this MovementState with the same properties
     * 
     * @return A new MovementState object with identical properties
     */
    @Override
    public Movement copy(){
        return new MovementState(name, description, PP, power, precision, type, state, priority, damageBase);
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
                "damageBase: " + damageBase + "<br>" +
                "state " + state.getName() + "<br>" + 
                "</html>";
    }
}