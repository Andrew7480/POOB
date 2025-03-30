package maxwell;
import shapes.*;
import java.util.ArrayList;

/**
 * It's a Hole
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Hole
{
    protected Rectangle blackHole;
    protected int maxParticles;
    public static final String MOVIL = "Movil", NORMAL = "Normal", EATPARTICLE = "EatParticle";
    protected boolean isLeft;
    protected String state;
    public static int attractor = 10;
    /**
     * Constructor of Hole
     * @param int xPosition
     * @param int yPosition
     * @param int particles
     * @param boolean whereIs
       */
    public Hole(int xPosition, int yPosition, int particles, boolean whereIs){
        blackHole = new Rectangle(xPosition, yPosition, 10, 10);
        maxParticles = particles;
        state = NORMAL;
        isLeft = whereIs;
    }
    public void reduceMaxParticles(){
        maxParticles--;
    }
    /**
     * Method that make visible of hole
       */
    public void makeVisibleHole(){
        blackHole.makeVisible();
    }
    /**
     * Method that make invisible the hole
       */
    public void makeInvisibleHole(){
        blackHole.makeInvisible();
    }
    /**
     * Method that set the position of the hole
     * @param int px
     * @param int py
       */
    public void setPositionHole(int px, int py){
        blackHole.makeInvisible();
        blackHole.setPosition(px, py);
        blackHole.makeVisible();
    }
    /**
     * return the max particles that can have the particle
       */
    public int getMaxParticles(){
        return maxParticles;
    }
    
    /**
     * return the x position of the hole
       */
    public int getXPosition(){
        return blackHole.getXPosition();
    }
    /**
     * return the y position of the hole
       */
    public int getYPosition(){
        return blackHole.getYPosition();
    }
    /**
     * return if It's a movil hole
     */
    public boolean isMovil(){
        return state.equals(MOVIL);
    }
    /**
     * return if It's a eatParticle hole
     */
    public boolean isEatParticle(){
        return state.equals(EATPARTICLE);
    }
    /**
     * return if is in the left
     */
    public boolean getWhereIs(){
        return isLeft;
    }
    /**
     * move the hole horizontally
     */
    public void moveHorizontal(int distance){
        blackHole.moveHorizontal(distance);
    }
    /**
     * move the hole vertically
     */
    public void moveVertical(int distance){
        blackHole.moveVertical(distance);
    }
    
}
