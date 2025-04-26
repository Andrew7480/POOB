package domain;

public class TransmilenioException extends Exception{
    public static final String  INVALID_OCUPATION = "no existe";
    public static final String  NOT_FOUND = "no existe";
    public static final String  ALREADY_EXITS = "ya existe";

    public TransmilenioException(String msg){
        super(msg);
    }
}
