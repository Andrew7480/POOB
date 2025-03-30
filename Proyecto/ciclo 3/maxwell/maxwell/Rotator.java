package maxwell;


/**
 * It's Rotator Particle
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Rotator extends Particle
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
    public Rotator(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        super(color,newXPosition,newYPosition,vx,vy,newIsLeft,newIsRed);
        state = ROTATOR;
    }
    /**
     * exchange the velocities  
     */
    public void changeVelocities(){
        int tempX = velocityX;
        int tempY = velocityY;
        if (velocityX == 0 || velocityY == 0){
            velocityX = tempY;
            velocityY = tempX;
            return;
        }
        velocityX = -tempY;
        velocityY = -tempX;
    }
}
