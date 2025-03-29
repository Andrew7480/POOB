package domain;


/**
 * Write a description of class Plan15Exception here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plan15Exception extends Exception
{
    public final static String CREDITS_UNKNOWN= "creditos nulos";
    public final static String CREDITS_ERROR="creditos negativos";
    public final static String IN_PERSON_UNKNOWN="horas presenciales nulas";
    public final static String IN_PERSON_ERROR="horas presenciales negativas";
    public final static String IMPOSSIBLE= "0 cursos en el nucleo";
    
    /**
     * Its the constructor of the Plan15Exception and use super
     */
    public Plan15Exception(String mesage){
        super(mesage);
    }
}
