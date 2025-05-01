package domain;

public class MovementState extends Movement{
    private StatusEffect state;
    private String stateTo;

    public MovementState(String newName, String newDescription, int newPP, int newPower, int newPrecision, PokemonType newPT, StatusEffect estado){
        super(newName,newDescription,newPP,newPower,newPrecision,newPT);
        state = estado;
        stateTo = state.getStateTo();
    }
    public StatusEffect getStatus(){
        return state;
    }
    public String getStateTo(){
        return stateTo;
    }
    @Override
    public int doAttackTo(Pokemon attacker, Pokemon target, int attack) throws PoobkemonException{
        target.addEffect(state);
        return 0;
    }
}