package presentation;
import javax.swing.*;
import java.awt.*;
public class PrincipalPanel extends JPanel{
    private String download = "download";
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        setOpaque(false);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ download+".GIF"));
        g.drawImage(back.getImage(),0,0,getWidth(),getHeight(),this);
    }
}
