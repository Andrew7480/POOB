package domain;

import java.awt.Color;

/**Information about a person<br>
<b>(city,row,column,color)</b><br>
<br>
 */
public class Person extends Agent implements Item{
    private City city;
    private int row,column;    
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
    public final Color getColor(){
        return color;
    }


    /**Act
     */
    public void decide(){
         state=(getSteps() % 3 == 0 ? Agent.HAPPY: (getSteps() % 3 == 1 ? Agent.INDIFFERENT: Agent.DISSATISFIED));
    }

    /**Change its actual state
     */
    public final void change(){
        step();
    }
        
}
