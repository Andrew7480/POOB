package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;
public class ExpertTrainer extends MachineTrainer implements Serializable{
    public ExpertTrainer(String newName, Color newColor) {
        super(newName, newColor);
    }


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
        return actualPokemon.aleatoryMovement(target).getName();
    }

}
