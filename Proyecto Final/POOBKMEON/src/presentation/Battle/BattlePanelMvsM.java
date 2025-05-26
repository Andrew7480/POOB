package presentation.Battle;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

import domain.LogPOOBKEMON;
import domain.PoobkemonException;
import presentation.*;
import presentation.helpers.*;

import javax.swing.*;
import java.awt.*;


public class BattlePanelMvsM extends JPanel {
    private POOBkemonGUI pooBkemonGUI;

    private String backgroundImage = "battle";
    private String affectVisualOpponnet;
    private String affectVisualActual;
    private String firstPokemon = "6";
    private String secondPokemon = "6";

    private JPanel movesPanel;
    private JPanel opciones;
    private JPanel battleOptionsPanel;
    private JPanel playerStatsPanel;
    private JPanel opponentStatsPanel;
    private JPanel panelInfo;

    private CustomHealthBar playerHealthBar;
    private CustomHealthBar opponentHealthBar;

    private JLabel playerHealthLabel;
    private JLabel opponentHealthLabel;
    private JLabel playerNameLabel;
    private JLabel opponentNameLabel;

    private int xFirst, xSecond;
    private int yFirst, ySecond;
    private Color actualColor;
    private int actualIndex;

    private JFileChooser fileChooser;
    private ArrayList<String> movMachine;

    private Font pokemonFont;
    protected CardLayout cardLayout;

    private JButton ticTac;
    private JButton save;
    private JButton open;
    private JButton runButton;


    public BattlePanelMvsM(POOBkemonGUI newPo) {
        pooBkemonGUI = newPo;        
        prepareElements();
        prepareActions();
    }

    public void inicializate(ArrayList<String> movs){

        movMachine = movs;

        String nameCurrent = pooBkemonGUI.domain.getCurrentPokemonName();
        int psCurrent = pooBkemonGUI.domain.getCurrentPokemonPs();
        int levelCurrent = pooBkemonGUI.domain.getCurrentPokemonLevel();
        int maxPs = pooBkemonGUI.domain.getcurrentMaxPs();
        playerStatsPanel = createStatsPanel(nameCurrent, levelCurrent ,psCurrent,maxPs, true);
        String nameOponent = pooBkemonGUI.domain.getOponentPokemonName();
        int psOponent = pooBkemonGUI.domain.getOponentPokemonPs();
        int levelOponent = pooBkemonGUI.domain.getOponentPokemonLevel();
        int maxPsOponent = pooBkemonGUI.domain.getOponentMaxPs();

        opponentStatsPanel = createStatsPanel(nameOponent,levelOponent,psOponent,maxPsOponent, false);
        playerStatsPanel.setBounds(xFirst - 30, yFirst - 100, 300, 100);
        opponentStatsPanel.setBounds(xSecond - 30, ySecond - 80, 300, 100);

        add(playerStatsPanel);
        add(opponentStatsPanel);
        actualizarColor();
        
    }

    private void prepareElements() {
        actualColor = new Color(100,100,100,100);
        
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        try {
            pokemonFont = new Font("Monospaced", Font.BOLD, 14);
        } catch (Exception e) {
            pokemonFont = new Font("SansSerif", Font.BOLD, 14);
            LogPOOBKEMON.record(e);
        }
        
        save = new JButton("SAVE GAME");
        open = new JButton("OPEN A GAME");
        pooBkemonGUI.styleButton(save);
        pooBkemonGUI.styleButton(open);
        ticTac = new JButton("TIC TAC");
        runButton = new JButton("HUIR");
        pooBkemonGUI.styleButton(ticTac);
        pooBkemonGUI.styleButton(runButton);
        

        calculatePokemonPositions();
        movMachine = new ArrayList<>();
        
        JPanel panelIz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIz.setOpaque(false);
        JPanel panelUp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUp.setOpaque(false);
        panelUp.add(open);
        panelUp.add(save);
        add(panelUp, BorderLayout.NORTH);

        panelInfo = new JPanel();
        panelInfo.setOpaque(false);
        
        panelInfo.setBorder(BorderFactory.createLineBorder(actualColor, 2));

        add(panelIz, BorderLayout.SOUTH);

        opciones = pooBkemonGUI.invisiblePanelWithoutOpacity();
        opciones.setOpaque(false);
        opciones.setPreferredSize(new Dimension(350, 150));
        
        cardLayout = new CardLayout();
        opciones.setLayout(cardLayout);

        panelIz.add(panelInfo);
        panelIz.add(opciones);

        battleOptionsPanel = new JPanel(new GridLayout(2,2,1,1));
        battleOptionsPanel.setOpaque(false);
        battleOptionsPanel.add(ticTac);
        battleOptionsPanel.add(runButton);

        opciones.add(battleOptionsPanel, "Opciones");
        
    }

    private void prepareActions(){
        ticTac.addActionListener(e ->{
            try{
                String randomMove = "";
                gameEnd();
                int oldIndex = pooBkemonGUI.domain.getOponentPokemonPokedexIndex();
                actualIndex = pooBkemonGUI.domain.getCurrentPokemonPokedexIndex();
                pooBkemonGUI.domain.movementPerformed(randomMove);
                if (!pooBkemonGUI.domain.isAliveOpponentPokemon() || oldIndex != pooBkemonGUI.domain.getOponentPokemonPokedexIndex()){
                    int newIndex = pooBkemonGUI.domain.getOponentPokemonPokedexIndex();
                    setSecondPokemon(Integer.toString(newIndex));
                }
                actualizar();
            }
            catch(PoobkemonException h){
                System.out.println(h.getMessage());
            }
            catch (Exception i) {
                LogPOOBKEMON.record(i);
            }
            });
        save.addActionListener(e -> {
            pooBkemonGUI.saveBattle();
            actualizar();
        });
        open.addActionListener(e -> {
            pooBkemonGUI.OpenBattle();
            actualizar();
        });
    }

