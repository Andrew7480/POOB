public class Plane extends Machine {

    private int plate;
    private boolean inAir;
    private Sailor pilot;
    private Sailor copilot;
    
    public boolean isDestroyed(int longitude, int latitude){
        boolean verify = super.isDestroyed(longitude,latitude);
        if (!verify || inAir){
            return false;
        }
        return true;
    }
    @Override
    public boolean isWeakMachine(){
        return pilot == null;
    }
    
    

}
