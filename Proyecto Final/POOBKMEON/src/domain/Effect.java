package domain;

import java.io.Serializable;

public abstract class Effect implements Serializable{

    protected String name;
    protected String description;
    protected int times;



    public Effect(String newName, String newDescription, int newTimes){
        name = newName;
        description = newDescription;
        times = newTimes;
    }

    public abstract void affectPokemon(Pokemon affectPokemon) throws PoobkemonException;
    

    public void decrementDuration() throws PoobkemonException{
        if (times== 0) throw new PoobkemonException(PoobkemonException.EFFECT_DURATION_OVER);
        times--;
        
    }

    public boolean isOver() {
        return times <= 0;
    }

    public String getName(){
        return name;
    }
}
