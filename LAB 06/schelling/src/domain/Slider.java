package domain;


/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends Person
{

    /**
     * Create a new Kind of person (Slider) in the city
     * @param City city
     * @param int row
     * @param int column
    */    
    public Slider(City city, int row, int column){
        super(city,row,column);
        color = color.pink;
        state = HAPPY;
    }
    /**
     * Begins to move horizontally without leaving the grid, if it has neighbors it change its state to happy and moves opposite in the grid
     * if cant make a move change stay in the same position.
    */
    @Override
    public void decide(){
        
        if (city.isEmpty(row, column-1) && state == DISSATISFIED){
            city.changeItemPosition(row,column,row,column-1,(Item)this);
            column --;
            
        }
        
        if (city.isEmpty(row, column+1) && state == HAPPY){
            city.changeItemPosition(row,column,row,column+1,(Item)this);
            column ++;
            if (city.neighbors(row, column) >= 1){
                state = DISSATISFIED;
            }
        }
        
    }
}
