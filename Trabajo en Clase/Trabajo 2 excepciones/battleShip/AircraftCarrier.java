import java.util.ArrayList;

public class AircraftCarrier extends Ship {
    private int number;
    private int capacity;
    private ArrayList<Plane> airPlanes;
    /**
     * return if a aircraft carrier is a weak machine
       */
    @Override
    public boolean isWeakMachine(){
        boolean verify = super.isWeakMachine();
        for (Plane p : airPlanes){
            if (p.isWeakMachine() && p.isInAir() ){
                verify = true;
            }
        }
        return verify;
    }
}
