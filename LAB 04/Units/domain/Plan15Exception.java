package domain;


/**
 * Write a description of class Plan15Exception here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plan15Exception extends Exception
{
    public final static String CREDITS_UNKNOWN="";
    public final static String CREDITS_ERROR="";
    public final static String IN_PERSON_UNKNOWN="";
    public final static String IN_PERSON_ERROR="";
    public final static String IMPOSSIBLE="";
    public Plan15Exception(String mesage){
        super(mesage);
    }
}
