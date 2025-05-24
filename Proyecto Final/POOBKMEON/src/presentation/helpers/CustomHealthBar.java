package presentation.helpers;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CustomHealthBar extends JProgressBar {
        public CustomHealthBar(int min, int max) {
            super(min, max);
            setUI(new PokemonHealthBarUI());
            setBorderPainted(false);
            setStringPainted(false);
            setOpaque(false);
        }

        private class PokemonHealthBarUI extends BasicProgressBarUI {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                
        int width = c.getWidth();
        int height = c.getHeight();
                
        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, width, height);
                
        int max = getModel().getMaximum();
        int min = getModel().getMinimum();
        int value = getModel().getValue();
                
        double progress = 0.0;
        if (max != min) {
            progress = (double)(value - min) / (double)(max - min);
        }
                
        int fillWidth = (int)(width * progress);
            
        Color healthColor;
                
        if (value > max * 0.5) {
            healthColor = new Color(88, 208, 120);
        } else if (value > max * 0.2) {
            healthColor = new Color(248, 208, 48);
        } else {
            healthColor = new Color(248, 88, 56);  
        }    
        g2d.setColor(healthColor);
        g2d.fillRect(0, 0, fillWidth, height);
        g2d.dispose();
            }
        }
    }