package domain;

public abstract class Trainer {
    protected String name;
    protected Inventory inventory;
    protected String color; //mirar despues
    protected Pokemon actualPokemon;


    public Trainer(String newName) {
        name = newName;   
    }

    public String getName(){
        return name;
    }
    public void pokemonMovement(Movement mov, Pokemon target) throws PoobkemonException{
        actualPokemon.useMovement(mov, target);
    }
    public abstract void changePokemon(Pokemon newPokemon) throws PoobkemonException;
    
    public abstract void useItem(Item item) throws PoobkemonException;
    
    
}
