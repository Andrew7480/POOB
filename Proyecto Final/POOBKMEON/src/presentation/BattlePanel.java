package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class BattlePanel extends JPanel {
    private String backgroundImage = "battle";
    private String firstPokemon = "blastoise-pokemon";
    private String secondPokemon = "bom-dia";
    private JButton fightButton;
    private JButton inventoryButton;
    private JButton pokemonButton;
    private JButton runButton;
    private POOBkemonGUI po;

    private JProgressBar playerHealthBar;
    private JProgressBar opponentHealthBar;
    private JLabel playerHealthLabel;
    private JLabel opponentHealthLabel;

    public BattlePanel(POOBkemonGUI newPo){
        po = newPo;
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        JPanel playerStatsPanel = createStatsPanel("Player Pokémon", 100, 100);
        playerStatsPanel.setBounds(0, 0, 300, 100);
        add(playerStatsPanel);

        JPanel opponentStatsPanel = createStatsPanel("Opponent Pokémon", 100, 120);
        opponentStatsPanel.setBounds(0, 100, 300, 100);
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

        if (pokemonName.equals("Player Pokémon")) {
            playerHealthBar = healthBar;
            playerHealthLabel = healthLabel;
        } else if (pokemonName.equals("Opponent Pokémon")) {
            opponentHealthBar = healthBar;
            opponentHealthLabel = healthLabel;
        }
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

    public void actualizarHealt(int healt1, int healt2){
        playerHealthBar.setValue(healt1);
        opponentHealthBar.setValue(healt2);
    }

    public void actualizar(){
    }

    public void setFirstPokemon(String pokemonImageName) {
        firstPokemon = pokemonImageName;
        repaint(); 
    }
    
    public void setSecondPokemon(String pokemonImageName) {
        secondPokemon = pokemonImageName;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
        int scaledWidth = getWidth() / 3;
        int scaledHeight = getHeight() / 3;

        if (firstPokemon != null){
            ImageIcon pokemonOne = new ImageIcon(getClass().getResource("/resources/"+ firstPokemon+".GIF"));
            
            int x = (getWidth() - scaledWidth) / 2 - scaledWidth + scaledWidth/4;
            int y = (getHeight() - scaledHeight) / 2 + scaledHeight/2 +scaledHeight/3;
            g.drawImage(pokemonOne.getImage(), x, y, scaledWidth, scaledHeight, this);
        }

        if (secondPokemon != null){
            ImageIcon pokemonTwo = new ImageIcon(getClass().getResource("/resources/"+ secondPokemon+".GIF"));
            int x = (getWidth() - scaledWidth) / 2 + scaledWidth/2 + scaledWidth/3;
            int y = (getHeight() - scaledHeight) / 2 - scaledHeight/3 ;
            g.drawImage(pokemonTwo.getImage(), x, y, scaledWidth, scaledHeight, this);
        }

    }
}
