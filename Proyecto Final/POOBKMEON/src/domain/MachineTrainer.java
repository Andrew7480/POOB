package domain;
import java.awt.Color;
import java.util.*;
public abstract class MachineTrainer extends Trainer {
    public MachineTrainer(String name, Color newColor) {
        super(name, newColor);
    }

    public abstract Movement decide(Pokemon target);

    public void doOtherThen(Pokemon target) {
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        if (randomIndex == 0) pokemonMovementDecide(target);
        if (randomIndex == 1) useItem(null);
        else {
            changePokemon();
        }
    }

    @Override
    public void changePokemon(Pokemon pokemon) {
        changePokemon();
    }

    public void changePokemon() {
        ArrayList<Pokemon> stillAlive = inventory.getAlivePokemons(actualPokemon);
        Random random = new Random();
        int choicesToPick = random.nextInt(stillAlive.size());
        actualPokemon = stillAlive.get(choicesToPick);
    }

    @Override
    public void useItem(Item item) {
        useItem();
    }

    public void useItem() {

    }

    @Override
    public void pokemonMovement(Movement mov, Pokemon target) throws PoobkemonException {
        //actualPokemon.useMovement(mov, target);
    }

    public Movement pokemonMovementDecide(Pokemon target) {
        return actualPokemon.aleatoryMovement(target);
    }

    @Override
    public void inicialPokemon(String pokemon) {
        inicialPokemon();
    }
    public void inicialPokemon() {
        String temp = inventory.getPokemons().firstKey();
        actualPokemon = inventory.getPokemons().get(temp);
    }
}
