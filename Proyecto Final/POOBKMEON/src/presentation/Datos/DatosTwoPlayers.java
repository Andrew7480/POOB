package presentation.Datos;
import javax.swing.*;
import domain.PoobkemonException;
import java.awt.*;
import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVSPlayer;

public class DatosTwoPlayers extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresarNormal;
    private JButton continuar;
    private JButton chooserColorPlayerOne;
    private JButton chooserColorPlayerTwo;
    private POOBkemonGUI po;
    private ModePlayerVSPlayer gameMode;

    private JColorChooser colorChooser;
    private Color colorChosedPlayerOne;
    private Color colorChosedPlayerTwo;

    private JPanel chooseDifficulty;

    private JButton playerOne;
    private JButton playerTwo;
    private String[] paths;
    private int indexPlayerOne = 0;
    private int indexPlayerTwo = 0;

    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
    private String playerOneName = "";
    private String playerTwoName = "";

    private Color choiceOne;
    private Color choiceTwo;

    public DatosTwoPlayers(POOBkemonGUI newPo, ModePlayerVSPlayer father){
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
        paths = new String[]{"/resources/trainers/Trainer1.png","/resources/trainers/Trainer2.png","/resources/trainers/Trainer3.png","/resources/trainers/Trainer4.png",
        "/resources/trainers/Trainer5.png","/resources/trainers/Trainer6.png","/resources/trainers/Trainer7.png","/resources/trainers/Trainer8.png"};
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
        continuar.addActionListener(e ->
            confirmPlayerInfo());

        btnRegresarNormal.addActionListener(e -> {
            po.changePanel("normal");
        });

        chooserColorPlayerOne.addActionListener(e -> {
            choiceOne = JColorChooser.showDialog(this, "Selecciona tu color", Color.BLUE);
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
            choiceTwo = JColorChooser.showDialog(this, "Selecciona tu color", Color.RED);
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
    
    private void confirmPlayerInfo(){
        try {
                    playerOneName = playerOneNameField.getText().trim();
                    playerTwoName = playerTwoNameField.getText().trim();
                    try{
                        po.domain.isTrainerIsed(playerOneName);
                        po.domain.isTrainerIsed(playerTwoName);
                    }
                    catch(PoobkemonException i){
                        JOptionPane.showMessageDialog(DatosTwoPlayers.this, "Nombre no disponible", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (playerOneName.equals(playerTwoName)){
                        JOptionPane.showMessageDialog(DatosTwoPlayers.this, "No pueden tener el mismo nombre.","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(playerOneName.isEmpty() || playerTwoName.isEmpty()){
                        JOptionPane.showMessageDialog(DatosTwoPlayers.this, "Ambos nombres deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(colorChosedPlayerOne == null || colorChosedPlayerTwo == null){
                        JOptionPane.showMessageDialog(DatosTwoPlayers.this,"Ambos colores deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gameMode.firstName = playerOneName;
                    gameMode.secondName = playerTwoName;

                    gameMode.inventory.inicializate(choiceOne,choiceTwo);
                    gameMode.changePanel("Inventory");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DatosTwoPlayers.this, ex.getMessage());
                }

    }
    
    private void playerPanel() {
        chooseDifficulty = new JPanel();
        chooseDifficulty.setLayout(new GridLayout(1, 2));
        chooseDifficulty.setOpaque(false);
        
        JPanel playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
        playerOnePanel.setOpaque(false);
        
        JLabel playerOneLabel = new JLabel("Player One", JLabel.CENTER);
        playerOneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerOneLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        playerOnePanel.add(playerOneLabel);
        playerOnePanel.add(Box.createVerticalStrut(10));
        
        JPanel trainerPanelOne = createPlayerTrainer(1);
        trainerPanelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerOnePanel.add(trainerPanelOne);
        playerOnePanel.add(Box.createVerticalStrut(20));
        
        JPanel namePanelOne = new JPanel();
        namePanelOne.setLayout(new BoxLayout(namePanelOne, BoxLayout.Y_AXIS));
        namePanelOne.setOpaque(false);
        namePanelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabelOne = new JLabel("Enter name for Player 1");
        nameLabelOne.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanelOne.add(nameLabelOne);
        
        playerOneNameField = new JTextField(10);
        playerOneNameField.setFont(new Font("Arial", Font.BOLD, 20));
        playerOneNameField.setHorizontalAlignment(JTextField.CENTER);
        playerOneNameField.setMaximumSize(new Dimension(180, 40));
        playerOneNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56,56,56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        playerOneNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        namePanelOne.add(Box.createVerticalStrut(5));
        namePanelOne.add(playerOneNameField);
        playerOnePanel.add(namePanelOne);
        playerOnePanel.add(Box.createVerticalStrut(15));
        
        JPanel colorPanelOne = new JPanel();
        colorPanelOne.setOpaque(false);
        colorPanelOne.add(chooserColorPlayerOne);
        colorPanelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerOnePanel.add(colorPanelOne);
        
        JPanel playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
        playerTwoPanel.setOpaque(false);
        
        JLabel playerTwoLabel = new JLabel("Player Two", JLabel.CENTER);
        playerTwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerTwoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        playerTwoPanel.add(playerTwoLabel);
        playerTwoPanel.add(Box.createVerticalStrut(10));
        
        JPanel trainerPanelTwo = createPlayerTrainer(2);
        trainerPanelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerTwoPanel.add(trainerPanelTwo);
        playerTwoPanel.add(Box.createVerticalStrut(20));
        
        JPanel namePanelTwo = new JPanel();
        namePanelTwo.setLayout(new BoxLayout(namePanelTwo, BoxLayout.Y_AXIS));
        namePanelTwo.setOpaque(false);
        namePanelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabelTwo = new JLabel("Enter name for Player 2");
        nameLabelTwo.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanelTwo.add(nameLabelTwo);
        
        playerTwoNameField = new JTextField(10);
        playerTwoNameField.setFont(new Font("Arial", Font.BOLD, 20));
        playerTwoNameField.setHorizontalAlignment(JTextField.CENTER);
        playerTwoNameField.setMaximumSize(new Dimension(180, 40));
        playerTwoNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56,56,56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        playerTwoNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        namePanelTwo.add(Box.createVerticalStrut(5));
        namePanelTwo.add(playerTwoNameField);
        playerTwoPanel.add(namePanelTwo);
        playerTwoPanel.add(Box.createVerticalStrut(15));
        
        JPanel colorPanelTwo = new JPanel();
        colorPanelTwo.setOpaque(false);
        colorPanelTwo.add(chooserColorPlayerTwo);
        colorPanelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerTwoPanel.add(colorPanelTwo);
        
        chooseDifficulty.add(playerOnePanel);
        chooseDifficulty.add(playerTwoPanel);
        
        add(chooseDifficulty, BorderLayout.CENTER);
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}