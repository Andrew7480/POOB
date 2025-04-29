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

    public abstract void useItem(Pokemon pokemon);
}
