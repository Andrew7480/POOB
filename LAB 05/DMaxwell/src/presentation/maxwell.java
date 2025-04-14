package presentation;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;


public class maxwell extends JPanel{
        public static int tamaño = 10;
        private static Color color1 = Color.RED;
        private static Color color2 = Color.BLUE;

    public maxwell(){
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,1));
        setSize(getWidth(), getHeight());
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        add(new PanelDerecho());

    }
    public void setColor1(Color color){
        color1 = color;
    }
    public void setColor2(Color color){
        color2 = color;
    }
    class PanelDerecho extends JPanel {
        public PanelDerecho() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color1);
            g.fillRect(30, tamaño, tamaño, tamaño);
            g.setColor(color2);
            g.fillRect(100, 20, tamaño, tamaño);
            g.setColor(color2);
            g.fillRect(50, 80, tamaño, tamaño);
            g.setColor(color1);
            g.fillRect(110, 50, tamaño, tamaño);
            g.setColor(Color.BLACK);
            g.fillRect(getWidth()/2, 0, tamaño, getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(getWidth()/2, getHeight()/2, tamaño, tamaño);
            g.setColor(color2);
            g.fillRect(20, 20, tamaño, tamaño);
            g.setColor(color2);
            g.fillRect(70, 50, tamaño, tamaño);
            g.setColor(color1);
            g.fillRect(30, 70, tamaño, tamaño);
        }
        

    }
    public void refresh(){
        repaint();
    }



}