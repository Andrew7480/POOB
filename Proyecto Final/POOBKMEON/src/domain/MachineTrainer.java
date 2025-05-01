package domain;
import java.util.*;
public abstract class MachineTrainer extends Trainer{
    public MachineTrainer(String name){
        super(name);
    }

    public abstract void decide(Pokemon target);

    public void doOtherThen(Pokemon target){
        Random random = new Random();
        int randomIndex = random.nextInt(4); 
        if (randomIndex == 0) pokemonMovement(target);
        if (randomIndex == 1) useItem(null);
        else {changePokemon();}
    }

    public void changePokemon(){
        ArrayList<Pokemon> stillAlive = inventory.getAlivePokemons(actualPokemon);
        Random random = new Random();
        int choicesToPick = random.nextInt(stillAlive.size());
        actualPokemon = stillAlive.get(choicesToPick);
    }

    @Override
    public void changePokemon(Pokemon pokemon){
        changePokemon();
    }
    @Override
    public void useItem(Item item){
        useItem();
    }
    public void useItem(){
        
    }

    public void pokemonMovement(Pokemon target){
        actualPokemon.useAleatoryMovement(target);
    }
}
