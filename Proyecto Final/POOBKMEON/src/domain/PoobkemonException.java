package domain;

public class PoobkemonException  extends Exception {

    public static final String INVALID_POKEMON = "THE POKEMON DOESN'T EXIST";
    public static final String INVALID_ITEM = "THE ITEM DOESN'T EXIST";
    public static final String ITEM_NOT_USABLE = "No se peude usar la poson.";
    public static final String INVALID_MOVEMENT = "on.";
    public static final String INVALID_VALUES = "on.";
    public static final String INVALID_EFFECT = "on.";
    public static final String MISSED_MOVEMENT = "";
    public static final String CANT_DO_MOVEMENT = "";
    public static final String EXCESS_CAPACITY = "";
    public static final String POKEMON_CANT_INTERACT = "";
    public static final String POKEMON_NOT_AFFECTED_BY_STATUS = "";
    public static final String NOT_STATUS_EFFECT = "";
    public static final String EFFECT_DURATION_OVER = "";
    public static final String CANT_ADD_MOVEMENT = "";
    public PoobkemonException(String message) {
        super(message);
    }
    

}
