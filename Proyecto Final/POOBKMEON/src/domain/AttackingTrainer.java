package domain;
import java.util.*;
public class AttackingTrainer extends MachineTrainer {
    public AttackingTrainer(String newName) {
        super(newName);
    }
    //Su enfoque va principalmente al ataque. Utiliza movimientos 
    //que potencian las estadísticas de ataque y/o ataque especial; 
    //o que bajan las estadísticas de defensa y/o defensa especial del jugador rival.
    
    public void decide(Pokemon target){
        ArrayList<MovementState> movementsPokemon = inventory.getPokemons().get(actualPokemon.getName()).getStateMovementsGiveAttack();
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
            try {
                pokemonMovement(bestAttackMovement,target);
            } catch (PoobkemonException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            doOtherThen(target);
        }
    }
}
