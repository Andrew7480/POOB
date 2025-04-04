
/**
 * Write a description of class EciSanitasException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EciSanitasException extends Exception
{
    public static final String TREATMENT_NOT_SUITABLE_EXCEPTION = "The treatment is not suitable to be assigned to illness";
    public static final String TREATMENT_NOT_FOUND = "When the treatment was not found";
    
    public EciSanitasException(String mesage){
        super(mesage);
    }
}
