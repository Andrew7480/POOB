package presentation.Battle;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import domain.LogPOOBKEMON;
import domain.PoobkemonException;
import presentation.*;
import presentation.helpers.*;


public class BattlePanel extends JPanel {
    private POOBkemonGUI pooBkemonGUI;

    private String backgroundImage = "battle";
    private String affectVisualOpponnet;
    private String affectVisualActual;
    private String firstPokemon = "6";
    private String secondPokemon = "6";

    private JButton fightButton;
    private JButton inventoryButton;
    private JButton pokemonButton;
    private JButton runButton;

    private ArrayList<JButton> buttonsMovs;

    private JPanel movesPanel;
    private JPanel opciones;
    private JPanel battleOptionsPanel;
    private JButton backToOptionsBattle;
    private JButton sacrificButton;


    private JPanel playerStatsPanel;
    private JPanel opponentStatsPanel;

    private CustomHealthBar playerHealthBar;
    private CustomHealthBar opponentHealthBar;

    private JLabel info;
    private JLabel moveLabel;
    private JPanel panelInfo; 

    private JLabel playerHealthLabel;
    private JLabel opponentHealthLabel;
    private JLabel playerNameLabel;
    private JLabel opponentNameLabel;


    private int xFirst, xSecond;
    private int yFirst, ySecond;
    private Color actualColor;

    private JLabel timerLabel;
    private BattleTimer timer;

    private Font pokemonFont;
    protected CardLayout cardLayoutButtons;
    
    private ArrayList<String> trainerActualMovements;

    private JButton cargarPartida;
    private JButton guardarPartida;

    private int damageToShow = 0;
    private int damageAnimX, damageAnimY, damageAnimAlpha;
    private boolean isAnimatingDamage = false;
    private Timer damageTimer;

    public BattlePanel(POOBkemonGUI newPo) {
        pooBkemonGUI = newPo;
        prepareElements();
        prepareActions();
    }

