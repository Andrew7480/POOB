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
        pokedexPanel.setLayout(null);


        regresarPokedex = new JButton("Volver");
        po.styleButton(regresarPokedex);
        regresarPokedex.setBounds(10,10,100,30);
        pokedexPanel.add(regresarPokedex);

        JLabel titleLabel = new JLabel("POKÉDEX", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(300, 10, 200, 30);
        pokedexPanel.add(titleLabel);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(null);
        statsPanel.setBackground(new Color(0, 64, 0));
        statsPanel.setBounds(30, 60, 200, 150);
        pokedexPanel.add(statsPanel);




        add(pokedexPanel, BorderLayout.CENTER);

    }
    public JButton getButton(){
        return regresarPokedex;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        //g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
