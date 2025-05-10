package domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Battle implements Serializable {
    ArrayList<Trainer> turnTrainers;
    private int turnIndex;
    private Random random;
    private boolean isOver;
    private Trainer winner;

    public Battle(Trainer trainer1, Trainer trainer2){
        turnTrainers = new ArrayList<>();
        turnTrainers.add(trainer1);
        turnTrainers.add(trainer2);
        turnIndex = 0;
        winner = null;
        isOver = false;
    }

    /* 
    public void doMovements(){
        if (turnTrainers.size() == 2){
            Trainer trainer1 = turnTrainers.get(0);
            Trainer trainer2 = turnTrainers.get(1);

            Movement mov1 = trainer1.decide(trainer2.getPokemonInUse());
            Movement mov2 = trainer2.decide(trainer1.getPokemonInUse());

            if (mov1.getPriority() > mov2.getPriority()){
                performMovements(trainer1, trainer2, mov1, mov2);
            }
            else if (mov1.getPriority() == mov2.getPriority()){
                if (trainer1.getPokemonInUse().getVelocity() > trainer2.getPokemonInUse().getVelocity()){
                    performMovements(trainer1, trainer2, mov1, mov2);
                }
                else{
                    performMovements(trainer2, trainer1, mov2, mov1);
                }
            }
        }

        checkBattleState();
    }

    private void performMovements(Trainer first, Trainer second, Movement firstMove, Movement secondMove){
        try{
            first.getPokemonInUse().useMovement(firstMove, second.getPokemonInUse());

            if(second.getPokemonInUse().isAlive()){
                second.getPokemonInUse().useMovement(secondMove,first.getPokemonInUse());
            }
        } catch(PoobkemonException e){
            System.out.println(e.getMessage());
        }
    }*/

    public void executeMovement(String move) throws PoobkemonException{ 
        Trainer current = getCurrentTrainer();
        Trainer opponent = getOpponentTrainer();
        current.pokemonMovement(move, opponent.getPokemonInUse());
        afterAction();
    }

    public void changePokemon(String pokemon) throws PoobkemonException{
        getCurrentTrainer().changePokemon(pokemon);
        afterAction();
    }

    public void useItem(String item) throws PoobkemonException{
        getCurrentTrainer().useItem(item);
        afterAction();
    }

    public void afterAction(){
        advanceTurn();
        checkBattleState();
    }

    private void advanceTurn(){
        turnIndex = (turnIndex + 1) % turnTrainers.size();
    }

    private void setTurn(Trainer trainer){
        int index = turnTrainers.indexOf(trainer);
        if (index != -1){
            turnIndex = index;
        }
    }

    public void resetTurn(){
        turnIndex = 0;
    }
    public void huir(){
        //
    }

    private void checkBattleState(){  //no extensible??
        if (turnTrainers.size() == 2){
            Trainer trainer1 = turnTrainers.get(0);
            Trainer trainer2 = turnTrainers.get(1);
            if(!trainer1.canStillFighting()){
                isOver = true;
                winner = trainer2;
            }else if(trainer2.canStillFighting()){
                isOver = true;
                winner = trainer1;
            }
        }
    }

    public boolean isOver(){
        return isOver;
    }

    public Trainer getWinner(){
        return winner;
    }

    public Trainer getCurrentTrainer(){
        return turnTrainers.get(turnIndex);
    }

    private int getTurnIndex(){
        return turnIndex;
    }

    public ArrayList<Trainer> getTurnTrainers(){
        return turnTrainers;
    }

    public Trainer getOpponentTrainer(){
        return turnTrainers.get((turnIndex + 1) % turnTrainers.size());
    }

    public boolean coinToss(){ //extensiblr??
        boolean result = random.nextBoolean();
        String coinside = result ? "Cara" : "Cruz";

        if (turnTrainers.size() == 2){
            turnIndex = result ? 0 : 1;
            System.out.println("Lanzamiento de moneda: " + coinside +
                    " - Comienza " + turnTrainers.get(turnIndex).getName());
        }

        return result; // Organizar arreglo dependiendo el resultado
    }
}
