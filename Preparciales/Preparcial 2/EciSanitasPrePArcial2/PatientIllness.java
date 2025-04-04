import java.util.ArrayList;

public class PatientIllness {
    private int riskLevel;
    private Illness illness;
    private ArrayList<Treatment> treatments= new ArrayList<Treatment>();
    private Appointment appointment;
    
    /**
     * Schedule a treatment to a illness if is possible
     * @ treatmentName string
     * @ maxRisk int
     */
    public boolean scheduleTreatmentToIllness(String treatmentName , int maxRisk) throws EciSanitasException{
        boolean hasProperlyRiskLevel = verifyMinRisk(maxRisk);
        if (hasProperlyRiskLevel){
            Treatment treatment = loanTreatment(treatmentName);
            boolean isApropiate = treatment.isAppropriateForIllness();
            if (isApropiate){
                illness.assignTreatment(treatment);
            }
        }
    
        return true;
    }
    public boolean verifyMinRisk(int risk){
        return risk <= riskLevel;
    }
    public Treatment loanTreatment(String treatmentName)throws EciSanitasException{
        for (Treatment t:treatments){
            if (t.getName()== treatmentName){
                return t;
            }
        }
        throw new EciSanitasException(EciSanitasException.TREATMENT_NOT_FOUND);
        //return new Treatment(treatmentName);
    }
}
