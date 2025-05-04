package domain;

public class PoobkemonException  extends Exception {

    public static final String INVALID_POKEMON = "THE POKEMON DOESN'T EXIST";
    public static final String INVALID_ITEM = "THE ITEM DOESN'T EXIST";
    public static final String ITEM_NOT_USABLE = "No se peude usar la poson.";
    public static final String INVALID_MOVEMENT = "no se puede hacer el movimiento.";
    public static final String INVALID_VALUES = "valores invalidos";
    public static final String INVALID_EFFECT = "effecto invalido";
    public static final String MISSED_MOVEMENT = "movimiento no realizado";
    public static final String CANT_DO_MOVEMENT = "no puede hacer el movimiento";
    public static final String EXCESS_CAPACITY = "no capacidad";
    public static final String POKEMON_CANT_INTERACT = "no puede actuar";
    public static final String POKEMON_NOT_AFFECTED_BY_STATUS = "tiene un estado";
    public static final String NOT_STATUS_EFFECT = "no hay estado";
    public static final String EFFECT_DURATION_OVER = "duracion terminada";
    public static final String CANT_ADD_MOVEMENT = "no se puede añadir el movimiento";
    public static final String TRAINER_EXIST = "ya existe este trainer.";
    public static final String COLOR_EXIST = "ya existe este color";
    public PoobkemonException(String message) {
        super(message);
    }
    

}
