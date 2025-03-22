public abstract class Machine {
    private Position location;
    /**
     * return if the machine is destroyed in a certain position.
     * @param int longitude
     * @param int latitude
       */
    public boolean isDestroyed(int longitude,int latitude){
        return longitude == location.getLongitude() && latitude == location.getLatitude();
    }
    /**
     * Method abstract that returns if a machine is weak
       */
    public abstract boolean isWeakMachine();
    /**
     * Method abstract that returns the number of sailor
       */
    public abstract int getSailorNumber();
}
