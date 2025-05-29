package presentation.Datos;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.*;
import presentation.ModesOfGame.ModeMachineVsMachine;
import presentation.Selection.SelectionMovementsPanel;

public class DatosMachine extends JPanel{
    private String backgroundImage = "PZ4ODE";
    private POOBkemonGUI po;
    private JButton btnRegresar;
    private JButton continueButton;
    private TreeMap<String,String> gameModes;
    private ArrayList<String> gameModeChoosenOne;
    private ArrayList<String> gameModeChoosenTwo;
    private ArrayList<JButton> buttonsOne;
    private ArrayList<JButton> buttonsTwo;
    private JButton buttonTrainerOne;
    private JButton buttonTrainerTwo;
    public String machineTrainerFirst;
    public String machineTrainerSecond;
    private String[] paths;
    private int indexPathOne = 0;
    private int indexPathTwo = 0;
    private JPanel chooseDifficulty;
    private ModeMachineVsMachine gameMode;

    private SelectionMovementsPanel chosenOne;
    private SelectionMovementsPanel chosenTwo;


    public DatosMachine(POOBkemonGUI newPo, ModeMachineVsMachine father){
        gameMode = father;
        po = newPo;
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        setLayout(new BorderLayout());
        gameModes = new TreeMap<>(){{
            put("Defensive", "/resources/Defensive.jpeg");
            put("Expert", "/resources/Expert.jpeg");
            put("Changing", "/resources/Changing.jpeg");
            put("Attacking", "/resources/Attacking.jpeg");
        }};
        paths = new String[]{"/resources/trainers/Trainer1.png","/resources/trainers/Trainer2.png","/resources/trainers/Trainer3.png","/resources/trainers/Trainer4.png",
        "/resources/trainers/Trainer5.png","/resources/trainers/Trainer6.png","/resources/trainers/Trainer7.png","/resources/trainers/Trainer8.png"};
        gameModeChoosenOne = new ArrayList<>();
        gameModeChoosenTwo = new ArrayList<>();
        buttonsOne = new ArrayList<>();
        buttonsTwo = new ArrayList<>();

        continueButton = new JButton("Continue");
        po.styleButton(continueButton);
        chosenOne = new SelectionMovementsPanel(po);
        chosenTwo = new SelectionMovementsPanel(po);
        prepareButtons();
        chooseDifficulty();
    }

