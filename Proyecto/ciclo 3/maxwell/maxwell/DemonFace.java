package maxwell;
import shapes.*;
/**
 * Its a DemonFace
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class DemonFace
{
    protected Circle faceDemon;
    public static final String BLUE = "Blue", WEAK = "Weak", NORMAL = "Normal";
    protected String state;
    protected int posD;
    /**
     * Constructor of demon face
     * @param int xPosition
     * @param int yPosition
     * @param int width
     * @param int d
       */
    public DemonFace(int newXPosition, int newYPosition, int width, int height, int d){
        int newYPos = newYPosition + height;
        int auxYMin=0;
        int auxYMax=height; //newYPos-yPosition;  
        if (d >= auxYMin && d <= auxYMax){
            faceDemon = new Circle("dark red",newXPosition,newYPosition+auxYMax-d,10);
            posD = d;
            faceDemon.changeSize(10);
        }
        state = NORMAL;
    }
    /**
     * return the position of the demon in the board
       */
    public int getPosD(){
        return posD;
    }
    /**
     * make visible of the face demon
       */
    public void makeVisible(){
        faceDemon.makeVisible();
    }
    /**
     * make invisible of the face demon
       */
    public void makeInvisible(){
        faceDemon.makeInvisible();
    }
    
    public int getPosX(){
        return faceDemon.getXPosition();
    }
    public int getPosY(){
        return faceDemon.getYPosition();
    }
    
    
    
}
