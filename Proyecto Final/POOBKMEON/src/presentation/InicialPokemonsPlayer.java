package presentation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class InicialPokemonsPlayer extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private ModePlayerVsMachine gameMode;

    private SelectionInicialPokemons selection;


    public InicialPokemonsPlayer(POOBkemonGUI pooBkemonGUI, ModePlayerVsMachine father){
        gameMode = father;
        po = pooBkemonGUI;
        prepareElements();
        prepareActions();
    }
    

    private void prepareElements(){
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.setOpaque(false);
        selection = new SelectionInicialPokemons(po);

        temp.add(selection);

        add(temp, BorderLayout.CENTER);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        //doneButton.setVisible(false);
        po.styleButton(doneButton);
        po.styleButton(come);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        add(down, BorderLayout.SOUTH);
    }

    private void prepareActions(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selection.sizeChosenPokemon() < 1){
                        JOptionPane.showMessageDialog(InicialPokemonsPlayer.this, "Debes escoger " + selection.MAX_CHANGED + "pokemon para iniciar la batalla", 
                        "Límite excedido", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (selection.sizeChosenPokemon() > selection.MAX_CHANGED ){
                        JOptionPane.showMessageDialog(InicialPokemonsPlayer.this, "Solo puedes escoger uno para cambiar " + selection.MAX_CHANGED + "pokemon", 
                        "Límite excedido", JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                    
                    gameMode.inicializateBattle(selection.getColor(), selection.getPokemonChoosed());
                    gameMode.actualizar();
                    gameMode.changePanel("Battle");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(InicialPokemonsPlayer.this, ex.getMessage());
                }
            }
        });

        come.addActionListener(e -> {
            reset();
            gameMode.changePanel("Movimientos");
        });
    }

    public JButton getButtonBack(){
        return come;
    }

    public JButton getNextBJButton(){
        return doneButton;
    }

    public void inicializate(Color color1, ArrayList<String> pok1){
        selection.inicializate(pok1, color1);
    }
    

    public void reset(){ 
        System.out.println("resetea todo de la seleccion movimientos dos players");
        selection.reset();
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
