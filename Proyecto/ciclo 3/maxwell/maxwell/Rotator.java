package maxwell;


/**
 * Write a description of class Rotator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rotator extends Particle
{
    public Rotator(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        super(color,newXPosition,newYPosition,vx,vy,newIsLeft,newIsRed);
        state = ROTATOR;
    }
    
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
