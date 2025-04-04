import java.util.ArrayList;

/**
 * Write a description of class PhysicalTherapy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicalTherapy extends Treatment
{
    private ArrayList<Equipment> equipments;
    private int sessionDuration;
    private String requireEquipment;
    

    @Override
    public boolean isAppropriateForIllness() throws EciSanitasException{
        for(Equipment e:equipments) {
            if (!e.isAvaiable()){
                throw new EciSanitasException(EciSanitasException.TREATMENT_NOT_SUITABLE_EXCEPTION);
            }
        }
        return true;
    }
}
