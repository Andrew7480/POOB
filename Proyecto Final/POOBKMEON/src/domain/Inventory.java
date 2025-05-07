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
        if (items.size() > capacityOfItems) throw new PoobkemonException(PoobkemonException.EXCESS_CAPACITY);
        if (items.containsKey(item) && countItems(item) >= 2) throw new PoobkemonException(PoobkemonException.EXCESS_CAPACITY);
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    public int countItems(Item item) throws PoobkemonException{
        if (!items.containsKey(item)) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        return items.get(item);
    }

    private void delItem(Item item) throws PoobkemonException{
        if (!items.containsKey(item)) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        Integer count = items.get(item);
        if (count <= 0) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        if (count == 1) {
            items.remove(item); 
        } else {
            items.put(item, count - 1);
        }

    }

    public void addPokemon(Pokemon pokemon) throws PoobkemonException{
        System.out.println("hola?");
        if (pokemons.containsValue(pokemon)) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        pokemons.put(pokemon.getName(), pokemon);
        System.out.println(pokemons.put(pokemon.getName(), pokemon));
    }
    public boolean contains(Pokemon pokemon){
        return pokemons.containsKey(pokemon.getName());
    }
    public boolean contains(Item item){
        return items.containsKey(item);
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
        if (! items.containsKey(item.getName())) throw new PoobkemonException(PoobkemonException.INVALID_ITEM);
        if (! pokemons.containsKey(pokemon.getName())) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        item.useItem(pokemon);
        items.remove(item.getName());
    }
    public ArrayList<String> getItemsArray(){
        ArrayList<String> temp = new ArrayList<>();
        for(Item i: items.keySet()){
            temp.add(i.getName());
        }
        return temp;
    }

}
