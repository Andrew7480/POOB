package domain;

public class PoobkemonException  extends Exception {

    public static final String INVALID_POKEMON = "THE POKEMON DOESN'T EXIST";

    public static final String ITEM_DONT_EXIST = "THE ITEM DOESN'T EXIST";

    public static final String ITEM_NOT_USABLE = "No se peude usar el item.";

    public static final String ITEM_NOT_FOUND = "No se ha encontrado.";

    public static final String INVALID_MOVEMENT = "no se puede hacer el movimiento.";

    public static final String MOVEMENT_NOT_FOUND = "no se ha encontrado el movimiento.";

    public static final String CANT_CHANGE_POKEMON = "no se puede cambiar el pokemon.";   

    public static final String INVALID_VALUES = "valores invalidos";

    public static final String INVALID_EFFECT = "effecto invalido";

    public static final String MISSED_MOVEMENT = "movimiento no realizado";

    public static final String CANT_DO_MOVEMENT = "no puede hacer el movimiento";

    public static final String EXCESS_CAPACITY_OF_ITEMS = "no capacidad";

    public static final String POKEMON_CANT_INTERACT = "no puede actuar";

    public static final String POKEMON_NOT_FOUND = "no se ha encontrado el pokemon";
    
    public static final String POKEMON_NOT_AFFECTED_BY_STATUS = "tiene un estado";

    public static final String NOT_STATUS_EFFECT = "no hay estado";

    public static final String EFFECT_DURATION_OVER = "Duración terminada del efecto";

    public static final String CANT_ADD_MOVEMENT = "no se puede añadir el movimiento";

    public static final String TRAINER_EXIST = "ya existe este trainer.";

    public static final String COLOR_EXIST = "ya existe este color";

    public static final String POKEMON_ALREADY_EXIST_IN_THE_INVENTORY = "el pokemon ya esta en el inventario y no se permite tener el mismo dos veces";

    public static final String POKEMON_DOESNT_EXIST_IN_THE_INVENTORY_OR_NOT_EXIST = "EL POKEMON NO EXISTE O NO SE HA AGREGADO AL INVENTARIO";

    public static final String THE_POKEMON_IS_ALIVE_OR_REVIVE_CANT_BE_USE = "El pokemon sigue vivo o el item de revivir ya fue usado";

    public static final String INVALID_EFFECT_TRIBUTE_EFFECT = "effecto invalido Tribute EFFECT";

    public static final String INVALID_EFFECT_STATUS_EFFECT = "effecto invalido status effect";
    
    public static final String POKEMON_DIE = "El pokemon esta muerto";

    public static final String POKEMON_DONT_HAVE_THESE_MOVEMENT = "El movimiento que se esta lanzando no lo tiene el pokemon asociado!";

    public static final String MOVEMENT_ALREADY_EXIST = "El movimiento ya existe en la lista de movimientos!";

    public static final String POKEMON_IS_ALIVE_OR_THE_REVIVED_ITEM_HAS_ALREADY_BEEN_USED = "El pokemon sigue vivo o el item de revivir ya ha sido usado";

    public static final String CANT_DO_THE_MOVE = "El movimiento no puede hacerse";

    public PoobkemonException(String message) {
        super(message);
    }
    

}
