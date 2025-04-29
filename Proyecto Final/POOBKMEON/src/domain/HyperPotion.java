package domain;
import java.io.*;
public class HyperPotion extends Potion implements Serializable{
    private PotionType hyper;
    
    public HyperPotion(String name, String description, PotionType newHyper) {
        super(name, description);
        hyper = newHyper;
    }

    public void useItem(Pokemon pokemon){
        pokemon.gainAttack(hyper.getValue());
    }
}
