
/**
 * Write a description of class Pit here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class Pit
{
    private boolean isVisible;
    private int xPosition;
    private int yPosition;
    private String color;
    private Rectangle square;
    
    
    public Pit(){
        square = new Rectangle();
        isVisible = true;
        color = "blue";
        xPosition = 10;
        yPosition = 20;
    }
    public void putSeeds(int seeds){
        
    }
    public void removeSeeds(int seeds){
        
    }
    /*
    public int seeds(){
        return
    }
    */
    public void makeVisible(){
        isVisible = true;
        square.makeVisible();
    }
    public void makeInvisible(){
        square.makeInvisible();
        isVisible = false;
    }
    public void changeColors(String pit, String seeds){
        
    }
    public void moveTo(int x, int y){
        square.makeInvisible();
        xPosition += x;
        yPosition += y;
        square.makeVisible();
    }
}
