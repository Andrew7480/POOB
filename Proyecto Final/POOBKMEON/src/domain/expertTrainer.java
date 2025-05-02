package domain;

import java.io.Serializable;

public class ExpertTrainer extends MachineTrainer implements Serializable{
    public ExpertTrainer(String newName) {
        super(newName);
    }


    public Movement decide(Pokemon target){
        return null;
    }

}
