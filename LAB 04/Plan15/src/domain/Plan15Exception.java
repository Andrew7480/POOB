package domain;


/**
 * Write a description of class Plan15Exception here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plan15Exception extends Exception
{
    public final static String CREDITS_UNKNOWN= "null credits";
    public final static String CREDITS_ERROR="negatives credits";
    public final static String IN_PERSON_UNKNOWN="null person hours";
    public final static String IN_PERSON_ERROR="negative person hours";
    public final static String IMPOSSIBLE= "0 courses";
    public Plan15Exception(String mesage){
        super(mesage);
    }
}
