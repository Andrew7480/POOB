package domain;
import java.awt.Color;


/**
 * Write a description of class Walker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Walker extends Person
{
    public Walker(City city, int row, int column){
        super(city,row,column);
        color = Color.green;
        state = INDIFFERENT;
    }
    @Override
    public void decide(){
        if (city.isEmpty(row-1, column)){
            city.changeItemPosition(row,column,row-1,column,(Item)this);
            row --;
            if (city.neighbors(row, column) >= 1){
                state = HAPPY;
            }
        }
        else{
            state = DISSATISFIED;
        }
    }
}
