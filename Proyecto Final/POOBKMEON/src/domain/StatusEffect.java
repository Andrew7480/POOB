package domain;
import java.io.*;
public abstract class StatusEffect{
        protected String name;
        private String description;
        protected int times;
        protected int status;

        public StatusEffect(String newName, String newDescription, int newTimes, int newStatus){
            name = newName;
            description = newDescription;
            times = newTimes;
            status = newStatus;
        }

        public abstract void affectPokemon(Pokemon affectPokemon);
    
}