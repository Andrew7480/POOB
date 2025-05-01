package domain;

public class EffectDefense extends StatusEffect{


    public EffectDefense(String newName, String newDescription, int newTimes, int newStatus){
        super(newName,newDescription,newTimes,newStatus);
        stateTo = "Defense";
    }
    public void affectPokemon(Pokemon affectPokemon){
        affectPokemon.gainDefense(status);
    }
}