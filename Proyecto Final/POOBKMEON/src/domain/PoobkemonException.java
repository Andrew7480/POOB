package domain;

public class PoobkemonException  extends Exception {

    public static final String INVALID_POKEMON = "THE POKEMON DOESN'T EXIST";
    public static final String INVALID_ITEM = "THE ITEM DOESN'T EXIST";
    public static final String ITEM_NOT_USABLE = "No se peude usar la poson.";
    public static final String INVALID_MOVEMENT = "on.";
    public static final String INVALID_VALUES = "on.";
    
    public PoobkemonException(String message) {
        super(message);
    }
    

}
