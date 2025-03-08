
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
    private Circle c1;
    private boolean isLeft;
    public Particle(String color, int newXPosition, int newYPosition, int vx, int vy, boolean newIsLeft){
        velocityX =  vx;
        velocityY = vy;
        c = new Circle(color, newXPosition+2, newYPosition+2, 4);
        isLeft = newIsLeft;
        
        if (isLeft){
            c1 = new Circle("red",newXPosition,newYPosition,8);
        }
        if (!isLeft){
            c1 = new Circle("blue",newXPosition,newYPosition,8);
        }
        makeVisibleParticle();
    }
    
    public void changeColorParticle(String color){
        c.changeColor(color);
    }
    public void setPositionParticle(int px, int py){
        c.setPosition(px,py);
        c1.setPosition(px+2,py+2);
    }
    
    public void makeVisibleParticle(){
        c1.makeVisible();
        c.makeVisible();
    }
    public void makeInvisibleParticle(){
        c1.makeInvisible();
        c.makeInvisible();
    }
    
    public String getColor(){
        return c.getColor();
    }
    public void moveHorizontal(int distance){
        c.moveHorizontal(distance);
        c1.moveHorizontal(distance);
    }
    public void moveVertical(int distance){
        c.moveVertical(distance);
        c1.moveVertical(distance);
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
