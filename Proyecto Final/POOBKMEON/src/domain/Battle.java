package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Battle implements Serializable {
    ArrayList<Trainer> turnTrainers;


    public Battle(){
        turnTrainers = new ArrayList<>();
    }
}
