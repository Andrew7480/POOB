package domain;
import java.io.*;
public class SuperPotion extends Potion implements Serializable{
    public SuperPotion(String name, String description, PotionType newSuper) {
        super(name, description, newSuper);
    }
    public void useItem(Pokemon pokemon) throws PoobkemonException {
        super.useItem(pokemon);
        pokemon.gainAttack(statics.getValue());
        pokemon.gainPS(statics.getValue());
        pokemon.gainDefense(statics.getValue());
    }
}