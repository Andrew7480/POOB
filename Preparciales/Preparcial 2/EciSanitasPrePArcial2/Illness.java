import java.util.ArrayList;

public class Illness {
    private String name;
    private String description;
    private ArrayList<Treatment> treatments;
    
    public String getName(){
        return name;
    }
    public boolean assignTreatment(Treatment  treatment){
        treatments.add(treatment);
        return true;
    }
}
