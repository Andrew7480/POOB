package domain;
public class HyperPotion extends Potion {
    
    /**
     * Constructor for creating a new HyperPotion
     * 
     * @param name The name of the potion
     * @param description A description of what the potion does
     * @param newHyper The type of potion defining its healing and stat boost properties
     */
    public HyperPotion(String name, String description, PotionType newHyper) {
        super(name, description, newHyper);
    }

    /**
     * Uses this HyperPotion on the specified Pokemon.
     * First calls the parent useItem method to perform the basic healing effect,
     * then increases the Pokemon's Special Attack and Special Defense by the potion's value.
     * 
     * @param pokemon The Pokemon to use the potion on
     * @throws PoobkemonException INVALID_POKEMON if the Pokemon is null,
     *                            ITEM_NOT_USABLE if the Pokemon is not alive
     */
    public void useItem(Pokemon pokemon) throws PoobkemonException{
        super.useItem(pokemon);
        pokemon.gainSpecialAttack(statics.getValue()); 
        pokemon.gainSpecialDefense(statics.getValue()); 
    }
}