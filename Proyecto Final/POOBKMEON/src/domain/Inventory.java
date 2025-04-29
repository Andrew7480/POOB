package domain;

import java.util.HashMap;

public class Inventory {
    private int capacity;
    private HashMap<String, Item> items = new HashMap<>();
    private HashMap<String, Pokemon> pokemons = new HashMap<>();

    public Inventory(int newCapacity) {
        capacity = newCapacity;
    }
    
}
