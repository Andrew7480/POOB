package domain;


public class MovementState extends SpecialMovement{
    private StatusEffect state;
    private int damageBase;

    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, StatusEffect estado, int newpPriority, int newDamageBase){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT, newpPriority);
        state = estado;
        damageBase = newDamageBase;
    }

    public Effect getStatus(){
        return state;
    }


    @Override
    public int doAttackTo(Pokemon attacker, Pokemon target) throws PoobkemonException{
        if (Math.random() * 100 > precision) {  //tiene prob de ocurri o no 
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        target.addEffect(state);
        return damageBase;
    }
    
    @Override
    public Movement copy(){
        return new MovementState(name, description, PP, power, precision, type, state, priority, damageBase);
    }
}