

public class UnderwaterCapsule extends Machine implements MotherMachine
{
    private MotherMachine motherMachine;
    private int depth;
    /**
     * The UnderwaterCapsule are immune to attacks
     * @int longitudee
     * @int latitudee
     */
    @Override
    public boolean isDestroyed(int longitude, int latitude){
        return false;
    }
    /**
     * The UnderwaterCapsule are not a weak Machine
     */
    @Override
    public boolean isWeakMachine(){
        return false;
    }
    /**
     * The UnderwaterCapsule have 0 sailors
     */
    @Override
    public int getSailorNumber(){
        return 0;
    }
}
