package maxwell;


/**
 * Write a description of class Normal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Normal extends Particle
{   
    public Normal(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        super(color,newXPosition,newYPosition,vx,vy,newIsLeft,newIsRed);
        state = NORMAL;
    }
    @Override 
    public boolean afterBounce(int width, int height){
        return true;
    }
}