    public void inicializate(){
        trainerActualMovements = pooBkemonGUI.domain.getMovementsStringCurrent();
        actualColor = pooBkemonGUI.domain.getCurrentColor();

        String nameCurrent = pooBkemonGUI.domain.getCurrentPokemonName();
        int psCurrent = pooBkemonGUI.domain.getCurrentPokemonPs();
        int levelCurrent = pooBkemonGUI.domain.getCurrentPokemonLevel();
        int maxPs = pooBkemonGUI.domain.getcurrentMaxPs();
        setFirstPokemon(Integer.toString(pooBkemonGUI.domain.getCurrentPokemonPokedexIndex()));
        playerStatsPanel = createStatsPanel(nameCurrent, levelCurrent ,psCurrent,maxPs, true);
        String nameOponent = pooBkemonGUI.domain.getOponentPokemonName();
        int psOponent = pooBkemonGUI.domain.getOponentPokemonPs();
        int levelOponent = pooBkemonGUI.domain.getOponentPokemonLevel();
        int maxPsOponent = pooBkemonGUI.domain.getOponentMaxPs();
        setSecondPokemon(Integer.toString(pooBkemonGUI.domain.getOponentPokemonPokedexIndex()));
        opponentStatsPanel = createStatsPanel(nameOponent,levelOponent,psOponent,maxPsOponent, false);
        
        playerStatsPanel.setBounds(xFirst - 30, yFirst - 100, 300, 100);
        opponentStatsPanel.setBounds(xSecond - 30, ySecond - 80, 300, 100);

        add(playerStatsPanel);
        add(opponentStatsPanel);
        movementButtons();
        actualizarColor();
        timer.iniciarTemporizadorDeBatalla();
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
        calculatePokemonPositions();

        timerLabel = new JLabel();
        timer = new BattleTimer(pooBkemonGUI){
            public void iniciarTemporizadorDeBatalla(){  
            battleTimer = new Timer(1000, e -> {
                pooBkemonGUI.domain.reduceTimeBattle(); 
                actualizar();
            });
            battleTimer.start();
            }
        };
        buttonsMovs = new ArrayList<>();
        trainerActualMovements = new ArrayList<>();
        backToOptionsBattle = new JButton("Back");
        sacrificButton = new JButton("Sacrificar");
        cargarPartida = new JButton("Cargar Partida");
        guardarPartida = new JButton("Guardar");

        info = new JLabel("Elige tu acción");
        info.setFont(pokemonFont);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setOpaque(true);
        
        JPanel panelIz = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIz.setOpaque(false);

        panelInfo = new JPanel();
        panelInfo.setOpaque(false);
         
        panelInfo.add(info);
        info.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(actualColor, 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        info.setBackground(actualColor);

        panelInfo.setBorder(BorderFactory.createLineBorder(actualColor, 2));

        add(panelIz, BorderLayout.SOUTH);

        opciones = pooBkemonGUI.invisiblePanelWithOpacity();
        opciones.setOpaque(false);
        opciones.setPreferredSize(new Dimension(350, 150));
        
        cardLayoutButtons = new CardLayout();
        opciones.setLayout(cardLayoutButtons);

        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPanel.setOpaque(false);
        JPanel timerPanel = new JPanel(new BorderLayout());
        JLabel timerTextLabel = new JLabel("Timer");
        timerTextLabel.setFont(pokemonFont);
        timerPanel.add(timerTextLabel, BorderLayout.NORTH);
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        

        upPanel.add(cargarPartida);
        upPanel.add(guardarPartida);
        upPanel.add(timerPanel);

        add(upPanel, BorderLayout.NORTH);
        panelIz.add(opciones);

        battleOptionsPanel = new JPanel(new GridLayout(2,2,1,1));
        battleOptionsPanel.setOpaque(false);

        fightButton = new JButton("LUCHAR");
        inventoryButton = new JButton("MOCHILA");
        pokemonButton = new JButton("POKéMON");
        runButton = new JButton("HUIR");        

        battleOptionsPanel.add(fightButton);
        battleOptionsPanel.add(inventoryButton);
        battleOptionsPanel.add(pokemonButton);
        battleOptionsPanel.add(runButton);

        JPanel temp = new JPanel(new BorderLayout());
        temp.setOpaque(false);
        temp.add(panelInfo, BorderLayout.NORTH);
        temp.add(battleOptionsPanel, BorderLayout.CENTER);
        
        opciones.add(temp, "Opciones");

        showBattleOptionsPanel();
        prepareElementsStyle();
    }

    private void prepareElementsStyle(){
        pooBkemonGUI.styleButton(backToOptionsBattle);
        pooBkemonGUI.styleButton(sacrificButton);
        pooBkemonGUI.styleButton(cargarPartida);
        pooBkemonGUI.styleButton(guardarPartida);
        pooBkemonGUI.styleButton(fightButton);
        pooBkemonGUI.styleButton(inventoryButton);
        pooBkemonGUI.styleButton(pokemonButton);
        pooBkemonGUI.styleButton(runButton);
        
        fightButton.setPreferredSize(new Dimension(20, 25));
        inventoryButton.setPreferredSize(new Dimension(20, 25));
        pokemonButton.setPreferredSize(new Dimension(20, 25));
        runButton.setPreferredSize(new Dimension(20, 25));
    }

    public void movementButtons() {
        movesPanel = new JPanel(new BorderLayout());
        movesPanel.setOpaque(false);

        JPanel movesButtonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        movesButtonsPanel.setOpaque(false);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(248, 248, 248, 220));
        messagePanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(40, 40, 40), 3, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        messagePanel.setLayout(new BorderLayout());

        moveLabel = new JLabel("¿Qué movimiento debería usar " + pooBkemonGUI.domain.getCurrentPokemonName() + "?"); //falta
        moveLabel.setFont(pokemonFont);
        messagePanel.add(moveLabel, BorderLayout.CENTER);

        for (String move : trainerActualMovements) {
            JButton moveBtn = new JButton(move);
            pooBkemonGUI.styleButton(moveBtn);
            moveBtn.setFont(pokemonFont);
            moveBtn.setPreferredSize(new Dimension(150, 40));
            moveBtn.setMinimumSize(new Dimension(150, 40));
            try{moveBtn.setToolTipText("PP: "+String.valueOf(pooBkemonGUI.domain.getPPInBattle(move)));}
            catch(PoobkemonException e){System.out.println(e.getMessage());}
            catch (Exception e) {LogPOOBKEMON.record(e);}

            movesButtonsPanel.add(moveBtn);
            buttonsMovs.add(moveBtn);
        }

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonPanel.setOpaque(false);
        backToOptionsBattle.setPreferredSize(new Dimension(100, 30));
        sacrificButton.setPreferredSize(new Dimension(100, 30));
        backButtonPanel.add(backToOptionsBattle);
        backButtonPanel.add(sacrificButton);

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setOpaque(false);
        topSection.add(messagePanel, BorderLayout.CENTER);
        topSection.add(backButtonPanel, BorderLayout.SOUTH);

        movesPanel.add(topSection, BorderLayout.NORTH);
        movesPanel.add(movesButtonsPanel, BorderLayout.CENTER);

        JPanel paddingPanel = new JPanel();
        paddingPanel.setOpaque(false);
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        paddingPanel.setLayout(new BorderLayout());
        paddingPanel.add(movesButtonsPanel, BorderLayout.CENTER);

        movesPanel.add(paddingPanel, BorderLayout.CENTER);

        opciones.add(movesPanel, "MovimientosP");
        movementsButtons();
        movesPanel.revalidate();
        movesPanel.repaint();

    }

