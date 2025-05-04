package domain;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class TributeEffect extends Effect{
    protected HashMap<String,Integer> stateTo = new HashMap<>();

    public TributeEffect(String newName, String newDescription, int newTimes){
        super(newName,newDescription,newTimes);
    }

    public TributeEffect(String newName, String newDescription, int newTimes,HashMap<String,Integer> tributes){
        super(newName, newDescription, newTimes);
        stateTo = tributes;
    }

    public HashMap<String,Integer> getStateTo(){
        return stateTo;
    }

    public void affectPokemon(Pokemon affectPokemon) throws PoobkemonException{
        for (Map.Entry<String, Integer> entry : stateTo.entrySet()) {
            String stat = entry.getKey();
            int amount = entry.getValue();
            affectPokemon.increaseStat(stat, amount);
        }
        decrementDuration();
    }
}
