import java.util.ArrayList;

public class Fleet {

    private int name;
    private ArrayList<Machine> machines = new ArrayList<>();
    private ArrayList<Sailor> sailors = new ArrayList<>();
    private Board board;
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
}