    public void movementsButtons(){
        for(JButton moveBtn: buttonsMovs){
            moveBtn.addActionListener(e -> {
                actualizar();
                System.out.println("Selected move: " + moveBtn.getText());
                if(gameEnd()){return;}
                int oldIndex = pooBkemonGUI.domain.getOponentPokemonPokedexIndex();
                try{
                    pooBkemonGUI.domain.movementPerformed(moveBtn.getText());

                    animatedDamage();

                    if (!pooBkemonGUI.domain.isAliveOpponentPokemon() || oldIndex != pooBkemonGUI.domain.getOponentPokemonPokedexIndex()){
                        int newIndex = pooBkemonGUI.domain.getOponentPokemonPokedexIndex();
                        setSecondPokemon(Integer.toString(newIndex));
                    }
                    actualizar();
                    showBattleOptionsPanel();
                }
                catch(PoobkemonException o){
                    System.out.println("No se hace el ataque: "+ o.getMessage());
                    if (o.getMessage().equals("El pokemon esta muerto")) JOptionPane.showMessageDialog(null, "Tu Pokemon Ha muerto","Alerta Pokemon Muerto",
                                        JOptionPane.INFORMATION_MESSAGE);
                    if(gameEnd()){return;}
                    showBattleOptionsPanel();
                }catch (Exception u) {LogPOOBKEMON.record(u);}
            });
        }
    }

    private void prepareActions(){
        fightButton.addActionListener(e -> {
            actualizar();
            showMovesPanel();
        });
        sacrificButton.addActionListener(e -> actualizar());
        backToOptionsBattle.addActionListener(e -> {
            actualizar();
            showBattleOptionsPanel();
            showBattleOptionsPanel();
        });
    }

    public JButton getSalvarPartida() {
        return guardarPartida;
    }
    public JButton getCargarPartida() {
        return cargarPartida;
    }
    public void stopTimer(){
        if (timer != null) {
            timer.detenerTemporizadorDeBatalla();
        }
    }

    public boolean gameEnd(){
        try{
            if (pooBkemonGUI.domain.GameIsOVer()){
                stopTimer();
                JOptionPane.showMessageDialog(this, "Ha ganado: "+ pooBkemonGUI.domain.getWinner(),"Se acabo!",JOptionPane.INFORMATION_MESSAGE);
                pooBkemonGUI.changePanel("inicio");
                pooBkemonGUI.resetBattles();
                pooBkemonGUI.domain.endBattle();
                reset();
                return true;
            }   }
        catch(Exception e){}
        return false;
    }

    public void actualizar(){
        if(gameEnd()){return;}
        actualizarSacrificable();
        actualizaInfo();
        actualizarColor();
        actualizarBar();
        actualizarListaMovements();
        setSecondPokemon(Integer.toString(pooBkemonGUI.domain.getOponentPokemonPokedexIndex()));
        setFirstPokemon(Integer.toString(pooBkemonGUI.domain.getCurrentPokemonPokedexIndex()));
        gameEnd();
    }
    
    public void actualizarSacrificable() {
    try {
        boolean hasAlivePokemons = pooBkemonGUI.domain.getCurrentAlivePokemons().size() > 1;
        boolean isSacrificable = hasAlivePokemons && pooBkemonGUI.domain.isSacrificableCurrent();

        sacrificButton.setEnabled(isSacrificable);
        sacrificButton.setVisible(isSacrificable);

    } catch (PoobkemonException e) {
        System.out.println(e.getMessage());
        sacrificButton.setEnabled(false);
        sacrificButton.setVisible(false);
    }catch (Exception e) {LogPOOBKEMON.record(e);}
}

    public void actualizarBar() {
        String pokemonNameOponent = pooBkemonGUI.domain.getOponentPokemonName();
        int healthOponent = pooBkemonGUI.domain.getOponentPokemonPs();
        int levelOponent = pooBkemonGUI.domain.getOponentPokemonLevel();
        int maxPsOponent = pooBkemonGUI.domain.getOponentMaxPs();

        String pokemonNameCurrent = pooBkemonGUI.domain.getCurrentPokemonName();
        int healthCurrent = pooBkemonGUI.domain.getCurrentPokemonPs();
        int levelCurrent = pooBkemonGUI.domain.getCurrentPokemonLevel();
        int maxPsCurrent = pooBkemonGUI.domain.getcurrentMaxPs();

        String jugadorLabelText = playerNameLabel.getText();
        String oponenteLabelText = opponentNameLabel.getText();

        boolean cambioJugador = !jugadorLabelText.startsWith(pokemonNameOponent + " Nv.");
        boolean cambioOponente = !oponenteLabelText.startsWith(pokemonNameCurrent + " Nv.");

        actualizarHealt(healthCurrent, maxPsCurrent, healthOponent, maxPsOponent, cambioJugador, cambioOponente);

        playerNameLabel.setText(pokemonNameCurrent + " Nv." + levelCurrent);
        opponentNameLabel.setText(pokemonNameOponent + " Nv. " + levelOponent);
    }

