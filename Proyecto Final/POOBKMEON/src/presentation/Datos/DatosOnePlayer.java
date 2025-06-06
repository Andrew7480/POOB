package presentation.Datos;
import javax.swing.*;

import domain.PoobkemonException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVsMachine;


public class DatosOnePlayer extends JPanel{
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresar;
    private JButton nextButton;
    private JButton chooserColor;
    private JButton resetButton;
    private JButton buttonTrainer;
    private POOBkemonGUI pooBkemonGUI;
    private ModePlayerVsMachine gameMode;

    private Color colorChosed;

    private JPanel centerPanel;

    private String[] paths;

    private JPanel nameInputPanel;
    private JPanel panelButtons;
    private JTextField playerNameField;
    private JLabel nameLabel;
    
    private String playerName = "";

    private Color choiceOne;

    private ArrayList<String> gamemodeChoosen;
    private TreeMap<String,String> gameModes;
    private ArrayList<JButton> buttons;
    private int indexPath=0;

    

    public DatosOnePlayer(POOBkemonGUI newPo, ModePlayerVsMachine father){
        gameMode = father;
        pooBkemonGUI = newPo;
        chooserColor = new JButton("Choose Color");
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        setLayout(new BorderLayout());
        btnRegresar = new JButton("BACK");
        chooserColor = new JButton("CHOOSE COLOR PLAYER");
        nextButton = new JButton("CONTINUE");
        resetButton = new JButton("RESET");
        paths = new String[]{"/resources/trainers/Trainer1.png","/resources/trainers/Trainer2.png","/resources/trainers/Trainer3.png","/resources/trainers/Trainer4.png",
        "/resources/trainers/Trainer5.png","/resources/trainers/Trainer6.png","/resources/trainers/Trainer7.png","/resources/trainers/Trainer8.png"};
        gameModes = new TreeMap<>(){{
            put("Defensive", "/resources/Defensive.jpeg");
            put("Expert", "/resources/Expert.jpeg");
            put("Changing", "/resources/Changing.jpeg");
            put("Attacking", "/resources/Attacking.jpeg");
        }};

        buttons = new ArrayList<>();
        gamemodeChoosen= new ArrayList<>();
        
        pooBkemonGUI.styleButton(nextButton);
        pooBkemonGUI.styleButton(btnRegresar);
        pooBkemonGUI.styleButtonchooser(chooserColor);
        pooBkemonGUI.styleButton(resetButton);
        chooserColor.setPreferredSize(new Dimension(200, 40));
        chooserColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        setupNameInputPanel();
        
        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setOpaque(false);
        upPanel.add(new JLabel(" "),BorderLayout.CENTER);
        add(upPanel,BorderLayout.NORTH);
         
        JPanel buttonPanel = pooBkemonGUI.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        buttonPanel.add(btnRegresar);
        buttonPanel.add(resetButton);
        buttonPanel.add(nextButton);
        
        add(buttonPanel,BorderLayout.SOUTH);

        add(nameInputPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);

        JPanel buttonTrainerPanel = new JPanel(new GridLayout(1,1,0,0));
        buttonTrainerPanel.setOpaque(false);
        buttonTrainer = createImageButton("Trainer1", paths[0]);
        buttonTrainerPanel.add(buttonTrainer);

        JPanel colorChooserPanel = new JPanel();
        colorChooserPanel.setOpaque(false);
        colorChooserPanel.add(chooserColor);


        rightPanel.add(colorChooserPanel);
        rightPanel.add(buttonTrainerPanel);
        rightPanel.add(Box.createVerticalStrut(10));


        add(rightPanel,BorderLayout.EAST); 

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(50, 0));
        add(leftPanel,BorderLayout.WEST); 
    }
    private void prepareActions(){

        btnRegresar.addActionListener(e -> {
            pooBkemonGUI.changePanel("normal");
        });

        chooserColor.addActionListener(e -> {
            choiceOne = JColorChooser.showDialog(this, "Selecciona tu color", Color.BLUE);
            if (choiceOne != null) {
                colorChosed = choiceOne;
                chooserColor.setBackground(colorChosed);
                Color borderColor = colorChosed.darker();
                chooserColor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
            }
        });

        nextButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                confirmPlayerInfo();
                
            }
        });

        resetButton.addActionListener(e ->
        reset());

        buttonTrainer.addActionListener(e -> 
            cambiarTrainer()
        );
    }
    private void cambiarTrainer(){
        indexPath = (indexPath + 1) % paths.length;
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPath]));
            Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            buttonTrainer.setIcon(new ImageIcon(scaledImage));
            buttonTrainer.setToolTipText("Trainer: " + paths[indexPath]);
        } catch (Exception e) {
            buttonTrainer.setText("No imagen??");
            buttonTrainer.setIcon(null);
        }
    }
    private void setupNameInputPanel(){
        nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new BorderLayout());
        nameInputPanel.setOpaque(false);

        JPanel aux = new JPanel(new GridBagLayout());
        aux.setOpaque(false);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel namePanel = createPokemonStylePanel();
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelButtons= new JPanel(new GridLayout(2,2));
        panelButtons.setOpaque(false);
        createButtons();

        centerPanel.add(panelButtons);
        centerPanel.add(Box.createVerticalStrut(20));

        aux.add(namePanel);
        centerPanel.add(aux);
        centerPanel.add(Box.createVerticalStrut(20));

        nameInputPanel.add(centerPanel, BorderLayout.CENTER);
        
    }

    private JPanel createPokemonStylePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setPreferredSize(new Dimension(200, 100));
        dialogPanel.setBorder(BorderFactory.createLineBorder(new Color(56, 56, 56), 3));
        
        nameLabel = new JLabel("¿Whats your name?");
        nameLabel.setFont(new Font("Times new Roman", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        playerNameField = new JTextField(10);
        playerNameField.setFont(new Font("Times new Roman", Font.BOLD, 18));
        playerNameField.setHorizontalAlignment(JTextField.CENTER);
        playerNameField.setMaximumSize(new Dimension(200, 40));
        playerNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56, 56, 56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        
        
        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.setOpaque(false);
        nameFieldPanel.add(playerNameField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        
        dialogPanel.add(nameLabel, BorderLayout.NORTH);
        dialogPanel.add(nameFieldPanel, BorderLayout.SOUTH);
        dialogPanel.add(buttonPanel, BorderLayout.CENTER);
        
        panel.add(Box.createVerticalStrut(50));
        panel.add(dialogPanel);
        panel.add(Box.createVerticalStrut(50));
        
        return panel;
    }

    private void confirmPlayerInfo(){
        playerName = playerNameField.getText().trim();
        if(playerName.isEmpty()){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un color y un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        nameLabel.setText("Hello " + playerName + "!");
        playerNameField.setEnabled(false);
        if (colorChosed == null) {
            JOptionPane.showMessageDialog(this, "Select a color", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (gamemodeChoosen.size()>1 || gamemodeChoosen.size() == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona solo un tipo de juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            gameMode.nameTrainer = pooBkemonGUI.domain.isTrainerIsed(playerName);
            gameMode.trainerEscogidoMachine = gamemodeChoosen.get(0);
            gameMode.inventory.inicializate(choiceOne);
            
            gameMode.changePanel("Inventory");
        }

        catch(PoobkemonException e){ 
            JOptionPane.showMessageDialog(this, "Nombre no disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public Color getColor(){
        return colorChosed;
    }

    public String getPlayerName(){
        return playerName;
    }
    public void reset(){
        System.out.println("resetea todo de la informacion ususario.");
        colorChosed = null;
        playerName = "";
        playerNameField.setText(""); 
        playerNameField.setEnabled(true); 
        nameLabel.setText("¿Whats your name?");
        chooserColor.setBackground(new Color(70, 130, 180));
        pooBkemonGUI.styleButtonchooser(chooserColor);
        revalidate();
        repaint();
    }


    private JButton createImageButton(String name,String imagePath) {
        int x=1, y=1;
        int width=70, height =70;
        Dimension smallSize = new Dimension(50, 30); 
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            
            if (imagePath.toLowerCase().endsWith(".gif")){
                button.setIcon(icon);
                button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            } 
            else {
                Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            }
        }catch (Exception e) {
            button.setText("No imagen, intenta de nuevo.");
        }
        button.setPreferredSize(smallSize);
        button.setMinimumSize(smallSize);
        button.setMaximumSize(smallSize); 
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setToolTipText(name);
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return button;
    }
    public void createButtons() {
        for (Map.Entry<String,String> entry: gameModes.entrySet()) {
            JButton button = createImageButton(entry.getKey(), entry.getValue());
            pooBkemonGUI.styleButton(button);
            buttons.add(button);
            button.addActionListener(e -> 
            selectionGameMode(button)
            );
            panelButtons.add(button);
            }
    }
    
    private void selectionGameMode(JButton button){
        for (JButton b : buttons){
            b.setBackground(null);
            b.setOpaque(false);
        }
        gamemodeChoosen.clear();
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        gamemodeChoosen.add(button.getToolTipText());
        System.out.println(gamemodeChoosen.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
    
    

}
