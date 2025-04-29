package domain;

public abstract class Trainer {
    protected String name;
    protected Inventory inventory;

    Trainer(String newName) {
        name = newName;
    }

    public String getName(){
        return name;
    }
    public void usePotion(Pokemon pokemon, Potion potion) {
        potion.useItem(pokemon);
    }

}
