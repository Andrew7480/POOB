
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
    
    
    public Pit(){
        
    }
    public void putSeeds(int seeds){
        
    }
    public void removeSeeds(int seeds){
        
    }
    public int seeds(){
        return
    }
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    public void changeColors(String pit, String seeds){
        
    }
    public void moveTo(int x, int y){
        erase();
        xPosition += x;
        yPosition += y;
        draw();
    }
}
