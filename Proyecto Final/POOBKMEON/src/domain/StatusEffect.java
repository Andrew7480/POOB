package domain;


public class StatusEffect extends Effect{
    protected double probabilidad;

    public StatusEffect(String newName, String newDescription, int newTimes, double prob){
        super(newName,newDescription,newTimes);
        prob = probabilidad;
    }

    public void affectPokemon(Pokemon affectPokemon) throws PoobkemonException{
        decrementDuration();
        if (Math.random() * 100 <= probabilidad) {
            throw new PoobkemonException(PoobkemonException.POKEMON_CANT_INTERACT);
        }
        //que se termine el estado
        affectPokemon.removeStatusEffect();
        }
}


