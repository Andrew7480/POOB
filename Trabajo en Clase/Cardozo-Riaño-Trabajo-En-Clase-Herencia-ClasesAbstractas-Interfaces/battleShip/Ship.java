import java.util.Collection;
public class Ship extends Machine implements MotherMachine {

    private Collection<Sailor> sailors;
    /**
     * verify if it has fewer than five sailors, return a boolean depends.
       */
    @Override
    public boolean isWeakMachine(){
        return sailors.size() >= 5;
    }
    @Override
    public int getSailorNumber(){
        return sailors.size();
    }
}
