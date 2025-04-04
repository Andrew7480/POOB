import java.time.LocalDate;

/**
 * Write a description of interface Schedulable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface Schedulable
{
    default  public boolean isAvailableDuring( LocalDate date , int startTime, int endTime) {
        //logica
        return true;
    }
}
