package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PokedexPanel extends JPanel{
    private String backgroundImage = "Pokémon";
    private JPanel pokedexPanel;
    private JButton regresarPokedex;
    private POOBkemonGUI po;
    
    public PokedexPanel(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        pokedexPanel = po.invisiblePanelWithoutOpacity();
        pokedexPanel.setOpaque(false);
        pokedexPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20 , 10));
        regresarPokedex = new JButton("Volver");
        po.styleButton(regresarPokedex);
        pokedexPanel.add(regresarPokedex);
        add(pokedexPanel, BorderLayout.SOUTH);

    }
    public JButton getButton(){
        return regresarPokedex;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
