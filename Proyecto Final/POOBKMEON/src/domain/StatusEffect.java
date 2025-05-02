package domain;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StatusEffect extends Effect implements Serializable{
    

    public StatusEffect(String newName, String newDescription, int newTimes){
        super(newName,newDescription,newTimes);
    }


    public void affectPokemon(Pokemon affectPokemon){}
    
    public Effect copy(){
        return new StatusEffect(name, description, times);
    }

}