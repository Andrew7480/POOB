package domain;

public class EffectPs extends StatusEffect{


    public EffectPs(String newName, String newDescription, int newTimes, int newStatus){
        super(newName,newDescription,newTimes,newStatus);
        stateTo = "Ps";
    }
    public void affectPokemon(Pokemon affectPokemon){
        affectPokemon.gainPS(status);
    }
}