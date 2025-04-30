package domain;

public class EffectAttack extends StatusEffect{


    public EffectAttack(String newName, String newDescription, int newTimes, int newStatus){
        super(newName,newDescription,newTimes,newStatus);
    }
    public void affectPokemon(Pokemon affectPokemon){
        affectPokemon.gainAttack(status);
    }
}