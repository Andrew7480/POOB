package domain;

import java.awt.Color;

/**Information about a person<br>
<b>(city,row,column,color)</b><br>
<br>
 */
public class Person extends Agent implements Item{
    protected City city;
    protected int row,column;    
    protected Color color;
    
    /**Create a new person (<b>row,column</b>) in the city <b>ac</b>..
     * @param city 
     * @param row 
     * @param column 
     */
    public Person(City city,int row, int column){
        this.city=city;
        this.row=row;
        this.column=column;
        this.city.setItem(row,column,(Item)this); 
        color=Color.blue;
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
   
    /**Returns the color
    @return 
     */
    @Override
    public final Color getColor(){
        return color;
    }

    /**Act
     */
    public void decide(){
        state=(getSteps() % 3 == 0 ? Agent.HAPPY: (getSteps() % 3 == 1 ? Agent.INDIFFERENT: Agent.DISSATISFIED));
        if (state == 'h') setColor(Color.magenta);
        if (state == 'i') setColor(Color.orange);
        if (state == 'd') setColor(Color.blue);
    }

    /**Change its actual state
     */
    public final void change(){
        step();
    }
    /**Set color of the person
     * @param Color newColor
       */
    public void setColor(Color newColor){
         color = newColor;
     }
}
