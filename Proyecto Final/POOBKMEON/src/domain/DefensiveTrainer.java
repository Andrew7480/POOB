package domain;
import java.util.*;
public class DefensiveTrainer extends MachineTrainer {
    
    public DefensiveTrainer(String newName) {
        super(newName);
    }
    /*
     * Su enfoque va principalmente a la defensa. 
     * Utiliza movimientos que potencian las estadísticas de
     * defensa y/o defensa especial, que brindan protección contra ataques
     * rivales o que bajan las estadísticas de ataque y/o ataque especial del jugador rival.
     */
    // actualPokemon -> referencia
    @Override
    public Movement decide(Pokemon target){
        //Cambiar Movement -> MovementState
        ArrayList<MovementTribute> movementsPokemon = inventory.getPokemons().get(actualPokemon.getName()).getMovementsGiveDefense();
        Movement bestMovementDefensive = null;
        int status = 0;
        for (int i = 0; i < movementsPokemon.size(); i++){
            if (movementsPokemon.get(i).getStateTo().get("Defense") > status && movementsPokemon.get(i).getPP() > 0){
                status = movementsPokemon.get(i).getStateTo().get("Defense");
                bestMovementDefensive = movementsPokemon.get(i);
            }
        }

        if (bestMovementDefensive != null){
            return bestMovementDefensive;
        }
        else{
            //hace otro movimiento que seria hacer un ataque o cambiar o gg 
            doOtherThen(target);
            return null;
        }
    }
}
