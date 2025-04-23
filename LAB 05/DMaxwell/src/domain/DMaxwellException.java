package domain;

/**
 * DMaxwellException
 * 
 * @author  Tulio Riaño and Andrés Cardozo
 * @version 95%
 */

public class DMaxwellException extends Exception {
    public static final String VALUES_ERROR = "The values cant be negative.";
    public static final String NULL_VALUES = "The values cant be null.";
    public static final String INVALID_MOVEMENT = "Cant do the movement.";
    public static final String INVALID_MOVE = "Not the right move.";
    public static final String INVALID_DIMENSIONS = "THE BOARD CAN'T BE SO BIG!";
    /*
     * Constructor of DMaxwellException
     * @param String message
     */
    public DMaxwellException(String mesage){
        super(mesage);
    }

}
