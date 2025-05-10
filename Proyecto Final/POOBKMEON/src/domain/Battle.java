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


    public void executeMovement(String move) throws PoobkemonException{ 
        Trainer current = getCurrentTrainer();
        Trainer opponent = getOpponentTrainer();
        current.pokemonMovement(move, opponent.getPokemonInUse());
        afterAction();
    }

    public void changePokemon(String pokemon) throws PoobkemonException{
        System.out.println("LLEGA BIEN A ENTRENADOR? " + pokemon);
        getCurrentTrainer().changePokemon(pokemon);
        System.out.println(getCurrentTrainer().getName());
        afterAction();
    }

    public void useItem(String item) throws PoobkemonException{
        getCurrentTrainer().useItem(item);
        afterAction();
    }

    public void afterAction(){
        //advanceTurn();
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
    public String getCurrentPokemonName(){
        return getCurrentTrainer().getPokemonInUse().getName();
    }
    public int getCurrentPokemonLevel(){
        return getCurrentTrainer().getPokemonInUse().getLevel();
    }
    public int getCurrentPokemonPs(){
        return getCurrentTrainer().getPokemonInUse().getPs();
    }
    public int getCurrentPokemonPokedexIndex(){
        System.out.println(getCurrentTrainer().getPokemonInUse().getName() + "SE IMPRIME DOS VECES?");
        System.out.println(getCurrentTrainer().getPokemonInUse().getPokedexIndex());
        return getCurrentTrainer().getPokemonInUse().getPokedexIndex();
    }
    public int getCurrentMaxPs(){
        return getCurrentTrainer().getPokemonInUse().getMaxPs();
    }

    public String getOponentPokemonName(){
        return getOpponentTrainer().getPokemonInUse().getName();
    }
    public int getOponentPokemonLevel(){
        return getOpponentTrainer().getPokemonInUse().getLevel();
    }
    public int getOponentPokemonPs(){
        return getOpponentTrainer().getPokemonInUse().getPs();
    }

    public int getOponentPokemonPokedexIndex(){
        return getOpponentTrainer().getPokemonInUse().getPokedexIndex();
    }
    public int getOponentMaxPs(){
        return getOpponentTrainer().getPokemonInUse().getMaxPs();
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
