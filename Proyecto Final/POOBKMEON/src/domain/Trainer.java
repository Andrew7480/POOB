package domain;

import java.io.Serializable;

public abstract class Trainer implements Serializable{
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
    public String getColor(){
        return color;
    }
    public Pokemon getPokemonInUse(){
        return actualPokemon;
    }
    public boolean canStillFighting(){
        return inventory.canStillFighting();
    }

    public void pokemonMovement(Movement mov, Pokemon target) throws PoobkemonException{
        actualPokemon.useMovement(mov, target);
    }
    //public abstract Movement getMovementTodo();

    public abstract void changePokemon(Pokemon newPokemon) throws PoobkemonException;
    
    public abstract void useItem(Item item) throws PoobkemonException;
    
    public abstract Movement decide(Pokemon pok);

    @Override
    public boolean equals(Object ob){
        return equals((Trainer) ob);
    }

    public boolean equals(Trainer trainer){
        return name.equals(trainer.getName()) && color.equals(trainer.getColor());
    }
}
