/**
 * Write a description of class demonFace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemonFace
{
    private Circle faceDemon;
    private int posD;
    
    private int xPosition;
    private int yPosition;
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
            xPosition = newXPosition;
            yPosition = newYPosition;
            
        }
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
        return xPosition;
    }
    public int getPosY(){
        return yPosition;
    }
}
