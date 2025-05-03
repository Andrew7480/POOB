package domain;
import java.io.Serializable;

public class Movement implements Attackable, Serializable{
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
        PP --;
    }
    public boolean canMakeMove(){
        return (PP>0);
    }
    
    public int doAttackTo(Pokemon attacker, Pokemon target, int attack, int defenseTarget) throws PoobkemonException{
        if (!canMakeMove()) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);
        if (Math.random() * 100 > precision) {
            losePP();
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        
        double levelFactor = (2.0 * attacker.getLevel()) / 5.0 + 2.0;
        double attackDefenseRatio = (double) attack / defenseTarget;
        double damage = ((levelFactor * power * attackDefenseRatio) / 50.0) + 2.0;
        damage *= getMultiplicator(target.getPrincipalType());
        damage *= 0.85 + (Math.random() * 0.15);
        target.losePS(damage);
        losePP();
        return (int)damage;
    }

    public void AttackToStruggle(Pokemon attacker, Pokemon target){
        try{
            int newValueAttacker = (doAttackTo(attacker, target, attacker.getAttack(),target.getDefense())/2);
            attacker.losePS(newValueAttacker);
        }
        catch (PoobkemonException e){
            e.getMessage();
        }
    }
    public Movement coyy(){
        return new Movement(name, description, PP, power, precision, type, priority);
    }
}
