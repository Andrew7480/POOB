package domain;

import java.util.*;

public class Inventory {
    private int capacity;
    private HashMap<String, Item> items = new HashMap<>();
    private TreeMap<String, Pokemon> pokemons = new TreeMap<>();

    public Inventory(int newCapacity) {
        capacity = newCapacity;
    }

    public void addPokemon(String name) throws PoobkemonException{
        if (!pokemons.containsKey(name)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        pokemons.put(pokemons.get(name).getName(),pokemons.get(name));
    }

    public void addItem(Item item) throws PoobkemonException{
        if (!items.containsValue(item)) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        items.put(item.getName(),item);
    }

    private void delItem(Item item) throws PoobkemonException{
        if (!items.containsValue(item)) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        items.remove(item.getName());
    }

    public void addPokemon(Pokemon pokemon) throws PoobkemonException{
        if (!pokemons.containsValue(pokemon)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        pokemons.put(pokemon.getName(), pokemon);
    }
    public boolean contains(Pokemon pokemon){
        return pokemons.containsKey(pokemon.getName());
    }
    public boolean contains(Item item){
        return items.containsKey(item.getName());
    }

    public TreeMap<String,Pokemon> getPokemons(){
        return pokemons;
    }
}
