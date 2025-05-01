package domain;

public class PlayerTrainer extends Trainer{

    public PlayerTrainer(String newName) {
        super(newName);
    }

    public void changePokemon(Pokemon newPokemon) throws PoobkemonException{
        if (!inventory.contains(newPokemon)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        actualPokemon = newPokemon;
    }
    public void useItem(Item item) throws PoobkemonException{
        item.useItem(actualPokemon);
    }
    
}
