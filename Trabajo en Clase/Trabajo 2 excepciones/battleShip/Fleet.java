import java.util.ArrayList;

public class Fleet {

    private int name;
    private ArrayList<Machine> machines = new ArrayList<>();
    private ArrayList<Sailor> sailors = new ArrayList<>();
    private Board board;
    private boolean doTheLastMovement = true;
    
    /**
     * Consults the machines that may be affected by an explosion at the given
     * position. Many machines can be at one coordinate. Airplanes in the air
     * are not destroyed.
     * @param int longitude
     * @param int latitude
     * return machines in position
       */
    public ArrayList<Machine> willBeDestroyed(int longitude, int latitude){
        ArrayList<Machine> machinesInPosition = new ArrayList<>();
        for (Machine m : machines){
            boolean verifyMachines = m.isDestroyed(longitude, latitude);
            if (verifyMachines){
                machinesInPosition.add(m);
            }
        }
        return machinesInPosition;
    }
    /**
     * Consults the weak machines of a fleet. A ship is weak if it has fewer
     * than five sailors; an airplane, if it has no main pilot; and an aircraft
     * carrier, if it is a weak ship or any of its airplanes in the air is weak.
     * 
     * return the weak machines.
       */
    public ArrayList<Machine> weakMachines(){
        ArrayList<Machine> listOfMachinesWeak = new ArrayList<>();
        for (Machine m : machines){
            boolean verifyWeakness = m.isWeakMachine();
            if (verifyWeakness){
                listOfMachinesWeak.add(m);
            }
        }
        return listOfMachinesWeak;
    }
    /**
     * Consults the power of the fleet. The power is the number of non-weak machines.
     * Returns The power of the fleet.
     * Throws BattleShipException - if there are fewer sailors than machines in the
     */
    public int power() throws BattleShipException{
        int count = 0;
        int machineSize = machines.size();
        int sailorsNumber = sailors.size();
        for (Machine m : machines){
            if(!m.isWeakMachine()){
                count++;
            }
            sailorsNumber += m.getSailorNumber();
        }
        if (sailorsNumber < machineSize){
            throw new BattleShipException("there are fewer sailors than machines");
        }
        return count;
    }
    /**
     * verify if is in the board
     * @int lon  the longitudee
     * @int lat  the latitudee
     * return a boolean
     */
    private boolean verifyLimits(int lon, int lat){
        int lonMin = board.getLongitudRange()[0];
        int lonMax= board.getLongitudRange()[1];
        int latMin = board.getLatitudRange()[0];
        int latMax= board.getLatitudRange()[1];
        return (lon>=lonMin  && lon <= lonMax && lat >= latMin  && lat <= latMax);
    }
    
    /**
     * Moves the fleet one position north.
     * Throws BattleShipException
     * 
     */
    public void moveNorth() throws BattleShipException {  //si una maquina no se puede mover, no se mueve toda la flota        
        for(Machine m:machines){
            doTheLastMovement = (verifyLimits(m.getPosition().getLongitude(),m.getPosition().getLatitude()+1));
            if (doTheLastMovement == false){
                throw new BattleShipException("Can't do the movement to North");
            }
        }
                
        if (doTheLastMovement){
            for(Machine m:machines){
                m.moveNorth();
            }
        }
    }
    /**
     * Get if the last movement was done
     */
    public boolean getDoTheLastMovement(){
        return doTheLastMovement;
    }
}