    public JButton getRunButton() {
        return runButton;
    }

    public void gameEnd(){
        if (pooBkemonGUI.domain.GameIsOVer()){
            JOptionPane.showMessageDialog(this, "Ha ganado: "+ pooBkemonGUI.domain.getWinner(),"Se acabo!",JOptionPane.INFORMATION_MESSAGE);
            pooBkemonGUI.changePanel("inicio"); 
            pooBkemonGUI.resetBattles();
            pooBkemonGUI.domain.endBattle();
        }  
    }
    
    public void actualizar(){
        actualizarColor(); 
        actualizarBar();
        actualizarListaMovements();
        setSecondPokemon(Integer.toString(pooBkemonGUI.domain.getOponentPokemonPokedexIndex()));
        setFirstPokemon(Integer.toString(pooBkemonGUI.domain.getCurrentPokemonPokedexIndex()));
        gameEnd();
    }

    public void actualizarHealt(int health1,int health1Max, int health2,int health2Max) {
        playerHealthBar.setMaximum(health1Max);
        opponentHealthBar.setMaximum(health2Max);
        playerHealthBar.setValue(health1);
        opponentHealthBar.setValue(health2);
        playerHealthLabel.setText(health1 + "/" + health1Max);
        opponentHealthLabel.setText(health2 + "/" + health2Max);
        playerStatsPanel.repaint();
        opponentStatsPanel.repaint();
        repaint();
    }

    public void actualizarBar(){

        String pokemonName = pooBkemonGUI.domain.getOponentPokemonName();
        int health = pooBkemonGUI.domain.getOponentPokemonPs();
        int level = pooBkemonGUI.domain.getOponentPokemonLevel();
        int maxPs = pooBkemonGUI.domain.getOponentMaxPs();
        
        if (actualIndex != pooBkemonGUI.domain.getCurrentPokemonPokedexIndex()){
            setFirstPokemon(Integer.toString(pooBkemonGUI.domain.getCurrentPokemonPokedexIndex()));
        }

        String pokemonNameCurrent = pooBkemonGUI.domain.getCurrentPokemonName();
        int healthCurrent = pooBkemonGUI.domain.getCurrentPokemonPs();
        int levelCurrent = pooBkemonGUI.domain.getCurrentPokemonLevel();
        int maxPsCurrent = pooBkemonGUI.domain.getcurrentMaxPs();
        System.out.println("vida afectada a mi pokemon : "+healthCurrent);

        playerHealthBar.setValue(healthCurrent);
        opponentHealthBar.setValue(health);

        playerHealthLabel.setText(healthCurrent + "/" + maxPsCurrent);
        opponentHealthLabel.setText(health + "/" + maxPs);

        playerNameLabel.setText(pokemonNameCurrent + " Nv." + levelCurrent);
        opponentNameLabel.setText(pokemonName + " Nv. "+ level);

        actualizarHealt(healthCurrent, maxPsCurrent, health, maxPs);

        playerStatsPanel.repaint();
        opponentStatsPanel.repaint();
        gameEnd();
    }

    public void actualizarListaMovements(){
        movMachine = pooBkemonGUI.domain.getMovementsStringCurrent();
    }
    public void actualizarColor(){
        actualColor = pooBkemonGUI.domain.getCurrentColor();
        panelInfo.setBorder(BorderFactory.createLineBorder(actualColor, 2));
    }

    public JPanel createStatsPanel(String pokemonName, int level, int health,int maxHealth, boolean isPlayer) {

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
        
        CustomHealthBar healthBar = new CustomHealthBar(0, maxHealth);
        healthBar.setValue(health);
        healthBar.setBounds(60, 40, 180, 15);
        innerPanel.add(healthBar);
        
        JLabel psLabel = new JLabel("PS");
        psLabel.setFont(pokemonFont);
        psLabel.setBounds(20, 38, 30, 20);
        innerPanel.add(psLabel);
        
        JLabel healthLabel = new JLabel(health + "/" +maxHealth);
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

    public void setPokemonNames(String playerPokemonName, String opponentPokemonName, int playerLevel, int opponentLevel) {
        playerNameLabel.setText(playerPokemonName + " Nv." + playerLevel);
        opponentNameLabel.setText(opponentPokemonName + " Nv." + opponentLevel);
    }

    public void setFirstPokemon(String pokemonImageName) {
        firstPokemon = pokemonImageName;
        if(pooBkemonGUI.domain.currentIsAffected()){affectVisualActual = "efecto1";}
        else{affectVisualActual = null;}
        repaint();
    }

    public void setSecondPokemon(String pokemonImageName) {
        secondPokemon = pokemonImageName;
        if(pooBkemonGUI.domain.opponentIsAffected()){affectVisualOpponnet = "efecto1";}
        else{affectVisualOpponnet = null;}
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
        playerStatsPanel.setBounds(xFirst - 30, yFirst - 100, 300, 100);
        opponentStatsPanel.setBounds(xSecond - 30, ySecond - 80, 300, 100);


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

        if (affectVisualOpponnet != null) {
            ImageIcon effect = new ImageIcon(getClass().getResource("/resources/" + affectVisualOpponnet + ".GIF"));
            g.drawImage(effect.getImage(), xSecond - 15, ySecond + 50, scaledWidth, scaledHeight, this);
        }
        if (affectVisualActual != null){
            ImageIcon effect = new ImageIcon(getClass().getResource("/resources/" + affectVisualActual + ".GIF"));
            g.drawImage(effect.getImage(), xFirst, yFirst + 50, scaledWidth, scaledHeight, this);
        }
    }
}
