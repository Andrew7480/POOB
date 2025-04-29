package domain;
import java.io.*;
public class PsPotion extends Potion implements Serializable {
    protected PotionType PS;
    public PsPotion(String name, String description, PotionType newPs) {
        super(name, description);
        PS = newPs;
    }

    public void useItem(Pokemon pokemon){
        pokemon.gainAttack(PS.getValue());
    }
}
