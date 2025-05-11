package domain;
import java.io.Serializable;
import java.util.*;
import java.awt.Color;
public class AttackingTrainer extends MachineTrainer implements Serializable {


    public AttackingTrainer(String newName, Color newColor) {
        super(newName, newColor);
    }
    //Su enfoque va principalmente al ataque. Utiliza movimientos 
    //que potencian las estadísticas de ataque y/o ataque especial; 
    //o que bajan las estadísticas de defensa y/o defensa especial del jugador rival.
    
    public String decide(Pokemon target){
        ArrayList<MovementTribute> movementsPokemon = inventory.getPokemons().get(actualPokemon.getName()).getMovementsGiveAttack();
        Movement bestAttackMovement = null;
        double possibleAttackMovement = 0;
        for (int i = 0; i < movementsPokemon.size(); i++){
            double attackMovement = movementsPokemon.get(i).getMultiplicator(target.getPrincipalType());
            if (possibleAttackMovement < attackMovement && movementsPokemon.get(i).getPP() > 0){
                possibleAttackMovement = attackMovement;
                bestAttackMovement = movementsPokemon.get(i);
            }
        }
        if (bestAttackMovement != null){
            return bestAttackMovement.getName();
        }
        /*
        else{
            doOtherThen(target);
            return null;
        }*/ //ARREGLAR ESTO doOtherThen si es una buena opcion?
        return actualPokemon.aleatoryMovement(target).getName();
    }
}
