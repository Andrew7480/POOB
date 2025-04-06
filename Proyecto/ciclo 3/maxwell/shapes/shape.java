package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Shape
{
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    
    public abstract void draw();
    public abstract void erase();
    
    public Shape(int x, int y, String newColor){
        xPosition = x;
        yPosition = y;
        color = newColor;
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
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
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
    public void setPosition(int px, int py){
        xPosition = px;
        yPosition = py;
    }
    
    public String getColor(){
        return color;
    }
    
    public int getXPosition(){
        return xPosition;
    }
    
    public int getYPosition(){
        return yPosition;
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
