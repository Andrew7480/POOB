package domain;

public class Pokemon implements Ataque{
    private String name;
    private int level;
    private int ps;
    private int attack;
    private int specialAttack;
    private int defense;
    private int velocity;
    private PokemonType principalType;
    private PokemonType secondaryType;

    private boolean isAlive = true;

    public Pokemon(String newName, int newLevel, int newPs, int newAttack, int newSpecialAttack, int newDefense, int newVelocity, PokemonType newPrincipalType, PokemonType newSecondaryType) {
        name = newName;
        level = newLevel;
        ps = newPs;
        attack = newAttack;
        specialAttack = newSpecialAttack;
        defense = newDefense;
        velocity = newVelocity;
        principalType = newPrincipalType;
        secondaryType = newSecondaryType;
    }
    
    public String getName() {
        return name;
    }

    public void losePS(){}
    public void gainPS(){}
    public void gainLevel(){}
    public void loseLevel(){}

    public void gainAttack(int newAttack){
        attack += newAttack;
    }

    public void loseAttack(int newAttack){
        attack -= newAttack;
    }

    public void gainSpecialAttack(){}
    public void loseSpecialAttack(){}
    public void gainDefense(){}
    public void loseDefense(){}
    public void gainVelocity(){}
    public void loseVelocity(){}

    public void doAttack( Pokemon oponente){

    }
    public void ataqueNormal(){

    }
    public void ataqueHabilidad(){

    }
    public void ataqueDefinitivo(){}

}

