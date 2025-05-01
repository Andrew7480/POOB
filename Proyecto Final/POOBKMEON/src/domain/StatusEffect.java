package domain;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StatusEffect extends Effect{
    

        public StatusEffect(String newName, String newDescription, int newTimes){
            super(newName,newDescription,newTimes);
        }

        public StatusEffect(String newName, String newDescription, int newTimes,HashMap<String,Integer> tributes){
            super(newName, newDescription, newTimes, tributes);
        }

        public void affectPokemon(Pokemon affectPokemon){
            for (Map.Entry<String, Integer> entry : stateTo.entrySet()) {
                String stat = entry.getKey();
                int amount = entry.getValue();
                affectPokemon.increaseStat(stat, amount);
            }
        }

}