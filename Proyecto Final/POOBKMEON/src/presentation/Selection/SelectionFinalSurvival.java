package presentation.Selection;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import presentation.POOBkemonGUI;
import presentation.Battle.BattleContainer;
import presentation.Datos.DatosTwoPlayersSurvival;
import presentation.ModesOfGame.ModePlayerVSPlayerSurvival;

public class SelectionFinalSurvival extends JPanel {
    private  String backgroundImage = "emerald";
    private POOBkemonGUI pooBkemonGUI;
    protected BattleContainer survivalBatalla;
    protected DatosTwoPlayersSurvival datos;
    private CardLayout cardLayout;
    protected String firstName;
    protected String secondName;
    private ModePlayerVSPlayerSurvival gameMode;

    public SelectionMovementsPanel selectionOne;
    public SelectionMovementsPanel selectionTwo;

    private JButton doneButton;
    private JButton come;
    private JButton aleatoryMovement;

    public SelectionFinalSurvival(POOBkemonGUI newPo, ModePlayerVSPlayerSurvival father){
        gameMode = father;
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.setOpaque(false);
        selectionOne = personalizateMovements();
        selectionTwo = personalizateMovements();
        temp.add(selectionOne);
        temp.add(selectionTwo);
        add(temp, BorderLayout.CENTER);
        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        aleatoryMovement = new JButton("Generar Nuevos Equipos");
        pooBkemonGUI.styleButton(doneButton);
        pooBkemonGUI.styleButton(come);
        pooBkemonGUI.styleButton(aleatoryMovement);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(aleatoryMovement);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        add(down, BorderLayout.SOUTH);
    }

    private void prepareActions(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode.inicializateGame();
            }});
        come.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameMode.changePanel("Datos");
            }
        });
        aleatoryMovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gameMode.generateAleatory();
            }
        });
    }

    public void inicializar(){
        selectionOne.infoSelectedPokemons(pooBkemonGUI.domain.getTrainer(gameMode.firstName).getInventory().getPokemonsName());
        selectionOne.setColor(new Color(0,0,255));
        selectionTwo.infoSelectedPokemons(pooBkemonGUI.domain.getTrainer(gameMode.secondName).getInventory().getPokemonsName());
        selectionTwo.setColor(new Color(255,0,0));
    }


    private SelectionMovementsPanel personalizateMovements(){
        return new SelectionMovementsPanel(pooBkemonGUI) {
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
    public void reset(){
        selectionOne.reset();
        selectionTwo.reset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}