   private void prepareButtons(){
        btnRegresar = new JButton("BACK");
        po.styleButton(btnRegresar);

        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(btnRegresar);
        buttonPanel.add(continueButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }

    private void prepareActions(){
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmMachinesInfo();
            }
        });
        btnRegresar.addActionListener(e -> {
            po.changePanel("normal");
        });
    }

    private void confirmMachinesInfo(){
        if (gameModeChoosenOne.size() != 1 || gameModeChoosenTwo.size() != 1) {
            JOptionPane.showMessageDialog(DatosMachine.this,
                            "Error",
                            "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            machineTrainerFirst = gameModeChoosenOne.get(0);
            machineTrainerSecond = gameModeChoosenTwo.get(0);
            chosenOne.infoSelectedPokemons(po.domain.getPokemonAlives(machineTrainerFirst));
            chosenTwo.infoSelectedPokemons(po.domain.getPokemonAlives(machineTrainerSecond));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(DatosMachine.this, ex.getMessage());
        }
        gameMode.selecionFinal.inicializar();
        gameMode.changePanel("seleccion machines");
    }

    private void chooseDifficulty(){
        chooseDifficulty = new JPanel();
        chooseDifficulty.setLayout(new GridLayout(1, 2));
        chooseDifficulty.setOpaque(false);

        JPanel trainerOnePanel = new JPanel();
        trainerOnePanel.setLayout(new BorderLayout());
        trainerOnePanel.setOpaque(false);

        JPanel trainerTwoPanel = new JPanel();
        trainerTwoPanel.setLayout(new BorderLayout());
        trainerTwoPanel.setOpaque(false);

        JPanel trainerSelectorOne = createTrainerSelector(1);
        JPanel trainerSelectorTwo = createTrainerSelector(2);

        JPanel gameModePanelOne = createGameModePanel(1);
        JPanel gameModePanelTwo = createGameModePanel(2);

        trainerOnePanel.add(new JLabel("Machine Trainer 1", JLabel.CENTER), BorderLayout.NORTH);
        trainerOnePanel.add(trainerSelectorOne, BorderLayout.CENTER);
        trainerOnePanel.add(gameModePanelOne, BorderLayout.SOUTH);

        trainerTwoPanel.add(new JLabel("Machine Trainer 2", JLabel.CENTER), BorderLayout.NORTH);
        trainerTwoPanel.add(trainerSelectorTwo, BorderLayout.CENTER);
        trainerTwoPanel.add(gameModePanelTwo, BorderLayout.SOUTH);

        chooseDifficulty.add(trainerOnePanel);
        chooseDifficulty.add(trainerTwoPanel);

        add(chooseDifficulty, BorderLayout.CENTER);
    }

    private JPanel createTrainerSelector(int trainerNum) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JButton trainerButton;
        if (trainerNum == 1) {
            buttonTrainerOne = createImageButton("Trainer1", paths[0]);
            trainerButton = buttonTrainerOne;
            trainerButton.addActionListener(e -> cambiarTrainer(1));
        } else {
            buttonTrainerTwo = createImageButton("Trainer2", paths[0]);
            trainerButton = buttonTrainerTwo;
            trainerButton.addActionListener(e -> cambiarTrainer(2));
        }

        panel.add(trainerButton, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGameModePanel(int trainerNum) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("Select Game Mode"));

        for (Map.Entry<String, String> entry: gameModes.entrySet()) {
            JButton button = createImageButton(entry.getKey(), entry.getValue());
            po.styleButton(button);

            if (trainerNum == 1) {
                buttonsOne.add(button);
                button.addActionListener(e -> selectionGameMode(button, 1));
            } else {
                buttonsTwo.add(button);
                button.addActionListener(e -> selectionGameMode(button, 2));
            }

            panel.add(button);
        }

        return panel;
    }

    private void cambiarTrainer(int trainerNum) {
        if (trainerNum == 1) {
            indexPathOne = (indexPathOne + 1) % paths.length;
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPathOne]));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                buttonTrainerOne.setIcon(new ImageIcon(scaledImage));
                buttonTrainerOne.setToolTipText("Trainer: " + paths[indexPathOne]);
            } catch (Exception e) {
                buttonTrainerOne.setText("No imagen??");
                buttonTrainerOne.setIcon(null);
            }
        } else {
            indexPathTwo = (indexPathTwo + 1) % paths.length;
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paths[indexPathTwo]));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                buttonTrainerTwo.setIcon(new ImageIcon(scaledImage));
                buttonTrainerTwo.setToolTipText("Trainer: " + paths[indexPathTwo]);
            } catch (Exception e) {
                buttonTrainerTwo.setText("No imagen??");
                buttonTrainerTwo.setIcon(null);
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

    private void selectionGameMode(JButton button, int trainerNum) {
        ArrayList<String> gameModeChoosen = (trainerNum == 1) ? gameModeChoosenOne : gameModeChoosenTwo;
        ArrayList<JButton> buttons = (trainerNum == 1) ? buttonsOne : buttonsTwo;
        for (JButton b : buttons) {
            b.setBackground(null);
            b.setOpaque(false);
        }
        gameModeChoosen.clear();
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        gameModeChoosen.add(button.getToolTipText());
    }

    public JButton getBtnRegresar(){
        return btnRegresar;
    }

    public JButton getContinueButton() {
        return continueButton;
    }
    public void reset(){
        chosenOne.reset();
        chosenTwo.reset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }


}