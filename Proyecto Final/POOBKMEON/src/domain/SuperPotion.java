package domain;
import java.io.*;
public class SuperPotion extends Potion implements Serializable {
    private PotionType superPo;

    public SuperPotion(String name, String description, PotionType newSuper) {
        super(name, description);
        superPo = newSuper;
    }
    public void useItem(Pokemon pokemon){
        pokemon.gainAttack(superPo.getValue());
    }
}
