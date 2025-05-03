package domain;

import java.io.Serializable;
import java.util.HashMap;

public class MovementTribute extends Movement{
    private HashMap<String,Integer> stateTo = new HashMap<>();
    private TributeEffect state;
    private int damageBase = 0;

    public MovementTribute(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, TributeEffect estado, int newpPriority){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT, newpPriority);
        state = estado;
    }

    public Effect getStatus(){
        return state;
    }
    public HashMap<String,Integer> getStateTo(){
        return stateTo;
    }


    @Override
    public int doAttackTo(Pokemon attacker, Pokemon target, int attack, int defenseTarget) throws PoobkemonException{
        if (Math.random() * 100 > precision) {  //tiene prob de ocurri o no 
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        target.addEffect(state);
        return damageBase;
    }
    @Override
    public Movement coyy(){
        return new MovementTribute(name, description, PP, power, precision, type, state, priority);
    }
}
