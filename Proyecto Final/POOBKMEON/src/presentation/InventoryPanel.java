package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class InventoryPanel extends JPanel{
    private String backgroundImage = "download";
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}