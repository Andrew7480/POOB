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
    /**
     * Constructor of demon face
     * @param int xPosition
     * @param int yPosition
     * @param int width
     * @param int d
       */
    public DemonFace(int xPosition, int yPosition, int width, int height, int d){
        int newYPos = yPosition+height;
        int auxYMin=0;
        int auxYMax=height; //newYPos-yPosition;        
        if (d >= auxYMin && d <= auxYMax){
            faceDemon = new Circle("magenta",xPosition,yPosition+auxYMax-d,10);
            posD = d;
            faceDemon.changeSize(10);
            makeVisible();
        }
    }
    /**
     * return the position of the demon
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
}
