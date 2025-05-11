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
            System.out.println("No se ha aplicado el efecto por probabilidad.");
            losePP();
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        target.addEffect(state);
        System.out.println( "Movimiento estado:" + damageBase);
        losePP();
        return damageBase;
    }
    
    @Override
    public Movement copy(){
        return new MovementState(name, description, PP, power, precision, type, state, priority, damageBase);
    }
}