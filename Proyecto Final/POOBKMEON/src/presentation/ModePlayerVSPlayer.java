package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModePlayerVSPlayer extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresarNormal;
    private JButton btnRegresarSurvival;
    private JButton continuar;
    private JButton chooserColorPlayer1;
    private JButton chooserColorPlayer2;
    private POOBkemonGUI po;

    private JColorChooser colorChooser;
    private Color colorChosed;
    private Color colorChosedPlayer1;
    private Color colorChosedPlayer2;

    private JPanel chooseDifficulty;
    private JPanel nameInputPanel;


    private JButton player1;
    private JButton player2;
    private String[] paths;
    private int indexPlayerOne = 0;
    private int indexPlayerTwo = 0;

    private JTextField player1NameField;
    private JTextField player2NameField;
    private JLabel player1nameLabel;
    private JLabel player2nameLabel;
    private String player1Name ="";
    private String player2Name ="";

    public ModePlayerVSPlayer(POOBkemonGUI newPo, boolean normal){
        po = newPo;
        prepareElements(normal);
        prepareActions();
    }
    private void prepareElements(boolean normal){
        setLayout(new BorderLayout());
        colorChooser = new JColorChooser();
        paths = new String[]{"/resources/trainers/Trainer1.png","/resources/trainers/Trainer2.png","/resources/trainers/Trainer3.png"};
        setupNameInputPanel();
        prepareButtons(normal);
    }

    private void prepareButtons(boolean normal){
        JButton regresar = new JButton("Back");
        continuar = new JButton("CONTINUE");
        chooserColorPlayer1 = new JButton("Choose Color");
        chooserColorPlayer2 = new JButton("Choose Color");
        po.styleButton(continuar);
        po.styleButton(regresar);
        po.styleButton(chooserColorPlayer1);
        po.styleButton(chooserColorPlayer2);
        if(normal){
            btnRegresarNormal = regresar;
        }
        else{
            btnRegresarSurvival = regresar;
        }
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(regresar);
        buttonPanel.add(continuar);
        add(buttonPanel,BorderLayout.SOUTH);

        playerPanel();
    }

    private void prepareActions(){
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    player1Name = player1NameField.getText().trim();
                    player2Name = player2NameField.getText().trim();

                    if(player1Name.isEmpty() || player2Name.isEmpty()){
                        JOptionPane.showMessageDialog(ModePlayerVSPlayer.this, "Ambos nombres deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(colorChosedPlayer1 == null || colorChosedPlayer2 == null){
                        JOptionPane.showMessageDialog(ModePlayerVSPlayer.this,"Ambos colores deben llenarse","Error",JOptionPane.ERROR_MESSAGE);
                    }

                    //po.trainerEscogido = player1Name;

                    po.cardLayout.show(po.panelContenedor, "chooser");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ModePlayerVSPlayer.this, ex.getMessage());
                }
            }
        });

        btnRegresarNormal.addActionListener(e -> {
            po.changePanel("modos de juego");
        });

        chooserColorPlayer1.addActionListener(e -> chooserColorPlayer1.setBackground(colorChosed));
        chooserColorPlayer1.addActionListener(e -> changeColor());
        chooserColorPlayer2.addActionListener(e -> chooserColorPlayer2.setBackground(colorChosed));
        chooserColorPlayer2.addActionListener(e -> changeColor());
    }

    private void setupNameInputPanel(){
        nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new GridLayout(1,2));
        nameInputPanel.setOpaque(false);

        JPanel player1NamePanel = createPokemonStylePanel("Player 1", true);
        JPanel player2NamePanel = createPokemonStylePanel("Player 2", true);

        nameInputPanel.add(player1NamePanel);
        nameInputPanel.add(player2NamePanel);
    }

    private JPanel createPokemonStylePanel(String playerTitle, boolean isPlayer1){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setPreferredSize(new Dimension(100, 100));
        dialogPanel.setBorder(BorderFactory.createLineBorder(new Color(56,56,56),3));

        JLabel nameLabel = new JLabel("Enter name for " + playerTitle);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField nameField = new JTextField(10);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setMaximumSize(new Dimension(180, 40));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56,56,56),2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        if (isPlayer1){
            player1NameField = nameField;
            player1nameLabel = nameLabel;
        } else{
            player2NameField = nameField;
            player2nameLabel = nameLabel;
        }
        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.setOpaque(false);
        nameFieldPanel.add(nameField);

        dialogPanel.add(nameLabel, BorderLayout.NORTH);
        dialogPanel.add(nameFieldPanel, BorderLayout.CENTER);

        panel.add(Box.createVerticalStrut(10));
        panel.add(dialogPanel);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }

    private void playerPanel(){
        chooseDifficulty = new JPanel();
        chooseDifficulty.setLayout(new GridLayout(1, 2));
        chooseDifficulty.setOpaque(false);

        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BorderLayout());
        player1Panel.setOpaque(false);

        JPanel player2panel = new JPanel();
        player2panel.setLayout(new BorderLayout());
        player2panel.setOpaque(false);

        JPanel trainerPlayerOneSelector = createPlayerTrainer(1);
        JPanel trainerPlayerTwoSelector = createPlayerTrainer(2);

        JPanel colorChooserPlayer1 = new JPanel();
        colorChooserPlayer1.setOpaque(false);
        colorChooserPlayer1.add(chooserColorPlayer1);

        JPanel colorChooserPlayer2 = new JPanel();
        colorChooserPlayer2.setOpaque(false);
        colorChooserPlayer2.add(chooserColorPlayer2);

        player1Panel.add(new JLabel("Player One", JLabel.CENTER), BorderLayout.NORTH);
        player1Panel.add(trainerPlayerOneSelector, BorderLayout.CENTER);
        player1Panel.add(colorChooserPlayer1, BorderLayout.SOUTH);

        player2panel.add(new JLabel("Player Two", JLabel.CENTER), BorderLayout.NORTH);
        player2panel.add(trainerPlayerTwoSelector, BorderLayout.CENTER);
        player2panel.add(colorChooserPlayer2, BorderLayout.SOUTH);

        chooseDifficulty.add(nameInputPanel);

        JPanel trainerSelectionPanel = new JPanel(new GridLayout(1,2));
        trainerSelectionPanel.setOpaque(false);
        trainerSelectionPanel.add(player1Panel);
        trainerSelectionPanel.add(player2panel);
        chooseDifficulty.add(trainerSelectionPanel);

        add(chooseDifficulty, BorderLayout.CENTER);
    }

    private JPanel createPlayerTrainer(int trainerNum) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JButton trainerButton;
        if (trainerNum == 1) {
            player1 = createImageButton("Trainer1", paths[0]);
            trainerButton = player1;
            trainerButton.addActionListener(e -> cambiarTrainer(1));
        } else {
            player2 = createImageButton("Trainer2", paths[0]);
            trainerButton = player2;
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
                player1.setIcon(new ImageIcon(scaledImage));
                player1.setToolTipText("Trainer: " + paths[indexPlayerOne]);
            } catch (Exception e) {
                player1.setText("No imagen??");
                player1.setIcon(null);
            }
        } else {
            indexPlayerTwo = (indexPlayerTwo + 1) % paths.length;
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPlayerTwo]));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                player2.setIcon(new ImageIcon(scaledImage));
                player2.setToolTipText("Trainer: " + paths[indexPlayerTwo]);
            } catch (Exception e) {
                player2.setText("No imagen??");
                player2.setIcon(null);
            }
        }
    }


    private JButton createImageButton(String name, String imagePath) {
        int width = 70, height = 70;
        Dimension smallSize = new Dimension(50, 30);
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
    public void changeColor(){
        Color choice = colorChooser.showDialog(this,"Seleccion tu color. ",Color.BLUE);
        setColor(choice);
    }
    public void setColor(Color choicePlayer){
        colorChosed = choicePlayer;
    }
    public Color getColor(){
        return colorChosed;
    }

    public JButton getBtnRegresarNormal(){
        return btnRegresarNormal;
    }
    public JButton getButtonContinuar(){
        return continuar;
    }
    public JButton getButtonRegresarSurvival(){
        return btnRegresarSurvival;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
