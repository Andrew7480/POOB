package domain;

public class EffectDefense extends StatusEffect{


    public EffectDefense(String newName, String newDescription, int newTimes, int newStatus){
        super(newName,newDescription,newTimes,newStatus);
    }
    public void affectPokemon(Pokemon affectPokemon){
        affectPokemon.gainDefense(status);
    }
}