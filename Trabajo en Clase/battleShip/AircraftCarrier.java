import java.util.ArrayList;

public class AircraftCarrier extends Ship {

    private int number;
    private int capacity;
    private ArrayList<Plane> airPlanes;
    public boolean isWeakMachine(){
        boolean verify = super.isWeakMachine();
        for (Plane p : airPlanes){
            if (p.isWeakMachine()){
                verify = true;
            }
        }
        return verify;
    }
}
