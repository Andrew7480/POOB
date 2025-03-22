import java.util.ArrayList;

public class Fleet {

    private int name;
    private ArrayList<Machine> machines = new ArrayList<>();
    private ArrayList<Sailor> sailors = new ArrayList<>();
    private Board board;
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
    public int power() throws BattleShipException{
        int count = 0;
        int machineSize = machines.size();
        int sailorsNumber = sailors.size();
        for (Machine m : machines){
            if(m.isWeakMachine()){
                count++;
            }
            sailorsNumber += m.getSailorNumber();
        }
        if (sailorsNumber < machineSize){
            throw new BattleShipException("there are fewer sailors than machines");
        }
        return count;
    }
    public void moveNorth(){
        int[] longitudArr = board.getLongitudRange();
        int[] latitudArr = board.getLatitudRange();
    }
}
