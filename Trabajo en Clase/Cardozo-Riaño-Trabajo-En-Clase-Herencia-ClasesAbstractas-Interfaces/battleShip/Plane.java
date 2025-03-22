public class Plane extends Machine {
    private int plate;
    private boolean inAir;
    private Sailor pilot;
    private Sailor copilot;
    /**
     * verify if a plane is destroyed in a certain longitude and latitude
     * @param int longitude
     * @param int latitude
    */
    @Override
    public boolean isDestroyed(int longitude, int latitude){
        boolean verify = super.isDestroyed(longitude,latitude);
        if (!verify || inAir){
            return false;
        }
        return true;
    }
    /**
     * return if the plane is a weak machine.
       */
    @Override
    public boolean isWeakMachine(){
        return pilot == null;
    }
    
    @Override
    public int getSailorNumber(){
        int count = 0;
        if (pilot != null) count++;
        if (copilot != null) count++;
        return count;
    }

}
