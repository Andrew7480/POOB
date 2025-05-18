package domain;
public abstract class Potion extends Item{

    protected final PotionType statics;

    /**
     * Constructor for creating a new Potion with a specific type.
     * Initializes the potion with the given name, description, and potion type.
     * 
     * @param name The name of the potion
     * @param description A description of what the potion does
     * @param newStatic The type of the potion, which determines its effectiveness
     */
    public Potion(String name, String description, PotionType newStatic) {
        super(name, description);
        statics = newStatic;
    }
    
    /**
     * Gets the type of this potion.
     * The potion type determines the magnitude of its effect when used.
     * 
     * @return The PotionType enum value representing this potion's type
     */
    public PotionType getPotionType(){
        return statics;
    }
}