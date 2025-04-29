package domain;

public class Revive extends Item {
    public Revive(String name) {
        super("Revive","Recupera 50% de la salud de un Pokémon caído en combate.");
    }

    public void useItem(Pokemon pokemon){ //luego logica
        pokemon.gainAttack(0);
    }
    
}
