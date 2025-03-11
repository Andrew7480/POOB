public class Machine {
    private Position location;
    public boolean isDestroyed(int longitude,int latitude){
        boolean verify = false;
        if (longitude == location.getLongitude() && latitude == location.getLatitude()){
            verify = true;
        }
        return verify;
    }
    public boolean isWeakMachine(){
        return false;
    }
}
