package domain;
import java.io.*;
public class DefensePotion extends Potion implements Serializable {

    public DefensePotion(String name, String description, PotionType newDefense) {
        super(name, description, newDefense);
    }
    
    public void useItem(Pokemon pokemon) throws PoobkemonException{
        super.useItem(pokemon);
        pokemon.gainAttack(statics.getValue());
        usedItem();
    }

}
