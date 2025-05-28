package presentation.Datos;
import javax.swing.*;

import domain.PoobkemonException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVSPlayerSurvival;
import presentation.Selection.SelectionFinalSurvival;

public class DatosTwoPlayersSurvival extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresarNormal;
    private JButton continuar;
    private JButton chooserColorPlayerOne;
    private JButton chooserColorPlayerTwo;
    private POOBkemonGUI po;
    private ModePlayerVSPlayerSurvival gameMode;

    private JColorChooser colorChooser;
    public Color colorChosedPlayerOne;
    public Color colorChosedPlayerTwo;

    private JPanel panelSelection;

    private JButton playerOne;
    private JButton playerTwo;
    private String[] paths;
    private int indexPlayerOne = 0;
    private int indexPlayerTwo = 0;

    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
    public String playerOneName = "";
    public String playerTwoName = "";

    private Color choiceOne;
    private Color choiceTwo;

    public DatosTwoPlayersSurvival(POOBkemonGUI newPo, ModePlayerVSPlayerSurvival father){
        gameMode = father;
        po = newPo;
        chooserColorPlayerOne = new JButton("Choose Color");
        chooserColorPlayerTwo = new JButton("Choose Color");
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements(){
        setLayout(new BorderLayout());
        colorChooser = new JColorChooser();
        paths = new String[]{"/resources/trainers/Trainer1.png","/resources/trainers/Trainer2.png","/resources/trainers/Trainer3.png"};
        
        prepareButtons();
        playerPanel();
    }

    private void prepareButtons(){
        btnRegresarNormal = new JButton("Back");
        continuar = new JButton("CONTINUE");
        po.styleButton(continuar);
        po.styleButton(btnRegresarNormal);
        po.styleButtonchooser(chooserColorPlayerOne);
        po.styleButtonchooser(chooserColorPlayerTwo);
        
        
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresarNormal);
        buttonPanel.add(continuar);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void prepareActions(){
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playerOneName = playerOneNameField.getText().trim();
                    playerTwoName = playerTwoNameField.getText().trim();
                    try{
                        po.domain.isTrainerIsed(playerOneName);
                        po.domain.isTrainerIsed(playerTwoName);
                    }
                    catch(PoobkemonException i){
                        JOptionPane.showMessageDialog(DatosTwoPlayersSurvival.this, "Nombre no disponible", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (playerOneName.equals(playerTwoName)){
                        JOptionPane.showMessageDialog(DatosTwoPlayersSurvival.this, "No pueden tener el mismo nombre.","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(playerOneName.isEmpty() || playerTwoName.isEmpty()){
                        JOptionPane.showMessageDialog(DatosTwoPlayersSurvival.this, "Ambos nombres deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(colorChosedPlayerOne == null || colorChosedPlayerTwo == null){
                        JOptionPane.showMessageDialog(DatosTwoPlayersSurvival.this,"Ambos colores deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gameMode.firstName = playerOneName;
                    gameMode.secondName = playerTwoName;
                    gameMode.inicializate(playerOneName, colorChosedPlayerOne, playerTwoName, colorChosedPlayerTwo);
                    gameMode.teamsSurvival.inicializar();
                    gameMode.changePanel("teams");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(DatosTwoPlayersSurvival.this, ex.getMessage());
                }
            }
        });

        btnRegresarNormal.addActionListener(e -> {
            po.changePanel("survival");
        });

        chooserColorPlayerOne.addActionListener(e -> {
            choiceOne = colorChooser.showDialog(this, "Selecciona tu color", Color.BLUE);
            if (choiceOne != null) {
                colorChosedPlayerOne = choiceOne;
                chooserColorPlayerOne.setBackground(colorChosedPlayerOne);
                Color borderColor = colorChosedPlayerOne.darker();
                chooserColorPlayerOne.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
            }
        });
        
        chooserColorPlayerTwo.addActionListener(e -> {
            choiceTwo = colorChooser.showDialog(this, "Selecciona tu color", Color.RED);
            if (choiceTwo != null) {
                colorChosedPlayerTwo = choiceTwo;
                chooserColorPlayerTwo.setBackground(colorChosedPlayerTwo);
                Color borderColor = colorChosedPlayerTwo.darker();
                chooserColorPlayerTwo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
            }
        });
    }
    

    private void playerPanel() {
        panelSelection = new JPanel();
        panelSelection.setLayout(new GridLayout(1, 2));
        panelSelection.setOpaque(false);
        
        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.Y_AXIS));
        player1Panel.setOpaque(false);
        
        JLabel player1Label = new JLabel("Player One", JLabel.CENTER);
        player1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        
        player1Panel.add(player1Label);
        player1Panel.add(Box.createVerticalStrut(10));
        
        JPanel trainerPanel1 = createPlayerTrainer(1);
        trainerPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        player1Panel.add(trainerPanel1);
        player1Panel.add(Box.createVerticalStrut(20));
        
        JPanel namePanel1 = new JPanel();
        namePanel1.setLayout(new BoxLayout(namePanel1, BoxLayout.Y_AXIS));
        namePanel1.setOpaque(false);
        namePanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel1 = new JLabel("Enter name for Player 1");
        nameLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel1.add(nameLabel1);
        
        playerOneNameField = new JTextField(10);
        playerOneNameField.setFont(new Font("Arial", Font.BOLD, 20));
        playerOneNameField.setHorizontalAlignment(JTextField.CENTER);
        playerOneNameField.setMaximumSize(new Dimension(180, 40));
        playerOneNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56,56,56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        playerOneNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        namePanel1.add(Box.createVerticalStrut(5));
        namePanel1.add(playerOneNameField);
        player1Panel.add(namePanel1);
        player1Panel.add(Box.createVerticalStrut(15));
        
        JPanel colorPanel1 = new JPanel();
        colorPanel1.setOpaque(false);
        colorPanel1.add(chooserColorPlayerOne);
        colorPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        player1Panel.add(colorPanel1);
        
        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.Y_AXIS));
        player2Panel.setOpaque(false);
        
        JLabel player2Label = new JLabel("Player Two", JLabel.CENTER);
        player2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        
        player2Panel.add(player2Label);
        player2Panel.add(Box.createVerticalStrut(10));
        
        JPanel trainerPanel2 = createPlayerTrainer(2);
        trainerPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2Panel.add(trainerPanel2);
        player2Panel.add(Box.createVerticalStrut(20));
        
        JPanel namePanel2 = new JPanel();
        namePanel2.setLayout(new BoxLayout(namePanel2, BoxLayout.Y_AXIS));
        namePanel2.setOpaque(false);
        namePanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel2 = new JLabel("Enter name for Player 2");
        nameLabel2.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel2.add(nameLabel2);
        
        playerTwoNameField = new JTextField(10);
        playerTwoNameField.setFont(new Font("Arial", Font.BOLD, 20));
        playerTwoNameField.setHorizontalAlignment(JTextField.CENTER);
        playerTwoNameField.setMaximumSize(new Dimension(180, 40));
        playerTwoNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56,56,56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        playerTwoNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        namePanel2.add(Box.createVerticalStrut(5));
        namePanel2.add(playerTwoNameField);
        player2Panel.add(namePanel2);
        player2Panel.add(Box.createVerticalStrut(15));
        
        JPanel colorPanel2 = new JPanel();
        colorPanel2.setOpaque(false);
        colorPanel2.add(chooserColorPlayerTwo);
        colorPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2Panel.add(colorPanel2);
        
        panelSelection.add(player1Panel);
        panelSelection.add(player2Panel);
        
        add(panelSelection, BorderLayout.CENTER);
    }

    private JPanel createPlayerTrainer(int trainerNum) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JButton trainerButton;
        if (trainerNum == 1) {
            playerOne = createImageButton("Trainer1", paths[0]);
            trainerButton = playerOne;
            trainerButton.addActionListener(e -> cambiarTrainer(1));
        } else {
            playerTwo = createImageButton("Trainer2", paths[0]);
            trainerButton = playerTwo;
            trainerButton.addActionListener(e -> cambiarTrainer(2));
        }
        panel.add(trainerButton, BorderLayout.CENTER);
        return panel;
    }

    private void cambiarTrainer(int trainerNum) {
        if (trainerNum == 1) {
            indexPlayerOne = (indexPlayerOne + 1) % paths.length;
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPlayerOne]));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                playerOne.setIcon(new ImageIcon(scaledImage));
                playerOne.setToolTipText("Trainer: " + paths[indexPlayerOne]);
            } catch (Exception e) {
                playerOne.setText("No imagen??");
                playerOne.setIcon(null);
            }
        } else {
            indexPlayerTwo = (indexPlayerTwo + 1) % paths.length;
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPlayerTwo]));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                playerTwo.setIcon(new ImageIcon(scaledImage));
                playerTwo.setToolTipText("Trainer: " + paths[indexPlayerTwo]);
            } catch (Exception e) {
                playerTwo.setText("No imagen??");
                playerTwo.setIcon(null);
            }
        }
    }

    private JButton createImageButton(String name, String imagePath) {
        int width = 70, height = 70;
        JButton button = new JButton();

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            if (imagePath.toLowerCase().endsWith(".gif")) {
                button.setIcon(icon);
                button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            } else {
                Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            }
        } catch (Exception e) {
            button.setText("No imagen, intenta de nuevo.");
        }

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setToolTipText(name);
        button.setPreferredSize(new Dimension(width, height));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        return button;
    }

    public JButton getBtnRegresarNormal(){
        return btnRegresarNormal;
    }
    
    public JButton getButtonContinuar(){
        return continuar;
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}