import java.awt.*;
import java.math.*;
/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    private static final int VERTICES=3;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 30;
        width = 40;
        xPosition = 140;
        yPosition = 15;
        color = "green";
        isVisible = false;
    }
    /**
     * Creates a new triangle in a new position.
       */
    public Triangle(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        color = "yellow";
        isVisible = true;
        height = 30;
        width = 40;
    }
    
    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the triangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the triangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the triangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the triangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the triangle horizontally.
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
     * Slowly move the triangle vertically.
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
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {  
        
        if ((newHeight <=0) || (newWidth <=0)) {
            System.out.println("La altura y el ancho no puede ser negativa.");

        }
        else {
            erase();
            height = newHeight;
            width = newWidth;
            draw();
        }
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

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Triangle's area
     **/
    public int area() {
        return (height*width)/2;
    }
    
    /**
     * Determine the side a triangle should have for it to be equilateral.
     **/
    private double sideToEquilateral(){
        int areaOfTriangle = area();
        double side = Math.sqrt((4*areaOfTriangle)/Math.sqrt(3));
        return side;
    }
    
    private double verify(){
        return ((Math.pow(sideToEquilateral(), 2))*Math.sqrt(3))/4;
    }
    /**
     * Makes a new triangle equilateral that have the same area equivalent
       **/
    public void equilateral(){
        int areaTriangle = area();
        double side = sideToEquilateral();
        int intSide = (int) side;
        //calcula la altura que deberia tener 
        double newHeight = (Math.sqrt(3)*side)/2;
        int intNewHeight = (int) newHeight;
        changeSize(intNewHeight, intSide);        
    }
    
    /**
     * it decreases its size times until it reaches a height.
       **/    
    public void shrink(int times, int oHeight){
        erase();
        int count = 0;
        double midHeight = (height-oHeight)/times;
        /*
         * Se disminuye la altura times veces, pero en algunos casos se pierde precisión al hacer la conversión.
           */
        while (count < times){
            height = height - ((int) midHeight);
            count ++;
            changeSize(height, width);
        }
    }
    
    /**
     * Move a triangle in a certain position.
       **/
    public void newPosition(int xPositionN, int yPositionN){
        erase();
        xPosition = xPositionN;
        yPosition = yPositionN;
        draw();
    }
    /**
     * Given area and width is determined the height of triangle.
       **/
    public int heightOfTringle(int areaN, int widthN){
        int determineHeight = (2*areaN)/widthN; 
        return determineHeight;
    }
}
