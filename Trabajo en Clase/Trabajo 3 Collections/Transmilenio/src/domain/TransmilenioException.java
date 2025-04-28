package domain;

public class TransmilenioException extends Exception{
    public static final String  INVALID_OCUPATION = "no existe";
    public static final String  NOT_FOUND = "no existe";
    public static final String  ALREADY_EXITS = "ya existe";
    public static final String CANT_FIND_THE_AMOUNT_OF_STOPS = "No se pudo calcular cantidad de paradas de la estacion 1 a la 2";

    public TransmilenioException(String msg){
        super(msg);
    }
}
