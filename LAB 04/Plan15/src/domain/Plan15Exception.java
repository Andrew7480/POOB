package domain;


/**
 * Write a description of class Plan15Exception here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plan15Exception extends Exception
{
    public final static String CREDITS_UNKNOWN= "Creditos nulos";
    public final static String CREDITS_ERROR="Creditos negativos";
    public final static String IN_PERSON_UNKNOWN="Null person hours";
    public final static String IN_PERSON_ERROR="Negative person hours";
    public final static String IMPOSSIBLE= "0 unidades";
    public final static String NULL_VALUES= "Valores nulos";
    public final static String INT_ERROR= "Un valor que no es entero";
    public final static String PERCENTAGE_ERROR= "Un porcentaje no valido";
    public final static String PERCENTAGE_NOT_FOUND= "Un porcentaje nulo";
    public final static String COURSE_NOT_FOUND= "No existe el curso";
    public final static String NOMENCLATURE_COURSE_ALREADY_EXITS= "Ya existe un curso con esa nomenclatura";
    public final static String NOMENCLATURE_CORE_ALREADY_EXITS= "Ya existe una unidad con esa nomenclatura";

    public Plan15Exception(String mesage){
        super(mesage);
    }
}
