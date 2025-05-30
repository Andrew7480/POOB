package presentation.Selection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVSPlayer;


public class SelectionMovementsTwoPlayers extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private JButton aleatory;
    private ModePlayerVSPlayer gameMode;

    private SelectionMovementsPanel selectionOne;
    private SelectionMovementsPanel selectionTwo;


    public SelectionMovementsTwoPlayers(POOBkemonGUI pooBkemonGUI, ModePlayerVSPlayer father){
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
        selectionOne = new SelectionMovementsPanel(po);
        selectionTwo = new SelectionMovementsPanel(po);

        temp.add(selectionOne);
        temp.add(selectionTwo);

        add(temp, BorderLayout.CENTER);

        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        aleatory = new JButton("Aleatory");
        po.styleButton(doneButton);
        po.styleButton(come);
        po.styleButton(aleatory);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(aleatory);
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
                    if (!selectionOne.isSelectedMovements() || ! selectionOne.isSelectedMovements()) {
                        JOptionPane.showMessageDialog(SelectionMovementsTwoPlayers.this,
                    "Tienes que escoger todos los movimientos.",
                    "Movimientos no seleccionados.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                    gameMode.firstPokemonMovs = selectionOne.getPokemonMovs();
                    gameMode.secondPokemonMovs = selectionTwo.getPokemonMovs();
                    
                   
                   gameMode.inicialPoks.inicializate(selectionOne.getColor(), selectionOne.getPokemonChoosen(), selectionTwo.getColor(), selectionTwo.getPokemonChoosen());
                   gameMode.changePanel("Iniciales");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SelectionMovementsTwoPlayers.this, ex.getMessage());
                }
            }
        });

        come.addActionListener(e -> {
            reset();
            gameMode.changePanel("Inventory");
        });

        aleatory.addActionListener(e -> {
            try {
                selectionOne.aleatoryMovements();
                selectionTwo.aleatoryMovements();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(SelectionMovementsTwoPlayers.this, ex.getMessage());
            }
        });

    }

    public JButton getButtonBack(){
        return come;
    }

    public JButton getNextBJButton(){
        return doneButton;
    }

    public void inicializate(Color colorOne, ArrayList<String> pokOne, Color colorTwo, ArrayList<String> pokTwo){
        selectionOne.setColor(colorOne);
        selectionTwo.setColor(colorTwo);
        selectionOne.infoSelectedPokemons(pokOne);
        selectionTwo.infoSelectedPokemons(pokTwo);
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
