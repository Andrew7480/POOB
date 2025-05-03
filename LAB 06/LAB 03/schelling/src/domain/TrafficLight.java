package domain;
import java.awt.Color;
import java.io.Serializable;


/**
 * Write a description of class TrafficLight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrafficLight implements Item, Serializable{
   private City city;
   private int row, column;
   private int count = 1;
   public final static char YELLOW='y', RED='r', GREEN='g';
   /**
     * Create a new Kind of Item (Traffic Light) in the city
     * @param City city
     * @param int row
     * @param int column
    */
   public TrafficLight(City city, int row, int column){
       this.city=city;
       this.row=row;
       this.column=column;
       this.city.setItem(row,column,(Item)this);
   }
   /**
    * Starting from a global variable initialized at 1, it will increase and if it is greater than 4, the counter is reset to 1.
      */
   @Override
   public void decide(){
       count ++;
       if (count > 4) count =1;
   }
   /**
    * Changes color depending on the counter value (red - yellow - green - yellow)
      */
   @Override
   public Color getColor(){
       if (count == 1){
           return Color.red;
       }
       if (count == 2){
           return Color.yellow;
       }
       if (count == 3){
           return Color.green;
       }
       if (count == 4){
           return Color.yellow;
       }
       
       return Color.black;
   }
   /**Returns the row
    @return 
     */
    public final int getRow(){
        return row;
    }

    /**Returns the column
    @return 
     */
    public final int getColumn(){
        return column;
    }
}
