package maxwell;
import shapes.*;

/**
 * It's Particle
 * 
 * @author Andrés Cardozo && Tulio Riaño 
 * @version (a version number or a date)
 */
public class Particle
{
    protected int velocityX;
    protected int velocityY;
    protected Circle c;
    protected Circle c1;
    protected boolean isLeft;
    protected boolean isRed;
    public static final String EPHEMERAL = "Ephemeral",FLYING = "Flying",ROTATOR = "Rotator", NORMAL = "Normal";
    protected String state;
    
    /**
     * Constructor of Particle
     * @param String color
     * @param int newXPosition
     * @param int newYPosition
     * @param int vx
     * @param int vy
     * @param boolean newIsLeft
       */
    public Particle(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft, boolean newIsRed){
        velocityX =  vx;
        velocityY = vy;
        c = new Circle(color, newXPosition+2, newYPosition+2, 4);
        isLeft = newIsLeft;
        isRed = newIsRed;
        if (newIsRed){
            c1 = new Circle("red",newXPosition,newYPosition,8);
        }
        if (!newIsRed){
            c1 = new Circle("blue",newXPosition,newYPosition,8);
        }
        state = NORMAL;
    }
    
    /**
     * change color of the particles 
     * @param String color
       */
    public void changeColorParticle(String color){
        c.changeColor(color);
    }
    /**
     * set position of particles
     * @param int px
     * @param int py
       */
    public void setPositionParticle(int px, int py){
        c.setPosition(px+2,py+2);
        c1.setPosition(px,py);
    }
    /**
     * make visible the particle
       */
    public void makeVisibleParticle(){
        c1.makeVisible();
        c.makeVisible();
    }
    /**
     * make invisible particle
       */
    public void makeInvisibleParticle(){
        c1.makeInvisible();
        c.makeInvisible();
    }
    /**
     * get the color of the particle
       */
    public String getColor(){
        return c.getColor();
    }
    public String getColorForBoolean(){
        return c1.getColor();
    }
    /**
     * move horizontal the particle
     * @param int distance
       */
    public void moveHorizontal(int distance){
        c.moveHorizontal(distance);
        c1.moveHorizontal(distance);
    }
    /**
     * move vertical the particle
     * @param int distance
       */
    public void moveVertical(int distance){
        c.moveVertical(distance);
        c1.moveVertical(distance);
    }
    /**
     * return the velocity in X of the particle
       */
    public int getVelocityX(){
        return velocityX;
    }
    /**
     * return the velocity in Y of the particle
       */
    public int getVelocityY(){
        return velocityY;
    }
    /**
     * return the position in X of the particle
       */
    public int getXPositionC(){
        return c1.getXPosition();
    }
    /**
     * return the position in Y of the particle
       */
    public int getYPositionC(){
        return c1.getYPosition();
    }
    /**
     * return if is red
       */
    public boolean getIsRed(){
        return isRed;
    }
    
    /**
     * return if is in the left
       */
    public boolean getIsLeft(){
        return isLeft;
    }
    /**
     * set the velocity in x of the particle
     * @param int x
       */
    public void setVelocityX(int x){
        velocityX = x;
    }
    /**
     * set the velocity in y of the particle
     * @param int y
       */
    public void setVelocityY(int y){
        velocityY = y;
    }
    
    /**
     * change to the other chamber
       */
    public void changeIsLeft(){
        isLeft = !isLeft;
    }
    /**
     * return if It's a ephemeral Particle
       */
    public boolean isEphemeral(){
        return state.equals(EPHEMERAL);
    }
    /**
     * return if It's a flying Particle
       */
    public boolean isFlying(){
        return state.equals(FLYING);
    }
    /**
     * return if It's a rotator Particle
       */
    public boolean isRotator(){
        return state.equals(ROTATOR);
    }
    
    
}
