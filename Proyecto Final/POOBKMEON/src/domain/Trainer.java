package domain;

public class Trainer {
    protected String name;
    protected Inventory inventory;

    protected String color; //mirar despues

    protected Pokemon actualPokemon;


    public Trainer(String newName) {
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

    public void useItem(Item item) throws PoobkemonException{
        //if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        item.useItem(actualPokemon);
    }
    public void doMovement(Movement mov){}

    public void changePokemon(Pokemon pokemon) throws PoobkemonException{
        if (!inventory.contains(pokemon)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        actualPokemon = pokemon;
    }

}
