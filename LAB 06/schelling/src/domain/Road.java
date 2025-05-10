package domain;
import java.awt.Color;
import java.io.Serializable;
/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Road implements Item, Serializable
{
   private City city;
   private int row, column;

   /**
     * Create a new Kind of Item (Road) in the city
     * @param City city
     * @param int row
     * @param int column
    */
   public Road(City city, int row, int column){
       this.city=city;
       this.row=row;
       this.column=column;
       this.city.setItem(row,column,(Item)this);
   }
   /**
    * if there are no objects on the right, new roads will be created to block the grid.
      */
   @Override
   public void decide(){
       if (city.isEmpty(row, column+1)){
           Road cr = new Road(city,row,column+1 );
       }
   }
   /**
    * Set the color to gray.
      */
   @Override
   public Color getColor(){
       return Color.gray;
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
