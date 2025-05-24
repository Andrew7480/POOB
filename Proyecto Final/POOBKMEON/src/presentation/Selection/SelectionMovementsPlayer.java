package presentation.Selection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVsMachine;

public class SelectionMovementsPlayer extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI pooBkemonGUI;
    private JButton come;
    private JButton doneButton; 
    private ModePlayerVsMachine gameMode;

    private SelectionMovementsPanel selection;

    public SelectionMovementsPlayer(POOBkemonGUI po, ModePlayerVsMachine father){
        gameMode = father;
        pooBkemonGUI = po;
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements(){
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.setOpaque(false);
        selection = new SelectionMovementsPanel(pooBkemonGUI);

        temp.add(selection);

        add(temp, BorderLayout.CENTER);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        //doneButton.setVisible(false);
        pooBkemonGUI.styleButton(doneButton);
        pooBkemonGUI.styleButton(come);
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
                    if (!selection.isSelectedMovements() || ! selection.isSelectedMovements()) {
                        JOptionPane.showMessageDialog(SelectionMovementsPlayer.this,
                    "Tienes que escoger todos los movimientos.",
                    "Movimientos no seleccionados.", JOptionPane.WARNING_MESSAGE);
                    return;
                    }
                    gameMode.pokemonsWithMovs = selection.getPokemonMovs();
                    gameMode.inicialPoks.inicializate(selection.getColor(), selection.getPokemonChoosen());
                   gameMode.changePanel("Iniciales");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SelectionMovementsPlayer.this, ex.getMessage());
                }
            }
        });

        come.addActionListener(e -> {
            reset();
            gameMode.changePanel("Inventory");
        });
    }

    public JButton getButtonBack(){
        return come;
    }

    public JButton getNextBJButton(){
        return doneButton;
    }

    public void inicializate(Color color1, ArrayList<String> pok1){
        selection.setColor(color1);
        selection.infoSelectedPokemons(pok1);
    }
    

    public void reset(){ 
        System.out.println("resetea todo de la seleccion movimientos dos players");
        selection.resetPokemonChosen();
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
