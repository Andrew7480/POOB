package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private String download = "download";
    private JPanel modosDeJuego;
    public JButton modoNormal;
    public JButton modoSurvival;
    public JButton pokedex;
    
    public PrincipalPanel() {
        setLayout(new BorderLayout());
        modosDeJuego = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 180));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        modosDeJuego.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        modosDeJuego.setOpaque(false);

        modoNormal = new JButton("Modo Normal");
        modoNormal.setFont(new Font("Times new Roman", Font.BOLD, 16));
        modoNormal.setBackground(new Color(30, 30, 180));
        modoNormal.setForeground(Color.WHITE);
        modoNormal.setFocusPainted(false);
        modoNormal.setBorder(BorderFactory.createRaisedBevelBorder());
        modoNormal.setPreferredSize(new Dimension(150, 40));
        
        modoSurvival = new JButton("Modo Survival");
        modoSurvival.setFont(new Font("Times new Roman", Font.BOLD, 16));
        modoSurvival.setBackground(new Color(30, 30, 180));
        modoSurvival.setForeground(Color.WHITE);
        modoSurvival.setFocusPainted(false);
        modoSurvival.setBorder(BorderFactory.createRaisedBevelBorder());
        modoSurvival.setPreferredSize(new Dimension(150, 40));
        
        pokedex = new JButton("Pokedex");
        pokedex.setFont(new Font("Times new Roman",Font.BOLD,16));
        pokedex.setBackground(new Color(30,30,180));
        pokedex.setForeground(Color.WHITE);
        pokedex.setFocusPainted(false);
        pokedex.setBorder(BorderFactory.createRaisedBevelBorder());
        pokedex.setPreferredSize(new Dimension(150,40));

        modosDeJuego.add(modoNormal);
        modosDeJuego.add(modoSurvival);
        modosDeJuego.add(pokedex);
        
        add(modosDeJuego, BorderLayout.SOUTH);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ download+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}