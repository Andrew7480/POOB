package domain;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StatusEffect extends Effect{
    

    public StatusEffect(String newName, String newDescription, int newTimes){
        super(newName,newDescription,newTimes);
    }


    public void affectPokemon(Pokemon affectPokemon){}
    

}