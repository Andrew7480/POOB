package domain;

import java.io.Serializable;

public class Revive extends Item{
    private final double recover;
    public Revive() {
        super("revive","Recupera 50% de la salud de un Pokémon caído en combate.");
        recover = 0.5;
    }

    @Override
    public void useItem(Pokemon pokemon) throws PoobkemonException { 
        if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if(pokemon.isAlive() || pokemon.haveUsedReviveItem()) throw new PoobkemonException(PoobkemonException.POKEMON_IS_ALIVE_OR_THE_REVIVED_ITEM_HAS_ALREADY_BEEN_USED);
        pokemon.revivedByItem(recover);
    }

    
}
