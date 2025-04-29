package domain;
import java.io.*;
public class AttackPotion extends Potion implements Serializable{
    protected PotionType attack;
    public AttackPotion(String name, String description, PotionType newAttack) {
        super(name, description);
        attack = newAttack;
    }

    public void useItem(Pokemon pokemon){
        pokemon.gainAttack(attack.getValue());
    }
}
