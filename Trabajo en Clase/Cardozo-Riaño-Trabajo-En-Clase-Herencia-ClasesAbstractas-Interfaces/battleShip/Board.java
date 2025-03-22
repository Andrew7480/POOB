import java.util.ArrayList;

public class Board {

    private ArrayList<Fleet> fleets;
    private final int[] longitudRange = {0,180};
    private final int[] latitudRange = {-90,90};
    
    public int[] getLongitudRange(){
        return longitudRange;
    }
    public int[] getLatitudRange(){
        return latitudRange;
    }
}
