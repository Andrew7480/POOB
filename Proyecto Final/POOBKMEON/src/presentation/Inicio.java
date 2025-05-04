package presentation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import java.awt.Graphics;
import java.awt.*;


public class Inicio extends JPanel{
    private String backgroundImage = "inicio";
    private POOBkemonGUI pooBkemonGUI;


    public Inicio(POOBkemonGUI pooBkemon){
        pooBkemonGUI = pooBkemon;
        prepareActions();
    }
    private void prepareActions(){       
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
