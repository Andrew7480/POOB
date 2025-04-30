package domain;

public class Movement implements Attackable{
    protected String name;
    protected String description;
    protected int PP = 25;
    protected int power;
    protected int precision = 100;

    protected PokemonType type;
    
    public Movement(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newType){
        name = newName;
        description = newDescription;
        PP = newPP;
        power = newPower;
        precision = newPrecision;
        type = newType;
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

    public int doAttack(Pokemon attacker, Pokemon target, int attack) throws PoobkemonException{
        double levelFactor = (2.0 * attacker.getLevel()) / 5.0 + 2.0;
        double attackDefenseRatio = (double) attack / target.getDefense();
        double damage = ((levelFactor * power * attackDefenseRatio) / 50.0) + 2.0;
        damage *= getMultiplicator(target.getPrincipalType());
        damage *= 0.85 + (Math.random() * 0.15);
        return (int) damage;
    }
    
}
