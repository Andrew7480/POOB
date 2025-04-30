package domain;
import java.io.*;
public class AttackPotion extends Potion implements Serializable{

    public AttackPotion(String name, String description, PotionType newAttack) {
        super(name, description, newAttack);
    }

    public void useItem(Pokemon pokemon) throws PoobkemonException{
        super.useItem(pokemon);
        pokemon.gainAttack(statics.getValue());
        usedItem();
    }
}
