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
    private void changeVelocities(int width, int height){
        int posX = getXPositionC();
        int posY = getYPositionC();
        int tempX = velocityX;
        int tempY = velocityY;
        if (isLeft){
            if(posX <= -width/2){
                velocityX = Math.abs(tempY);
                velocityY = tempX;
            }
            else if(posX >= 0){
                velocityX = -Math.abs(tempY);
                velocityY = tempX;
            }
        } 
        else{
            if(posX <= 0){
                velocityX = Math.abs(tempY);
                velocityY = tempX;
            }
            else if(posX >= width/2){
                velocityX = -Math.abs(tempY);
                velocityY = tempX;
            }
        }
        
        if(posY <= 0){
            velocityX = tempY;
            velocityY = Math.abs(tempX);
        }
        else if(posY >= height){
            velocityX = tempY;
            velocityY = -Math.abs(tempX);
        }
    }
    
    @Override
    public boolean afterBounce(int width, int height){
        if (getVelocityX() != getVelocityY()) changeVelocities(width,height);
        return true;
    }
}
