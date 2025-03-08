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
    public DemonFace(int xPosition, int yPosition, int width, int height, int d){
        int newYPos = yPosition+height;
        int auxYMin=0;
        int auxYMax=height; //newYPos-yPosition;        
        if (d >= auxYMin && d <= auxYMax){
            faceDemon = new Circle("yellow",xPosition,yPosition+auxYMax-d);
            posD = d;
            faceDemon.changeSize(10);
            makeVisible();
        }
    }
    public int getD(){
        return posD;
    }
    public void makeVisible(){
        faceDemon.makeVisible();
    }
    public void makeInvisible(){
        faceDemon.makeInvisible();
    }
}
