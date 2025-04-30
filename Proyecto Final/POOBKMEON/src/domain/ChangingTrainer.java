package domain;
import java.util.*;
public class ChangingTrainer extends Trainer {
    public ChangingTrainer(String newName) {
        super(newName);
    }
    /*
     * Depende del pokemon que el rival coloque.
     * Cambia al pokemon que tenga mayor 
     * efectividad contra el pokemon que el rival utilice.
    */
    @Override
    public void changePokemon(Pokemon target){
        TreeMap<String,Pokemon> p = inventory.getPokemons();
        double possible = 0;
        for (Pokemon pok : p.values()){
            double possibleMultiplicator = pok.getMovements().get(0).getMultiplicator(target.getPrincipalType());
            if (possible < possibleMultiplicator){
                possible = possibleMultiplicator;
                actualPokemon = pok;
            }
        }
        //Obtener posicion en la matriz [][]
    }
    /* Se encuentra en el inventario el mejor pokemon posible? */


}
