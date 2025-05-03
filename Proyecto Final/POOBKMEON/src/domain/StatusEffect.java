package domain;


public class StatusEffect extends Effect{
    
    public StatusEffect(String newName, String newDescription, int newTimes){
        super(newName,newDescription,newTimes);
    }


    public void affectPokemon(Pokemon affectPokemon){}
    
    public Effect copy(){
        return new StatusEffect(name, description, times);
    }

}