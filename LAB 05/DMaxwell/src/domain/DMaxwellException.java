package domain;

public class DMaxwellException extends Exception {
    public static final String VALUES_ERROR = "The values cant be negative.";
    public static final String TULIO_THE_PROTAGINIST = "NOONE CAN BE THE PROTAGONIST IF IS NOT TULIO.";
    public static final String NULL_VALUES = "The values cant be null.";
    public static final String INVALID_MOVEMENT = "Cant do the movement.";
    
    public DMaxwellException(String mesage){
        super(mesage);
    }

}
