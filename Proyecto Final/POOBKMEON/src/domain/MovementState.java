package domain;

public class MovementState extends Movement{
    private int status;
    private int turns;
    private StatusEffect state;

    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, int newStatus, int newTurns, StatusEffect estado){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT);
        status = newStatus;
        turns = newTurns;
        state = estado;
    }
    
    @Override
    public int doAttack(Pokemon attacker, Pokemon target, int attack) throws PoobkemonException{
        target.addEffect(state);
        return 0;
    }
}