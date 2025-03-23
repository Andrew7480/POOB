import java.util.ArrayList;

public class Board {

    private ArrayList<Fleet> fleets;
    private final static int[] longitudRange = {0,180};
    private final static int[] latitudRange = {-90,90};
    /**
     * returns the range fot longitudee 
     */
    public int[] getLongitudRange(){
        return longitudRange;
    }
    /**
     * returns the range fot latituee 
     */
    public int[] getLatitudRange(){
        return latitudRange;
    }
    /**
     * Consults the number of fleets that completed a full movement
     * return the number of fleets that completed a full movement
     */
    public int toNorth(){
        int counter = 0;
        for (Fleet f : fleets){
            if (f.getDoTheLastMovement()) counter++;
        }
        return counter;
    }
}
