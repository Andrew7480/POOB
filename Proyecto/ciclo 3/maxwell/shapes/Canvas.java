package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;
    public int centerX;
    public int centerY;
    
    public void drawCartesianPlane() {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        centerX = width / 2;
        centerY = height / 2;
        
        graphic.setColor(Color.white);
        graphic.drawLine(0, centerY, width, centerY);
        graphic.drawLine(centerX, 0, centerX, height);
        canvas.repaint();
    }
    
    public int getCenterX(){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        centerX = width / 2;
        centerY = height / 2;
        return centerX;
    }
    public int getCenterY(){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        centerX = width / 2;
        centerY = height / 2;
        return centerY;
    }

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas(){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", 700, 700, 
                                         Color.black);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List <Object> objects;
    private HashMap <Object,ShapeDescription> shapes;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Canvas(String title, int width, int height, Color bgColour){
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, String color, Shape shape){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String colorString){
            if(colorString.equals("red"))
                graphic.setColor(Color.red);
            else if(colorString.equals("black"))
                graphic.setColor(Color.black);
            else if(colorString.equals("blue"))
                graphic.setColor(Color.blue);
            else if(colorString.equals("yellow"))
                graphic.setColor(Color.yellow);
            else if(colorString.equals("green"))
                graphic.setColor(Color.green);
            else if(colorString.equals("magenta"))
                graphic.setColor(Color.magenta);
            else if(colorString.equals("white"))
                graphic.setColor(Color.white);
            else if(colorString.equals("orange"))
                graphic.setColor(Color.orange);
            else if(colorString.equals("pink"))
                graphic.setColor(Color.pink);
            else if(colorString.equals("dark red"))
                graphic.setColor(new Color(204,0 ,0 ));
            else if(colorString.equals("light red"))
                graphic.setColor(new Color(255,102 ,102 ));
            else if(colorString.equals("dark blue"))
                graphic.setColor(new Color(0,0 ,153));
            else if(colorString.equals("light blue"))
                graphic.setColor(new Color(51,51 ,255 ));
            else if(colorString.equals("dark yellow"))
                graphic.setColor(new Color(204,204 ,0));   
            else if(colorString.equals("light yellow"))
                graphic.setColor(new Color(255,255 ,153 ));                
            else if(colorString.equals("dark green"))
                graphic.setColor(new Color(0,153 ,76));
            else if(colorString.equals("light green"))
                graphic.setColor(new Color(102,255 ,178 ));
            else if(colorString.equals("dark magenta"))
                graphic.setColor(new Color(76,0 ,153));   
            else if(colorString.equals("light magenta"))
                graphic.setColor(new Color(178,102 ,255 ));
            else if(colorString.equals("dark orange"))
                graphic.setColor(new Color(204,102 ,0));   
            else if(colorString.equals("light orange"))
                graphic.setColor(new Color(255,178 ,102 ));  
            else if(colorString.equals("dark pink"))
                graphic.setColor(new Color(204,0 ,204));
            else if(colorString.equals("light pink"))
                graphic.setColor(new Color(255,102 ,255 ));
            else if(colorString.equals("grey"))
                graphic.setColor(new Color(160,160 ,160));
            else if(colorString.equals("light grey"))
                graphic.setColor(new Color(192,192 ,192 ));
            else if(colorString.equals("dark grey"))
                graphic.setColor(new Color(64,64 ,64));
            else if(colorString.equals("violet"))
                graphic.setColor(new Color(76,0 ,153));
            else if(colorString.equals("lila"))
                graphic.setColor(new Color(102,0 ,102));
            else if(colorString.equals("turquoise"))
                graphic.setColor(new Color(0,204 ,204));    
            else if(colorString.equals("brown"))
                graphic.setColor(new Color(153,76 ,0));
            else if(colorString.equals("light brown"))
                graphic.setColor(new Color(51,25 ,0 ));
            else if(colorString.equals("dark brown"))
                graphic.setColor(new Color(102,51 ,0));    
            else if(colorString.equals("sky blue"))
                graphic.setColor(new Color(153,204 ,255 ));
            else if(colorString.equals("aquamarine"))
                graphic.setColor(new Color(0,204 ,204));
            else if(colorString.equals("light aquamarine"))
                graphic.setColor(new Color(0,255 ,255 ));
            else if(colorString.equals("dark aquamarine"))
                graphic.setColor(new Color(0,153 ,153));
            else if(colorString.equals("slime"))
                graphic.setColor(new Color(229,255 ,204));    
            else if(colorString.equals("pale yellow"))
                graphic.setColor(new Color(255,255 ,204));    
            else if(colorString.equals("burnt yellow"))
                graphic.setColor(new Color(153,153 ,0)); 
            else if(colorString.equals("porcelain"))
                graphic.setColor(new Color(255,204 ,204)); 
            else if(colorString.equals("ivory"))
                graphic.setColor(new Color(255,229,204)); 
            else if(colorString.equals("golden"))
                graphic.setColor(new Color(204,204 ,0)); 
            else if(colorString.equals("neon green"))
                graphic.setColor(new Color(57, 255, 20));
            else if(colorString.equals("neon blue"))
                graphic.setColor(new Color(77, 77, 255));
            else if(colorString.equals("neon pink"))
                graphic.setColor(new Color(255, 20, 147));
            else if(colorString.equals("neon yellow"))
                graphic.setColor(new Color(255, 255, 51));
            else if(colorString.equals("pastel blue"))
                graphic.setColor(new Color(173, 216, 230));
            else if(colorString.equals("pastel green"))
                graphic.setColor(new Color(119, 221, 119));
            else if(colorString.equals("pastel pink"))
                graphic.setColor(new Color(255, 182, 193));
            else if(colorString.equals("pastel purple"))
                graphic.setColor(new Color(179, 158, 181));
            else if(colorString.equals("wine"))
                graphic.setColor(new Color(114, 47, 55));
            else if(colorString.equals("olive"))
                graphic.setColor(new Color(128, 128, 0));
            else if(colorString.equals("bronze"))
                graphic.setColor(new Color(205, 127, 50));
            else if(colorString.equals("silver"))
                graphic.setColor(new Color(192, 192, 192));
            else if(colorString.equals("copper"))
                graphic.setColor(new Color(184, 115, 51));
            else if(colorString.equals("charcoal"))
                graphic.setColor(new Color(54, 69, 79));
            else if(colorString.equals("mustard"))
                graphic.setColor(new Color(255, 219, 88));
            else if(colorString.equals("teal"))
                graphic.setColor(new Color(0, 128, 128));
            else
                graphic.setColor(Color.black); //RGB EXTENDER
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
                       shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }
    public void drawImage(String imagePath, int x, int y, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image != null) {
                graphic.drawImage(image, x, y, width, height, null);
                canvas.repaint();
            }
        } catch (IOException e) {
            System.out.println("Error cargando la imagen: " + e.getMessage());
        }
    }
    /**
     * close the canvas
       */
    public void close(){
        frame.dispose();
        canvasSingleton = null;
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel{
        public void paint(Graphics g){
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color){
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic){
            setForegroundColor(colorString);
            graphic.draw(shape);
            graphic.fill(shape);
        }
    }

}
