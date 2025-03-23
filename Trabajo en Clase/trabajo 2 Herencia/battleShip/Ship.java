import java.util.Collection;

public class Ship extends Machine {

    private Collection<Sailor> sailors;
    /**
     * verify if it has fewer than five sailors, return a boolean depends.
       */
    @Override
    public boolean isWeakMachine(){
        return sailors.size() >= 5;
    }
}
