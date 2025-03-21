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
   private Color color;
   private int count;
   public final static char YELLOW='y', RED='r', GREEN='g';
   public TrafficLight(City city, int row, int column){
       this.city=city;
       this.row=row;
       this.column=column;
       this.city.setItem(row,column,(Item)this);
   }
   @Override
   public void decide(){
       
   }
   @Override
   public Color getColor(){
       return Color.red;
   }
}
