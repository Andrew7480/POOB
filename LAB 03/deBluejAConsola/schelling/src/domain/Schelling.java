package domain;
import java.awt.Color;


public class Schelling extends Person
{
    /**
     * Create a new Kind of person (Slider) in the city
     * @param City city
     * @param int row
     * @param int column
    */
    public Schelling(City city, int row, int column){
        super(city,row,column);
        color = Color.MAGENTA;
        decideState();
    }
    /*
     * The schelling person decide how to feel for his/her neighborhood
       */
    private void decideState(){
        int similarNeighbors = city.neighborsEquals(row,column);
        int neighbors = city.neighbors(row,column);
        int diferentNeighbors = neighbors-similarNeighbors;
        
        if (neighbors ==0 || neighbors == similarNeighbors){
            state =  INDIFFERENT;
        }
        else if (similarNeighbors <= neighbors/3){
            state = DISSATISFIED;
        }
        else if(similarNeighbors > neighbors/3 && similarNeighbors != neighbors){
            state = HAPPY;
        }
    }
    
    /**
     * They only move if they are dissatisfied with their neighbors, if the are fine where they are they do not move, their state changes to indifferent
     * if the neighbors, they are dissatisfied if less than 1/3 of the neighbors are not like them, they are satisfied if more than 1/3 of the neighbors
     * are like them and not all are like them.
    */
    @Override
    public void decide(){
        decideState();
        
        if (state == DISSATISFIED){
            if (city.isEmpty(row, column-1)){
                city.changeItemPosition(row,column,row,column-1,(Item)this);
                column --;
            }
            else if (city.isEmpty(row-1, column)){
                city.changeItemPosition(row,column,row-1,column,(Item)this);
                row --;
            }
            else if (city.isEmpty(row+1,column)){
                city.changeItemPosition(row,column,row+1,column,(Item)this);
                row ++;
            }
            else if (city.isEmpty(row, column+1)){
                city.changeItemPosition(row,column,row,column+1,(Item)this);
                column ++;
            }
        }
    
    }
    
}
