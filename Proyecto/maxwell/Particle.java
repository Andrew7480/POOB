
/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle
{
    private int velocityX;
    private int velocityY;
    private Circle c;
    private boolean isLeft;
    public Particle(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft){
        velocityX =  vx;
        velocityY = vy;
        c = new Circle(color, newXPosition, newYPosition);
        isLeft = newIsLeft;
        c.makeVisible();
        makeVisibleParticle();
    }
    
    public void changeColorParticle(String color){
        c.changeColor(color);
    }
    public void setPositionParticle(int px, int py){
        c.setPosition(px,py);
    }
    
    public void makeVisibleParticle(){
        c.makeVisible();
    }
    public void makeInvisibleParticle(){
        c.makeInvisible();
    }
    
    public String getColor(){
        return c.getColor();
    }
    public void moveHorizontal(int distance){
        c.moveHorizontal(distance);
    }
    public void moveVertical(int distance){
        c.moveVertical(distance);
    }
    public int getVelocityX(){
        return velocityX;
    }
    public int getVelocityY(){
        return velocityY;
    }
    
    public int getXPositionC(){
        return c.getXPosition();
    }
    
    public int getYPositionC(){
        return c.getYPosition();
    }
    public boolean getIsLeft(){
        return isLeft;
    }
    public void setVelocityX(int x){
        velocityX = x;
    }
    public void setVelocityY(int y){
        velocityY = y;
    }
}
