package domain;
import java.io.*;
public class PsPotion extends Potion {
    public PsPotion(String name, String description, PotionType newPs) {
        super(name, description,newPs);
    }

    public void useItem(Pokemon pokemon) throws PoobkemonException{
        super.useItem(pokemon);
        pokemon.gainPS(statics.getValue());
        usedItem();
    }
}
