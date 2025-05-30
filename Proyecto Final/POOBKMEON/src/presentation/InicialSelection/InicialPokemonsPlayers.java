package presentation.InicialSelection;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVSPlayer;

public class InicialPokemonsPlayers extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private ModePlayerVSPlayer gameMode;

    private SelectionInicialPokemons selectionOne;
    private SelectionInicialPokemons selectionTwo;


    public InicialPokemonsPlayers(POOBkemonGUI pooBkemonGUI, ModePlayerVSPlayer father){
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
        selectionOne = new SelectionInicialPokemons(po);
        selectionTwo = new SelectionInicialPokemons(po);

        temp.add(selectionOne);
        temp.add(selectionTwo);

        add(temp, BorderLayout.CENTER);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
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
                    if (selectionOne.sizeChosenPokemon() < 1 || selectionTwo.sizeChosenPokemon() < 1){
                        JOptionPane.showMessageDialog(InicialPokemonsPlayers.this, "Debes escoger " + selectionOne.MAX_CHANGED + "pokemon para iniciar la batalla", 
                        "Límite excedido", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (selectionOne.sizeChosenPokemon() > selectionOne.MAX_CHANGED || selectionTwo.sizeChosenPokemon() > selectionTwo.MAX_CHANGED){
                        JOptionPane.showMessageDialog(InicialPokemonsPlayers.this, "Solo puedes escoger uno para cambiar " + selectionOne.MAX_CHANGED + "pokemon", 
                        "Límite excedido", JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                    
                    gameMode.inicializateBattle(selectionOne.getColor(),selectionTwo.getColor(), selectionOne.getPokemonChoosed(), selectionTwo.getPokemonChoosed());
                    gameMode.changePanel("Battle");
                    gameMode.actualizar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        come.addActionListener(e -> {
            gameMode.movements.inicializate(selectionOne.getColor(), gameMode.inventory.getPokemonsChosenFightOne(),selectionTwo.getColor(), gameMode.inventory.getPokemonsChosenFightTwo());
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

    public void inicializate(Color colorOne, ArrayList<String> pokOne, Color colorTwo, ArrayList<String> pokTwo){
        selectionOne.inicializate(pokOne, colorOne);
        selectionTwo.inicializate(pokTwo, colorTwo);

    }
    

    public void reset(){ 
        System.out.println("resetea todo de la seleccion movimientos dos players");
        selectionOne.reset();
        selectionTwo.reset();
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
