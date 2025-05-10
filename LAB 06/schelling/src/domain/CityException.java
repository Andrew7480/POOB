package domain;

public class CityException extends Exception{
    public static final String IMPORT_CONSTRUCTION = "Option Import in construction";
    public static final String EXPORT_CONSTRUCTION = "Option Export in construction";
    public static final String SAVE_CONSTRUCTION = "Option Save in construction";
    public static final String OPEN_CONSTRUCTION = "Option Open in construction";
    public static final String SAVE_ERROR = "Error saving option";
    public static final String OPEN_ERROR = "Error opening option";
    public static final String EXPORT_ERROR = "Error opening option";
    public static final String IMPORT_ERROR = "Error opening option";
    public static final String IMPORT_ERROR_COMPILADOR = "Error opening option";
    public static final String EXPORT_ERROR_COMPILADOR = "Error opening option";
    public CityException(String message){
        super(message);
    }

}
