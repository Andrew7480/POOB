package domain;

import java.io.Serializable;

public class expertTrainer extends MachineTrainer implements Serializable{
    public expertTrainer(String newName) {
        super(newName);
    }


    public Movement decide(Pokemon target){
        return null;
    }

}
