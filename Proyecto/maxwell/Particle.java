
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
    public Particle(String color, int newXPosition, int newYPosition, int vx, int vy){
        velocityX =  vx;
        velocityY = vy;
        c = new Circle(color, newXPosition, newYPosition);
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
    
}
