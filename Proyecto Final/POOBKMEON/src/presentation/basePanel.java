package presentation;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class basePanel extends JPanel{

    protected abstract void prepareElements();
    protected abstract void prepareActions();
    protected abstract void paintComponent(Graphics g);

}
