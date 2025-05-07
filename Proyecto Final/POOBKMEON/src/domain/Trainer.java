package domain;

import java.awt.Color;
import java.io.Serializable;

public abstract class Trainer implements Serializable{
    protected String name;
    protected Inventory inventory;
    protected Color color; //mirar despues
    protected Pokemon actualPokemon;


    public Trainer(String newName, Color newColor) {
        name = newName;   
        color = newColor;
        inventory = new Inventory();
        
    }

    public Inventory getInventory(){
        return inventory;
    }
    public String getName(){
        return name;
    }
    public Color getColor(){
        return color;
    }
    public Pokemon getPokemonInUse(){
        if (actualPokemon != null && actualPokemon.isAlive()){
            return actualPokemon;
        }

        if (inventory != null && !inventory.getAlivePokemons().isEmpty()){
            actualPokemon = inventory.getAlivePokemons().get(0);
            return actualPokemon;
        }

        return null; //CAMBIADO
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
    public void inicialPokemon(String pokemon){
        actualPokemon = inventory.getPokemons().get(pokemon);
    }

    public void addPokemon(Pokemon pokemon) throws PoobkemonException{
        inventory.addPokemon(pokemon);
    }
    public void setInventory(Inventory newInventory){
        inventory = newInventory;
    }

    public void setPokemonInUse(Pokemon pokemonNew){
        if (pokemonNew != null && pokemonNew.isAlive()){
            actualPokemon = pokemonNew;
        }
    }
}
