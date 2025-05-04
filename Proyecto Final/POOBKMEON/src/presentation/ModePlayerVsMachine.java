package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ModePlayerVsMachine extends JPanel {
    private String backgroundImage = "fondoAnimado2";
    private JButton btnRegresar;
    private JButton chooserColor;
    private JButton nextButton;
    private JColorChooser colorChooser;
    private POOBkemonGUI po;
    private Color colorChosed;

    private JPanel nameInputPanel;
    private JTextField playerNameField;
    private JLabel nameLabel;
    private JButton confirmNameButton;
    private String playerName = "";
    private boolean confirm = false;


    public ModePlayerVsMachine(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        btnRegresar = new JButton("BACK");
        chooserColor = new JButton("CHOOSE COLOR PLAYER");
        nextButton = new JButton("CONTINUE");
        colorChooser = new JColorChooser();
        po.styleButton(btnRegresar);
        po.styleButton(nextButton);
        po.styleButton(chooserColor);

        setupNameInputPanel();

        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
        buttonPanel.add(chooserColor);
        buttonPanel.add(nextButton);
        buttonPanel.add(btnRegresar);
        add(buttonPanel,BorderLayout.SOUTH);

        add(nameInputPanel, BorderLayout.CENTER);

        confirmNameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                confirmPlayerName();
            }
        });
    }

    private void setupNameInputPanel(){
        nameInputPanel = new JPanel();
        nameInputPanel.setLayout(new BoxLayout(nameInputPanel, BoxLayout.Y_AXIS));
        nameInputPanel.setOpaque(false);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JPanel namePanel = createPokemonStylePanel();
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(namePanel);
        nameInputPanel.add(centerPanel);
    }

    private JPanel createPokemonStylePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setPreferredSize(new Dimension(400, 150));
        dialogPanel.setBackground(new Color(248, 248, 216));
        dialogPanel.setBorder(BorderFactory.createLineBorder(new Color(56, 56, 56), 3));
        
        nameLabel = new JLabel("¿Whats your name?");
        nameLabel.setFont(new Font("Times new Roman", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        playerNameField = new JTextField(10);
        playerNameField.setFont(new Font("Times new Roman", Font.BOLD, 18));
        playerNameField.setHorizontalAlignment(JTextField.CENTER);
        playerNameField.setMaximumSize(new Dimension(300, 40));
        playerNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56, 56, 56), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        confirmNameButton = new JButton("OK");
        po.styleButton(confirmNameButton);
        confirmNameButton.setPreferredSize(new Dimension(100, 40));
        confirmNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.setOpaque(false);
        nameFieldPanel.add(playerNameField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(confirmNameButton);
        
        dialogPanel.add(nameLabel, BorderLayout.NORTH);
        dialogPanel.add(nameFieldPanel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(Box.createVerticalStrut(50));
        panel.add(dialogPanel);
        panel.add(Box.createVerticalStrut(50));
        
        return panel;
    }

    private void confirmPlayerName(){
        playerName = playerNameField.getText().trim();
        if(playerName.isEmpty()){
            JOptionPane.showMessageDialog(this, "Invalid name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        confirm = true;
        nameLabel.setText("Hello " + playerName + "!");
        playerNameField.setEnabled(false);
        confirmNameButton.setEnabled(false);
    }

    public JButton getButtonRegresar(){
        return btnRegresar;
    }
    public JButton getChoserColorNext(){
        return chooserColor;
    }
    public JButton getnexButton(){
        return nextButton;
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

    public String getPlayerName(){
        return playerName;
    }

    public boolean isNameConfirmed(){
        return confirm;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
