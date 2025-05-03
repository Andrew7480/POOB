package domain;

import java.io.Serializable;

public class Revive extends Item{
    private final double recover;
    public Revive() {
        super("Revive","Recupera 50% de la salud de un Pokémon caído en combate.");
        recover = 0.5;
    }

    @Override
    public void useItem(Pokemon pokemon) throws PoobkemonException { 
        if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if(pokemon.isAlive() || pokemon.haveUsedReviveItem()) throw new PoobkemonException(PoobkemonException.ITEM_NOT_USABLE);
        pokemon.revivedByItem(recover);
    }

    
}
