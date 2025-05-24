package presentation.Selection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModeMachineVsMachine;
public class SelectionFinalMachines extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private ModeMachineVsMachine gameMode;

    private SelectionMovementsPanel selection1;
    private SelectionMovementsPanel selection2;

    private ArrayList<String> pokemonesChosen;
    private ArrayList<String> pokemonesChosenTwo;

    public SelectionFinalMachines(POOBkemonGUI pooBkemonGUI, ModeMachineVsMachine father){
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
        selection1 = personalizateMovements();
        selection2 = personalizateMovements();
        temp.add(selection1);
        temp.add(selection2);
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
                gameMode.inicializate();
                gameMode.actualizar();
            }
        });
        come.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameMode.changePanel("Datos");
            }
        });
    }

    public void inicializar(){
        selection1.infoSelectedPokemons(po.domain.getPokemonAlives(gameMode.datos.machineTrainerFirst));
        selection1.setColor(gameMode.colorPlayerOne);
        selection2.infoSelectedPokemons(po.domain.getPokemonAlives(gameMode.datos.machineTrainerSecond));
        selection2.setColor(gameMode.colorPlayerTwo);
    }
    private SelectionMovementsPanel personalizateMovements(){
        return new SelectionMovementsPanel(po) {
                @Override
                public JPanel createMovementPanel(String namePokemon, ArrayList<String> movements, String imagePath) {
                    if (namePokemon.equals("") || movements ==null || imagePath.equals("")) return new JPanel();
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setOpaque(false);
                    JLabel imageLabel = new JLabel();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/"+imagePath +".png"));
                    Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(imageLabel, BorderLayout.NORTH);
                    JLabel nameLabel = new JLabel(namePokemon, SwingConstants.CENTER);
                    panel.add(nameLabel, BorderLayout.CENTER);
                    JPanel movesPanel = new JPanel(new GridLayout(2, 1));
                    movesPanel.setOpaque(false);
                    JPanel Arriba = new JPanel(new FlowLayout());
                    JPanel Abajo = new JPanel(new FlowLayout());
                    Arriba.setOpaque(false);
                    Abajo.setOpaque(false);
                    for (int h = 0; h < 4; h++){
                        JButton moveButton = new JButton();
                        moveButton.setEnabled(false);
                        if (h % 2 == 0) Arriba.add(moveButton);
                        else{Abajo.add(moveButton);}
                    }
                    movesPanel.add(Arriba);
                    movesPanel.add(Abajo);

                    panel.add(movesPanel, BorderLayout.SOUTH);

                    return panel;
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}