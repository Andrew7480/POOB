package domain;
import java.io.*;
public class DefensePotion extends Potion implements Serializable {
    protected PotionType defense;
    public DefensePotion(String name, String description, PotionType newDefense) {
        super(name, description);
        defense = newDefense;
    }
    
    public void useItem(Pokemon pokemon){
        pokemon.gainAttack(defense.getValue());
    }

}
