package domain;
import java.awt.Color;


/**
 * Write a description of class TrafficLight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrafficLight implements Item
{
   private City city;
   private int row, column;
   private int count = 1;
   public final static char YELLOW='y', RED='r', GREEN='g';
   public TrafficLight(City city, int row, int column){
       this.city=city;
       this.row=row;
       this.column=column;
       this.city.setItem(row,column,(Item)this);
   }
   @Override
   public void decide(){
       count ++;
       if (count > 4) count =1;
   }
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
}
