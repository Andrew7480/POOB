package domain;
import java.awt.Color;
import java.io.Serializable;
import java.util.*;
public class ChangingTrainer extends MachineTrainer implements Serializable{
    public ChangingTrainer(String newName, Color color) {
        super(newName, color);
    }
    /*
     * Depende del pokemon que el rival coloque.
     * Cambia al pokemon que tenga mayor 
     * efectividad contra el pokemon que el rival utilice.
    */
    @Override
    public String decide(Pokemon target){
        TreeMap<String,Pokemon> p = inventory.getPokemons();
        Pokemon pokemonActual = actualPokemon;
        double possible = 0;
        for (Pokemon pok : p.values()){
            double possibleMultiplicator = pok.getMovements().get(0).getMultiplicator(target.getPrincipalType());
            if (possible < possibleMultiplicator){
                possible = possibleMultiplicator;
                actualPokemon = pok;
            }
        }
        if (pokemonActual.equals(actualPokemon)){
            doOtherThen(target);
        }
        //return null; doOtherThen si es una buena opcion?
        return actualPokemon.aleatoryMovement(target).getName();
    }


}
