package domain;

import java.io.Serializable;
import java.awt.Color;
public class ExpertTrainer extends MachineTrainer implements Serializable{
    public ExpertTrainer(String newName, Color newColor) {
        super(newName, newColor);
    }


    public Movement decide(Pokemon target){
        return null;
    }

}
