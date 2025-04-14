package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 */

public class Circle extends Shape{
    public static final double PI=3.1416;
    private int diameter;
    public Circle(){
        super(20, 15,"blue");
        diameter = 15;
    }
    
    public Circle(String newColor, int newXPosition, int newYPosition, int ra){
        super(newXPosition, newYPosition,newColor);
        diameter = ra;  //5
    }

    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

}
