package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class BattlePanel extends JPanel {
    private String backgroundImage = "battle";
    private String firstPokemon = "3";
    private String secondPokemon = "6";
    private JButton fightButton;
    private JButton inventoryButton;
    private JButton pokemonButton;
    private JButton runButton;
    private POOBkemonGUI po;
    private JPanel movesPanel;
    private JPanel battleOptionsPanel;
    private JButton backToOptionsBattle;

    private JPanel playerStatsPanel;
    private JPanel opponentStatsPanel;

    private CustomHealthBar playerHealthBar;
    private CustomHealthBar opponentHealthBar;
    private JLabel playerHealthLabel;
    private JLabel opponentHealthLabel;
    private JLabel playerNameLabel;
    private JLabel opponentNameLabel;

    private int xFirst, xSecond;
    private int yFirst, ySecond;

    private Font pokemonFont;

    public BattlePanel(POOBkemonGUI newPo) {
        po = newPo;
        backToOptionsBattle = new JButton("Back");
        po.styleButton(backToOptionsBattle);
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        try {
            pokemonFont = new Font("Monospaced", Font.BOLD, 14);
        } catch (Exception e) {
            pokemonFont = new Font("SansSerif", Font.BOLD, 14);
            e.printStackTrace();
        }
        
        prepareElements();
    }

    private void prepareElements() {
        calculatePokemonPositions();
        playerStatsPanel = createStatsPanel("VENASSUR", 100, 100, true);
        add(playerStatsPanel);

        opponentStatsPanel = createStatsPanel("CHARIZARD", 100, 120, false);
        add(opponentStatsPanel);

        battleOptionsPanel = new JPanel();
        battleOptionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        battleOptionsPanel.setOpaque(false);

        fightButton = new JButton("LUCHAR");
        inventoryButton = new JButton("MOCHILA");
        pokemonButton = new JButton("POKéMON");
        runButton = new JButton("HUIR");

        po.styleButton(fightButton);
        po.styleButton(inventoryButton);
        po.styleButton(pokemonButton);
        po.styleButton(runButton);

        battleOptionsPanel.add(fightButton);
        battleOptionsPanel.add(inventoryButton);
        battleOptionsPanel.add(pokemonButton);
        battleOptionsPanel.add(runButton);

        add(battleOptionsPanel);
    }

    private class CustomHealthBar extends JProgressBar {
        private boolean isPlayer;

        public CustomHealthBar(int min, int max, boolean isPlayer) {
            super(min, max);
            this.isPlayer = isPlayer;
            setUI(new PokemonHealthBarUI());
            setBorderPainted(false);
            setStringPainted(false);
            setOpaque(false);
        }

        private class PokemonHealthBarUI extends BasicProgressBarUI {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                
                int width = c.getWidth();
                int height = c.getHeight();
                
                g2d.setColor(new Color(40, 40, 40));
                g2d.fillRect(0, 0, width, height);
                
                int max = getModel().getMaximum();
                int min = getModel().getMinimum();
                int value = getModel().getValue();
                
                double progress = 0.0;
                if (max != min) {
                    progress = (double)(value - min) / (double)(max - min);
                }
                
                int fillWidth = (int)(width * progress);
            
                Color healthColor;
                
                if (value > max * 0.5) {
                    healthColor = new Color(88, 208, 120);
                } else if (value > max * 0.2) {
                    healthColor = new Color(248, 208, 48);
                } else {
                    healthColor = new Color(248, 88, 56);  
                }
                
                g2d.setColor(healthColor);
                g2d.fillRect(0, 0, fillWidth, height);
                
                g2d.dispose();
            }
        }
    }

    public void showMovesPanel() {
        calculatePokemonPositions();
        remove(battleOptionsPanel);

        movesPanel = new JPanel(new BorderLayout());
        movesPanel.setOpaque(false);
        movesPanel.setBounds(xFirst + 800, yFirst + 100, 380, 200);
        
        JPanel moveMessagePanel = new JPanel();
        moveMessagePanel.setLayout(new BorderLayout());
        moveMessagePanel.setBackground(new Color(248, 248, 248, 240));
        moveMessagePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(40, 40, 40), 3, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel moveMessageLabel = new JLabel("¿Qué movimiento debería usar?");
        moveMessageLabel.setFont(pokemonFont);
        moveMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        moveMessagePanel.add(moveMessageLabel, BorderLayout.CENTER);
        
        JPanel movesButtonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        movesButtonsPanel.setOpaque(false);

        String[] moves = {"PLACAJE", "GRUÑIDO", "ASCUAS", "GARRA METAL"};

        for (String move : moves) {
            JButton movementButton = new JButton(move);
            po.styleButton(movementButton);
            movementButton.setFont(pokemonFont);
            movesButtonsPanel.add(movementButton);
        }
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.setOpaque(false);
        backToOptionsBattle.setFont(pokemonFont);
        backPanel.add(backToOptionsBattle);
        
        movesPanel.add(moveMessagePanel, BorderLayout.NORTH);
        movesPanel.add(movesButtonsPanel, BorderLayout.CENTER);
        movesPanel.add(backPanel, BorderLayout.SOUTH);

        add(movesPanel);

        revalidate();
        repaint();
    }

    public void showBattleOptionsPanel() {
        remove(movesPanel);
        add(battleOptionsPanel);
        revalidate();
        repaint();
    }


    private JPanel createStatsPanel(String pokemonName, int level, int health, boolean isPlayer) {

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(null);
        statsPanel.setPreferredSize(new Dimension(300, 100));
        
        statsPanel.setOpaque(false);
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(null);
        innerPanel.setBounds(0, 0, 280, 80);
        innerPanel.setBackground(new Color(248, 248, 248, 220));
        innerPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(40, 40, 40), 3, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        JLabel nameLabel = new JLabel(pokemonName + " Nv." + level);
        nameLabel.setFont(pokemonFont);
        nameLabel.setBounds(20, 10, 200, 20);
        innerPanel.add(nameLabel);
        
        CustomHealthBar healthBar = new CustomHealthBar(0, 100, isPlayer);
        healthBar.setValue(health);
        healthBar.setBounds(60, 40, 180, 15);
        innerPanel.add(healthBar);
        
        JLabel psLabel = new JLabel("PS");
        psLabel.setFont(pokemonFont);
        psLabel.setBounds(20, 38, 30, 20);
        innerPanel.add(psLabel);
        
        JLabel healthLabel = new JLabel(health + "/" + 100);
        healthLabel.setFont(new Font(pokemonFont.getName(), Font.PLAIN, 12));
        healthLabel.setBounds(180, 55, 80, 20);
        healthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        innerPanel.add(healthLabel);
        
        statsPanel.add(innerPanel);
        
        if (isPlayer) {
            playerHealthBar = healthBar;
            playerHealthLabel = healthLabel;
            playerNameLabel = nameLabel;
        } else {
            opponentHealthBar = healthBar;
            opponentHealthLabel = healthLabel;
            opponentNameLabel = nameLabel;
        }
        
        return statsPanel;
    }

    public JButton getFighButton() {
        return fightButton;
    }

    public JButton getInventoryButton() {
        return inventoryButton;
    }

    public JButton getPokemonButton() {
        return pokemonButton;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JButton getBackOptions() {
        return backToOptionsBattle;
    }

    public void actualizarHealt(int health1, int health2) {
        playerHealthBar.setValue(health1);
        opponentHealthBar.setValue(health2);
        playerHealthLabel.setText(health1 + "/" + 100);
        opponentHealthLabel.setText(health2 + "/" + 100);
        
        repaint();
    }
    
    public void setPokemonNames(String playerPokemonName, String opponentPokemonName, int playerLevel, int opponentLevel) {
        playerNameLabel.setText(playerPokemonName + " Nv." + playerLevel);
        opponentNameLabel.setText(opponentPokemonName + " Nv." + opponentLevel);
    }

    public void setFirstPokemon(String pokemonImageName) {
        firstPokemon = pokemonImageName;
        repaint();
    }

    public void setSecondPokemon(String pokemonImageName) {
        secondPokemon = pokemonImageName;
        repaint();
    }

    private void calculatePokemonPositions() {
        int scaledWidth = getWidth() / 3;
        int scaledHeight = getHeight() / 3;

        xFirst = (getWidth() - scaledWidth) / 2 - scaledWidth + scaledWidth / 4;
        yFirst = (getHeight() - scaledHeight) / 2 + scaledHeight / 2 + scaledHeight / 3;

        xSecond = (getWidth() - scaledWidth) / 2 + scaledWidth / 2 + scaledWidth / 3;
        ySecond = (getHeight() - scaledHeight) / 2 - scaledHeight / 3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculatePokemonPositions();

        battleOptionsPanel.setBounds(xFirst + 800, yFirst+100, 180, 200);
        playerStatsPanel.setBounds(xFirst - 30, yFirst - 100, 300, 100);
        opponentStatsPanel.setBounds(xSecond - 30, ySecond - 80, 300, 100);

        battleOptionsPanel.revalidate();
        battleOptionsPanel.repaint();

        playerStatsPanel.revalidate();
        playerStatsPanel.repaint();

        opponentStatsPanel.revalidate();
        opponentStatsPanel.repaint();

        ImageIcon back = new ImageIcon(getClass().getResource("/resources/" + backgroundImage + ".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
        int scaledWidth = getWidth() / 3;
        int scaledHeight = getHeight() / 3;

        if (firstPokemon != null) {
            ImageIcon pokemonOne = new ImageIcon(getClass().getResource("/resources/battle/back/" + firstPokemon + ".PNG"));
            g.drawImage(pokemonOne.getImage(), xFirst, yFirst + 50, scaledWidth, scaledHeight, this);
        }

        if (secondPokemon != null) {
            ImageIcon pokemonTwo = new ImageIcon(getClass().getResource("/resources/battle/frente/" + secondPokemon + ".PNG"));
            g.drawImage(pokemonTwo.getImage(), xSecond - 15, ySecond + 50, scaledWidth, scaledHeight, this);
        }
    }
}