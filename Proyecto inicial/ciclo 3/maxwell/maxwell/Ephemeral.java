package maxwell;


/**
 * It's Ephemeral Particle
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Ephemeral extends Particle
{
    public Ephemeral(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        super(color,newXPosition,newYPosition,vx,vy,newIsLeft,newIsRed);
        state = EPHEMERAL;
    }
    
    private boolean reduceVelocities(){
        if (velocityX > 0) velocityX--;
        if (velocityY > 0) velocityY--;
        if (velocityX < 0) velocityX++;
        if (velocityY < 0) velocityY++;
        if (velocityX == 0 && velocityY == 0) return false;
        return true;
    }
    
    @Override
    public boolean afterBounce(int width, int height){
        return reduceVelocities();
    }
}
