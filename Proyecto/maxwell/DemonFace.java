/**
 * Write a description of class demonFace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemonFace
{
    private Circle faceDemon = new Circle();
    private int posD;
    public DemonFace(int xPosition, int yPosition, int width, int height, int d){
        if (d > yPosition && d < yPosition + height){
            posD = d;
            faceDemon.changeSize(width/8);
            faceDemon.moveHorizontal(xPosition+width-(width/8)+2);
            faceDemon.moveVertical(d);
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