    public void actualizarHealt(int healthOne, int health1Max, int healthTwo, int healthTwoMax, boolean setDirectoOne, boolean setDirectoTwo) {
        playerHealthBar.setMaximum(health1Max);
        opponentHealthBar.setMaximum(healthTwoMax);

        if (setDirectoOne || playerHealthBar.getValue() == healthOne) {
            playerHealthBar.setValue(healthOne);
        } else {
            playerHealthBar.setAnimatedValue(healthOne);
        }
        if (setDirectoTwo || opponentHealthBar.getValue() == healthTwo) {
            opponentHealthBar.setValue(healthTwo);
        } else {
            opponentHealthBar.setAnimatedValue(healthTwo);
        }

        playerHealthLabel.setText(healthOne + "/" + health1Max);
        opponentHealthLabel.setText(healthTwo + "/" + healthTwoMax);
        playerStatsPanel.repaint();
        opponentStatsPanel.repaint();
        repaint();
    }
    
    public void actualizarListaMovements(){
        trainerActualMovements = pooBkemonGUI.domain.getMovementsStringCurrent();
        int index =0;
        for (String move : trainerActualMovements) {
            JButton btn = buttonsMovs.get(index);
            if (btn != null) {
                try {
                    btn.setToolTipText("PP: " + pooBkemonGUI.domain.getPPInBattle(move));
                } catch (PoobkemonException e) {
                    System.out.println("Error al actualizar  PP: "+ e.getMessage());
                }catch (Exception e) {LogPOOBKEMON.record(e);}
                btn.setText(move);
            }
            index++;
        }
    }
    
    public void actualizaInfo(){
        timerLabel.setText("" + pooBkemonGUI.domain.getTurnTimer());
        info.setText("<html><body style='width: 200px'>" + pooBkemonGUI.domain.getLastMessage() + "</body></html>");
        moveLabel.setText("¿Qué movimiento debería usar " + pooBkemonGUI.domain.getCurrentPokemonName() + "?");
    }

    public void actualizarColor(){
        actualColor = pooBkemonGUI.domain.getCurrentColor();
        info.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(actualColor, 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        info.setBackground(actualColor);
        panelInfo.setBackground(actualColor);
        panelInfo.setBorder(BorderFactory.createLineBorder(actualColor, 2));
    }
    
    public void showMovesPanel() {
        cardLayoutButtons.show(opciones, "MovimientosP");
        opciones.revalidate();
        opciones.repaint();
    }

    public void showBattleOptionsPanel() {
        cardLayoutButtons.show(opciones,"Opciones"); 
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
    public JButton getSacrificableButton(){
        return sacrificButton;
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

    private void animatedDamage(){
        showDamageAnimation(pooBkemonGUI.domain.getLastDamage());
    }

    private void timerExpired() {
        info.setText("¡Se acabó el tiempo!");
        showBattleOptionsPanel();
    }


    public void reset(){
        trainerActualMovements.clear();
    }
    private void showDamageAnimation(int damage) {
        int scaledWidth = getWidth() / 3;
        int scaledHeight = getHeight() / 3;
        damageToShow = damage;

        damageAnimX = xSecond - 15 + scaledWidth / 2 - 20;
        damageAnimY = ySecond + 50 + scaledHeight / 3;
        damageAnimAlpha = 255;
        isAnimatingDamage = true;

        if (damageTimer != null && damageTimer.isRunning()) damageTimer.stop();
        damageTimer = new Timer(30, e -> {
            damageAnimY -= 2;
            damageAnimAlpha -= 10;
            if (damageAnimAlpha <= 0) {
                isAnimatingDamage = false;
                ((Timer)e.getSource()).stop();
            }
            repaint();
        });
        damageTimer.start();
        repaint();
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
        if (isAnimatingDamage) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            Color color;
            if (damageToShow > 100) {
                color = new Color(255, 0, 0, Math.max(0, damageAnimAlpha)); // Rojo
            } else {
                color = new Color(255, 140, 0, Math.max(0, damageAnimAlpha)); // Naranja (RGB: 255,140,0)
            }
            g2.setColor(color);
            String dmg = "-" + damageToShow;
            g2.drawString(dmg, damageAnimX, damageAnimY);
            g2.dispose();
        }
    }
}