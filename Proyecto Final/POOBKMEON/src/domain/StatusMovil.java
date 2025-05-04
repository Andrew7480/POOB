package domain;
public class StatusMovil extends StatusEffect{
    private TributeEffect tribute;

    public StatusMovil(String newName, String newDescription, int newTimes, TributeEffect newTribute, double prob){
        super(newName,newDescription,newTimes,prob);
        tribute = newTribute;
    }
    @Override
    public void affectPokemon(Pokemon pokemon) throws PoobkemonException{
        if (Math.random() * 100 <= probabilidad) {
            decrementDuration();
            tribute.affectPokemon(pokemon);
            throw new PoobkemonException(PoobkemonException.POKEMON_CANT_INTERACT);
        }
        decrementDuration();
        tribute.decrementDuration();
        throw new PoobkemonException(PoobkemonException.POKEMON_NOT_AFFECTED_BY_STATUS);
    }
}