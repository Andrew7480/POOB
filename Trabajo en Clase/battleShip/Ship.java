import java.util.Collection;

public class Ship extends Machine {

    private Collection<Sailor> sailors;
    @Override
    public boolean isWeakMachine(){
        return sailors.size() >= 5;
    }
}
