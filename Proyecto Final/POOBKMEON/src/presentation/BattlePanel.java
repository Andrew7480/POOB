package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class BattlePanel extends JPanel {
    private static final String backgroundImage = "battleScente";
    private String firstPokemon = "blastoise-pokemon";
    private String secondPokemon = "bom-dia";
    private JButton fightButton;
    private JButton inventoryButton;
    private JButton pokemonButton;
    private JButton runButton;
    private POOBkemonGUI po;

    public BattlePanel(POOBkemonGUI newPo){
        po = newPo;
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        JPanel playerStatsPanel = createStatsPanel("Player Pokémon", 100, 100);
        playerStatsPanel.setBounds(50, 450, 300, 100);
        add(playerStatsPanel);

        JPanel opponentStatsPanel = createStatsPanel("Opponent Pokémon", 100, 120);
        opponentStatsPanel.setBounds(450, 10, 300, 100);
        add(opponentStatsPanel);

        JPanel battleOptionsPanel = new JPanel();
        battleOptionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        battleOptionsPanel.setOpaque(false);
        battleOptionsPanel.setBounds(400, 450, 300, 100);

        fightButton = new JButton("Fight");
        inventoryButton = new JButton("Inventory");
        pokemonButton = new JButton("Pokémon");
        runButton = new JButton("Run");

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


    private JPanel createStatsPanel(String pokemonName, int level, int health) {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1));
        statsPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(pokemonName + " Lv " + level);
        JProgressBar healthBar = new JProgressBar(0, 100);
        healthBar.setValue(health);
        healthBar.setForeground(Color.GREEN);

        JLabel healthLabel = new JLabel("HP: " + health + "/100");

        statsPanel.add(nameLabel);
        statsPanel.add(healthBar);
        statsPanel.add(healthLabel);

        return statsPanel;
    }

    public JButton getFighButton(){
        return fightButton;
    }
    public JButton getInventoryButton(){
        return inventoryButton;
    }
    public JButton getPokemonButton(){
        return pokemonButton;
    }
    public JButton getRunButton(){
        return runButton;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);

        if (firstPokemon != null){
            ImageIcon pokemonOne = new ImageIcon(getClass().getResource("/resources/"+ firstPokemon+".GIF"));
            g.drawImage(pokemonOne.getImage(), 100, 300, 200, 200, this);
        }

        if (secondPokemon != null){
            ImageIcon pokemonTwo = new ImageIcon(getClass().getResource("/resources/"+ secondPokemon+".GIF"));
            g.drawImage(pokemonTwo.getImage(), 500, 100, 200, 200, this);
        }

    }
}
