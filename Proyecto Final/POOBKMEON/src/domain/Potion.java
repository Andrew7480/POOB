package domain;


public abstract class Potion extends Item{

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

}

