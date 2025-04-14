package maxwell;


/**
 * It's Flying Particle
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Flying extends Particle
{
    /**
     * Constructor of Particle
     * @param String color
     * @param int newXPosition
     * @param int newYPosition
     * @param int vx
     * @param int vy
     * @param boolean newIsLeft
       */
    public Flying(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        super(color,newXPosition,newYPosition,vx,vy,newIsLeft,newIsRed);
        state = FLYING;
    }
    public boolean afterBounce(int width, int height){
        return true;
    }
}
