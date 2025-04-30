package domain;
public class MovementState extends Movement{
    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT);
    }

    
    @Override
    public int doAttack(Movement movimiento,Pokemon attacker, Pokemon target, int attack){
        return 0;
    }
}