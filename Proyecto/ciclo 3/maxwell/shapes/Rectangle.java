package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 */


 
public class Rectangle extends Shape{

    public static final int EDGES = 4;
    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super(70, 15,"white");
        height = 30;
        width = 40;
    }
    /**
     * Create a new rectangle
     * @param int newXPosition
     * @param int newYPosition
     * @param int newWidth
     * @param int newHeight
       */
    public Rectangle (int newXPosition, int newYPosition, int newWidth, int newHeight){
        super(newXPosition, newYPosition,"burnt yellow");
        width = newWidth;
        height = newHeight;
    }
    /**
     * Create a new rectangle
     * @param int newXPosition
     * @param int newYPosition
     * @param int newWidth
     * @param int newHeight
     * @param String newColor
       */
    public Rectangle (int newXPosition, int newYPosition, int newWidth, int newHeight, String newColor){
        super(newXPosition, newYPosition,newColor);
        width = newWidth;
        height = newHeight;
    }
    /**
     * Create a new rectangle
     * @param int h
     * @param int w
       */
    public Rectangle(int h, int w){
        super(100, 100,"white");
        height = h;
        width = w;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    
    public void plusSize(){
        erase();
        height += 3;
        width += 3;
        draw();
    }
    public int getwidth(){
        return width;
    }
    /*
     * Draw the rectangle with current specifications on screen.
     */

    public void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
}

