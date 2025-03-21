package domain;
import java.awt.Color;


public class Schelling extends Person
{
    public Schelling(City city, int row, int column){
        super(city,row,column);
        color = Color.BLUE;
        state = INDIFFERENT; //mirar despues
    }
    @Override
    public void decide(){
        int similarNeighbors = city.neighborsEquals(row,column);
        int Neighbors = city.neighbors(row,column);
        int diferentNeighbors = Neighbors-similarNeighbors;
        
        if (Neighbors ==0 || Neighbors == similarNeighbors){
            state =  INDIFFERENT;
        }
        if (similarNeighbors <= Neighbors/3){
            state = DISSATISFIED;
        }
        if(similarNeighbors > Neighbors/3 && similarNeighbors != Neighbors){
            state = HAPPY;
        }
        
        if (state == DISSATISFIED){
            if (city.isEmpty(row, column-1)){
                city.changeItemPosition(row,column,row,column-1,(Item)this);
                column --;
            }
        }
    
    }
    private void planningRoute(){};
    
}
