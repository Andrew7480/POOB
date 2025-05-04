package domain;

import java.awt.Color;

public class PlayerTrainer extends Trainer{

    public PlayerTrainer(String newName, Color newColor) {
        super(newName, newColor);
    }

    public void changePokemon(Pokemon newPokemon) throws PoobkemonException{
        if (!inventory.contains(newPokemon)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        setPokemonInUse(newPokemon);
    }
    
    public void useItem(Item item) throws PoobkemonException{
        item.useItem(actualPokemon);
    }

    public Movement decide(Pokemon pokemon){return null;}
}
