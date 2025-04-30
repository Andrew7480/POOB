package domain;

public abstract class Trainer {
    protected String name;
    protected Inventory inventory;

    Trainer(String newName) {
        name = newName;
    }

    public void decide(Pokemon pokemon, Movement movement, Pokemon target) throws PoobkemonException{
        if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if(movement == null) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);
        if(target == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        pokemon.useMovement(movement, target);
    }

    public String getName(){
        return name;
    }

    public void usePotion(Pokemon pokemon, Potion potion) throws PoobkemonException{
        if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        potion.useItem(pokemon);
    }

}
