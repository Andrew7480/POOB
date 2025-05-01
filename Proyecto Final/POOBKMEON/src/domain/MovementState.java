package domain;

import java.util.HashMap;

public class MovementState extends Movement{
    private StatusEffect state;
    private HashMap<String,Integer> stateTo = new HashMap<>();

    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, StatusEffect estado, int newpPriority){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT, newpPriority);
        state = estado;
        stateTo = state.getStateTo();
    }
    public StatusEffect getStatus(){
        return state;
    }
    public HashMap<String,Integer> getStateTo(){
        return stateTo;
    }
    @Override
    public int doAttackTo(Pokemon attacker, Pokemon target, int attack) throws PoobkemonException{
        if (Math.random() * 100 > precision) {  //tiene prob de ocurri o no 
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        target.addEffect(state);
        return 0;
    }
}