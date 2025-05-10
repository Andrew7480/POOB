package domain;

import java.awt.Color;

public class PlayerTrainer extends Trainer{

    public PlayerTrainer(String newName, Color newColor) {
        super(newName, newColor);
    }

    public void changePokemon(Pokemon newPokemon) throws PoobkemonException{
        if (!inventory.contains(newPokemon)) throw new PoobkemonException(PoobkemonException.POKEMON_DOESNT_EXIST_IN_THE_INVENTORY_OR_NOT_EXIST);
        setPokemonInUse(newPokemon);
    }
    public void changePokemon(String newPokemon) throws PoobkemonException{
        changePokemon(getPokemonByName(newPokemon));
    }
    
    public void useItem(Item item) throws PoobkemonException{
        inventory.useItem(actualPokemon, item);
    }
    public void useItem(String item) throws PoobkemonException{
        inventory.useItem(actualPokemon, item);
    }

    public Movement decide(Pokemon pokemon){return null;}
}
