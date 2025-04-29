package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private String download = "download";
    private JPanel modosDeJuego;
    private POOBkemonGUI po;
    public JButton jugar;
    public JButton pokedex;
    
    public PrincipalPanel(POOBkemonGUI newPo) {
        po = newPo;
        setLayout(new BorderLayout());
        modosDeJuego = po.invisiblePanelWithOpacity();
        modosDeJuego.setOpaque(false);
        modosDeJuego.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        jugar = new JButton("Play");
        po.styleButtonExternal(jugar);
    
        pokedex = new JButton("Pokedex");
        po.styleButtonExternal(pokedex);

        modosDeJuego.add(jugar);
        modosDeJuego.add(pokedex);
        
        add(modosDeJuego, BorderLayout.SOUTH);
    }

    public JButton getButtonJugar(){
        return jugar;
    }

    public JButton getButtonPokedex(){
        return pokedex;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ download+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}