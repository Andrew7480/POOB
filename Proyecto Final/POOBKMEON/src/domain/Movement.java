package domain;
import java.io.Serializable;

public abstract class Movement implements Attackable, Serializable{
    protected String name;
    protected String description;
    protected int PP = 25;
    protected int power;
    protected int precision = 100;

    protected PokemonType type;
    protected int priority; 
    

    public Movement(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newType, int newpPriority){
        name = newName;
        description = newDescription;
        PP = newPP;
        power = newPower;
        precision = newPrecision;
        type = newType;
        priority = newpPriority;

        //los pp se ponen de acuerdo al power, si es debil se pueden usar mas segudo
        PP = Math.min(newPP, (newPower > 50 ? 20 : 40));
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getPrecision(){
        return precision;
    }

    public PokemonType getType(){
        return type;
    }
    public int getPriority(){
        return priority;
    }

    public double getMultiplicator(PokemonType defender){
            return multiplicadores[type.getIndex()][defender.getIndex()];
    }

    public int getPP(){
        return PP;
    }
    public int getPower(){
        return power;
    }

    public void losePP()throws PoobkemonException{
        if (PP - 1 <= 0) throw new PoobkemonException(PoobkemonException.INVALID_VALUES);
        PP --;}

    public boolean canMakeMove(){return (PP>0);}
    
    public abstract int doAttackTo(Pokemon attacker, Pokemon target) throws PoobkemonException;

    public void AttackToStruggle(Pokemon attacker, Pokemon target){
        try{
            int newValueAttacker = (doAttackTo(attacker, target)); //mirar desespspps
            attacker.losePS(newValueAttacker);
        }
        catch (PoobkemonException e){
            e.getMessage();
        }
    }
    public abstract Movement copy();


}
