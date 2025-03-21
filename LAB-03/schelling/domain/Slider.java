package domain;


/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends Person
{
    
    public Slider(City city, int row, int column){
        super(city,row,column);
        color = color.pink;
        state = HAPPY;
    }
    
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
