package domain;

import java.io.Serializable;

public abstract class Potion extends Item implements Serializable{

    protected final PotionType statics;

    public Potion(String name, String description) {
        super(name, description);
        statics = PotionType.NORMAL;
    }

    public Potion(String name, String description, PotionType newStatic) {
        super(name, description);
        statics = newStatic;
    }
    
    public PotionType getPotionType(){
        return statics;
    }

    public Item copy(){
        return new AttackPotion(name, description, statics);
    }
}

