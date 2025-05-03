package domain;
import java.io.*;
public class HyperPotion extends Potion implements Serializable{
    public HyperPotion(String name, String description, PotionType newHyper) {
        super(name, description, newHyper);
    }

    public void useItem(Pokemon pokemon) throws PoobkemonException{
        super.useItem(pokemon);
        pokemon.gainSpecialAttack(statics.getValue());
        pokemon.gainSpecialDefense(statics.getValue());
        usedItem();
    }
}