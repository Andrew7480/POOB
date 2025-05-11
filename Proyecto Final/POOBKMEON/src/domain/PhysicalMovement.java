package domain;

public class PhysicalMovement extends Movement {
    public PhysicalMovement(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newType, int newPriority){
        super(newName, newDescription, newPP, newPower, newPrecision, newType, newPriority);
    }

    public int doAttackTo(Pokemon attacker, Pokemon target) throws PoobkemonException{
        if (!canMakeMove()) throw new PoobkemonException(PoobkemonException.INVALID_MOVEMENT);
        if (Math.random() * 100 > precision) {
            System.out.println("No se ha hecho el fisico por precision.");
            losePP();
            throw new PoobkemonException(PoobkemonException.MISSED_MOVEMENT);
        }
        
        double levelFactor = (2.0 * attacker.getLevel()) / 5.0 + 2.0;
        double attackDefenseRatio = (double) attacker.getAttack() / target.getDefense();
        double damage = ((levelFactor * power * attackDefenseRatio) / 50.0) + 2.0;
        damage *= getMultiplicator(target.getPrincipalType());
        damage *= 0.85 + (Math.random() * 0.15);
        target.losePS(damage);
        losePP();
        System.out.println("Movimiento fisico: " +name +" " + damage);
        return (int)damage;
    }
    @Override
    public Movement copy(){
        return new PhysicalMovement(name, description, PP, power, precision, type, priority);
    }
}
