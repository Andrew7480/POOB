package domain;

import java.io.Serializable;
import java.util.*;

public abstract class Effect implements Serializable{

    protected String name;
    protected String description;
    protected int times;



    public Effect(String newName, String newDescription, int newTimes){
        name = newName;
        description = newDescription;
        times = newTimes;
    }

    public abstract void affectPokemon(Pokemon affectPokemon);
    

    public void decrementDuration() {
        if (times > 0) {
            times--;
        }
    }

    public boolean isOver() {
        return times <= 0;
    }

    public String getName(){
        return name;
    }
}
