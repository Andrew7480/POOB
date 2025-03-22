package domain;
import java.awt.Color;



/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Road implements Item
{
   private City city;
   private int row, column;
   public Road(City city, int row, int column){
       this.city=city;
       this.row=row;
       this.column=column;
       this.city.setItem(row,column,(Item)this);
   }
   @Override
   public void decide(){
       if (city.isEmpty(row, column+1)){
           Road cr = new Road(city,row,column+1 );
       }
   }
   @Override
   public Color getColor(){
       return Color.gray;
   }
}
