package domain;
import java.io.*;

public abstract class Item implements Serializable{
    protected String name;
    protected Boolean isUsable = true;
    private String description;
    
    public Item(String newName, String newDescription) {
        name = newName;
        description = newDescription;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public void useItem(Pokemon pokemon) throws PoobkemonException{
        if(pokemon == null) throw new PoobkemonException(PoobkemonException.INVALID_POKEMON);
        if(!pokemon.isAlive() || !isUsable) throw new PoobkemonException(PoobkemonException.ITEM_NOT_USABLE);
    }

    protected void usedItem() {
        isUsable = false;
    }
    
}
