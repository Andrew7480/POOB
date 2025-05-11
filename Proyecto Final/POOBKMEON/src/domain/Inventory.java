package domain;

import java.io.Serializable;
import java.util.*;

public class Inventory implements Serializable{
    private final int capacityOfItems = 20;
    private HashMap<Item, Integer> items = new HashMap<>();
    private TreeMap<String, Pokemon> pokemons = new TreeMap<>();

    public Inventory() {
    }

    public void addItem(Item item) throws PoobkemonException{
        //if (items.containsKey(item)) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        if (items.size() > capacityOfItems) throw new PoobkemonException(PoobkemonException.EXCESS_CAPACITY_OF_ITEMS);
        if (items.containsKey(item) && countItems(item) >= 2) throw new PoobkemonException(PoobkemonException.EXCESS_CAPACITY_OF_ITEMS);
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    public int countItems(Item item) throws PoobkemonException{
        if (!items.containsKey(item)) throw new PoobkemonException(PoobkemonException.ITEM_DONT_EXIST);
        return items.get(item);
    }

    private void delItem(Item item) throws PoobkemonException{
        if (!items.containsKey(item)) throw new PoobkemonException(PoobkemonException.ITEM_DONT_EXIST);
        Integer count = items.get(item);
        if (count <= 0) throw new PoobkemonException(PoobkemonException.ITEM_DONT_EXIST);
        if (count == 1) {
            items.remove(item); 
        } else {
            items.put(item, count - 1);
        }

    }

    public void addPokemon(Pokemon pokemon) throws PoobkemonException{
        if (pokemons.containsValue(pokemon)) throw new PoobkemonException(PoobkemonException.POKEMON_ALREADY_EXIST_IN_THE_INVENTORY);
        pokemons.put(pokemon.getName(), pokemon);
    }
    public boolean contains(Pokemon pokemon){
        return pokemons.containsKey(pokemon.getName());
    }
    
    public boolean contains(Item item){
        return items.containsKey(item);
    }
    public Pokemon getPokemonByName(String name) throws PoobkemonException{
        for (Map.Entry<String, Pokemon> entry :pokemons.entrySet()){
            if (name.equals(entry.getKey())) return entry.getValue();
        }
        throw new  PoobkemonException(PoobkemonException.POKEMON_NOT_FOUND);
    }
    public Item getItemByName(String name) throws PoobkemonException{
        for (Item i : items.keySet()){
            if (name.equals(i.getName())) return i;
        }
        throw new  PoobkemonException(PoobkemonException.ITEM_NOT_FOUND);
    }

    public TreeMap<String,Pokemon> getPokemons(){
        return pokemons;
    }
    public boolean canStillFighting(){ // prodria tambien verificar si no toiene revivir
        return getAlivePokemons().size() > 0;
    }
    public ArrayList<Pokemon> getAlivePokemons(){
        ArrayList<Pokemon> poke= new ArrayList<Pokemon> ();
        for(Pokemon p: pokemons.values()){
            if(p.isAlive()) poke.add(p);
        }
        return poke;
    }

    public ArrayList<Pokemon> getAlivePokemons(Pokemon pok){
        ArrayList<Pokemon> poke= new ArrayList<Pokemon> ();
        for(Pokemon p: pokemons.values()){
            if(p.isAlive() && !p.equals(pok)) poke.add(p);
        }
        return poke;
    }


    public boolean canChange(Pokemon pokemon){
        return pokemon.isAlive() && pokemons.containsValue(pokemon);
    }

    public void useItem(Pokemon pokemon,Item item) throws PoobkemonException{
        if (! items.containsKey(item.getName())) throw new PoobkemonException(PoobkemonException.ITEM_NOT_FOUND);
        if (! pokemons.containsKey(pokemon.getName())) throw new PoobkemonException(PoobkemonException.POKEMON_DOESNT_EXIST_IN_THE_INVENTORY_OR_NOT_EXIST);
        item.useItem(pokemon);
        items.remove(item.getName());
    }
    public void useItem(Pokemon pokemon,String item) throws PoobkemonException{
        useItem(pokemon, getItemByName(item));
    }

    public ArrayList<String> getItemsArray(){
        ArrayList<String> temp = new ArrayList<>();
        for(Item i: items.keySet()){
            temp.add(i.getName());
        }
        return temp;
    }

}
