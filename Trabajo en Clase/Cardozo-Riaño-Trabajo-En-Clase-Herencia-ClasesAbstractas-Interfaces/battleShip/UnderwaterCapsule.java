
/**
 * Write a description of class UnderwaterCapsule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UnderwaterCapsule extends Machine implements MotherMachine
{
    private MotherMachine motherMachine;
    private int depth;
    @Override
    public boolean isDestroyed(int longitude, int latitude){
        return false;
    }
    @Override
    public boolean isWeakMachine(){
        return false;
    }
    @Override
    public int getSailorNumber(){
        return 0;
    }
}
