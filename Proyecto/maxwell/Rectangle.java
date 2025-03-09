import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle{

    public static int EDGES = 4;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "white";
        isVisible = false;
    }
    /**
     * Create a new rectangle
     * @param int newXPosition
     * @param int newYPosition
     * @param int newWidth
     * @param int newHeight
       */
    public Rectangle (int newXPosition, int newYPosition, int newWidth, int newHeight){
        xPosition = newXPosition;
        yPosition = newYPosition;
        width = newWidth;
        height = newHeight;
        color = "green";
        isVisible = false;
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
        xPosition = newXPosition;
        yPosition = newYPosition;
        width = newWidth;
        height = newHeight;
        color = newColor;
        isVisible = false;
    }
    /**
     * Create a new rectangle
     * @param int h
     * @param int w
       */
    public Rectangle(int h, int w){
        height = h;
        width = w;
        xPosition = 100;
        yPosition = 100;
        color = "white";
        isVisible = false;
    }

    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }
    /**
     * Move the rectangle to new positions in x or y
     * @param int newX
     * @param int newY
       */
    public void moveTo(int newX, int newY){
        erase();
        xPosition = newX;
        yPosition = newY;
        draw();
    }
    
    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
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
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    /**
     * return the position in x of the rectangle
       */
    public int getXPosition(){
        return xPosition;
    }
    /**
     * return the position in y of the rectangle
       */
    public int getYPosition(){
        return yPosition;
    }
    /**
     * set the position of the rectangle
     * @param int px
     * @param int py
       */
    public void setPosition(int px, int py){
        xPosition = px;
        yPosition = py;
    }
    
    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
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
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    /**
     * Shows the cartesian plane of canva
       */
    public void showCenter(){
        Canvas canvas= Canvas.getCanvas();
        canvas.drawCartesianPlane();
    }
    /**
     * Method that determines the center of the rectangle
       */
    public ArrayList<Integer> getCenterXY(){
        Canvas canvas= Canvas.getCanvas();
        int centerX = canvas.getCenterX();
        int centerY = canvas.getCenterY();
        return new ArrayList<>(Arrays.asList(centerX,centerY));
    }
}

