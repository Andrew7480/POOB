import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class demonFace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemonFace
{   
    private int posD;
    private boolean isVisible;
    private String demonImage = "C:\\Users\\RYZEN\\Documents\\POOB\\POOB\\Proyecto\\hellish-grin-aggressive-devil-s-face-logo-design-demonic-impression-black-icon-of-devil-s-sinister-visage-vector.jpg";
    private Canvas canvas;
    public DemonFace(int xPosition, int yPosition,int w, int h, int d){
        canvas = Canvas.getCanvas();
        if (d > yPosition && d < yPosition + h){
            posD = d;
            canvas.drawImage(demonImage,xPosition+w-(w/8)+5,yPosition+d,w/4,h/4);
            makeVisible(xPosition+w-(w/8)+5,yPosition+d,w/4,h/4);
        }
    }
    public int getD(){
        return posD;
    }
   public void makeVisible(int xPosition, int yPosition, int w, int h) {
        if (!isVisible) {
            canvas.drawImage(demonImage,xPosition,yPosition,w,h);
            isVisible = true;
        }
    }
    public void makeInvisible() {
        if (isVisible) {
            canvas.erase(demonImage);
            isVisible = false;
        }
    }
}
