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

    /**
     * Create a new Kind of person (Walker) in the city
     * @param City city
     * @param int row
     * @param int column
    */
    public Walker(City city, int row, int column){
        super(city,row,column);
        color = Color.green;
        state = INDIFFERENT;
    }
    /**
     * Begins to move north without leaving the grid, if it has neighbors it change its state to happy
     * if cant make a move change state to dissatisfied.
       */
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
    /**
     * returns the shape of the walker (SQUARE)
       */
    @Override
    public int shape(){
        return SQUARE;
    }
    
}
