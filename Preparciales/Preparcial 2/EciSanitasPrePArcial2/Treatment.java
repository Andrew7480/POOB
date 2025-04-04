public class Treatment {
    private String name;
    private String description;
    private String duration;
    

    
    public boolean isAppropriateForIllness() throws EciSanitasException{
        throw new EciSanitasException(EciSanitasException.TREATMENT_NOT_SUITABLE_EXCEPTION);
    }
    
    public String getName(){
        return name;
    }
}
