package presentation;
//import domain.*; //MIRAR
import domain.POOBkemon;
import domain.PoobkemonException;
import domain.LogPOOBKEMON;

import java.io.File;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import presentation.ModesOfGame.*;
import presentation.Pokedex.PokedexPanel;
import presentation.helpers.ImageButton;

public class POOBkemonGUI extends JFrame {
    private JMenuItem leave;
    private JMenuItem save;
    private JMenuItem openBattle;
    private JFileChooser fileChooser;
    public CardLayout cardLayout;
    public JPanel panelContenedor;
    private PokedexPanel pokedexPanelPrueba;
    protected ModePlayerVSPlayer playerVSplayerPanel;
    protected ModePlayerVsMachine playerVsMachinePanel;
    protected ModePlayerVSPlayer playerVSplayerPanelSurvival;
    protected ModeMachineVsMachine machineVsMachinePanel;
    protected ModePlayerVSPlayerSurvival panelPvsPSurvival;
    
    private JPanel inicio;
    private JPanel panelPrincipal;
    private JPanel modesOfGamePanelP;
    private JPanel modesOfGameNormal;
    private JPanel modesOfGameSurvival;

    public POOBkemon domain = new POOBkemon();


    private static final Color STANDARD_COLOR = new Color(70, 130, 180);
    private static final Color EXTERNAL_COLOR = new Color(30, 30, 180);
    private static final Color HOVER_COLOR = new Color(100, 160, 210);
    private static final Color EXTERNAL_HOVER_COLOR = new Color(60, 60, 210);
    private static final Color PRESSED_COLOR = new Color(50, 110, 160);
    private static final Color EXTERNAL_PRESSED_COLOR = new Color(20, 20, 160);


    /**
     * Constructor of POOBkemon
     */
    public POOBkemonGUI(){
        domain = domain.deserializateGame();
        prepareElements();
        prepareActions();
    }
    
    /*
     * prepare elements of the innermost layer
     */
    private void prepareElements(){
        setTitle("POOBkemon Esmeralda");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth() / 1.5), (int)(screenSize.getHeight() / 1.5));
        fileChooser = new JFileChooser();
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icono.jpg"));
        setIconImage(icon.getImage());
        prepareElementsMenu();
        prepareElementsModesOfGame();
        add(panelContenedor);
    }

    public void prepareActions(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                exit();
            }
        });

        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                saveOpen();
            }
        });
        openBattle.addActionListener(e -> {
            //OpenBattle();

        });
        prepareMovementActions();
    }

    public void changePanel(String namePanel){
        cardLayout.show(panelContenedor,namePanel);
    }

    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu menuDesplegable = new JMenu("Menu");
        leave = new JMenuItem("Exit");
        save = new JMenuItem("Save");
        openBattle = new JMenuItem("Open Batle");
        menuDesplegable.add(leave);
        menuDesplegable.addSeparator();
        menuDesplegable.add(save);
        menuDesplegable.addSeparator();
        menuDesplegable.add(openBattle);
        menu.add(menuDesplegable);
        setJMenuBar(menu);
    }

    private void prepareElementsModesOfGame(){
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        inicio = inicio();
        panelContenedor.add(inicio,"inicio");

        panelPrincipal = menuPrincipal();
        panelContenedor.add(panelPrincipal,"principal");

        pokedexPanelPrueba = new PokedexPanel(this);
        panelContenedor.add(pokedexPanelPrueba,"pokedex");

        modesOfGamePanelP = modesOfGamePanel();
        panelContenedor.add(modesOfGamePanelP,"modos de juego");

        modesOfGameNormal = modesOfGamePanelNormal();
        panelContenedor.add(modesOfGameNormal,"normal");
        
        modesOfGameSurvival = modesOfGamePanelSurvival();
        panelContenedor.add(modesOfGameSurvival,"survival");

        playerVSplayerPanel = new ModePlayerVSPlayer(this);
        panelContenedor.add(playerVSplayerPanel,"player vs player");
            
        playerVsMachinePanel = new ModePlayerVsMachine(this);
        panelContenedor.add(playerVsMachinePanel,"player vs machine");

        machineVsMachinePanel = new ModeMachineVsMachine(this);
        panelContenedor.add(machineVsMachinePanel,"machine vs machine");

        panelPvsPSurvival = new ModePlayerVSPlayerSurvival(this);
        panelContenedor.add(panelPvsPSurvival,"player vs player survival");

    }

    private JPanel inicio(){
        JPanel inicioPanelPrueba = background("inicio");
        inicioPanelPrueba.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panelContenedor,"principal");
            }
        });
        return inicioPanelPrueba;
    }
    private JPanel backgroundMenu(String backgroundImage){
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon back = new ImageIcon(getClass().getResource("/resources/" + backgroundImage + ".GIF"));
                g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
                Image centeredImage = new ImageIcon(getClass().getResource("/resources/" + "title" + ".PNG")).getImage();
                int scaledWidth = getWidth() / 2;
                int scaledHeight = getHeight() / 2;
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                g.drawImage(centeredImage, x, y, scaledWidth, scaledHeight, this);
            }
        };
    }

    private JPanel menuPrincipal(){
        JPanel menuPrincipal = backgroundMenu("download");
        menuPrincipal.setLayout(new BorderLayout());
        JPanel modosDeJuego = invisiblePanelWithOpacity();
        modosDeJuego.setOpaque(false);
        modosDeJuego.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        menuPrincipalButtons(modosDeJuego);
        menuPrincipal.add(modosDeJuego, BorderLayout.SOUTH);
        return menuPrincipal;
    }

    private void menuPrincipalButtons(JPanel modosDeJuego){
        JButton play = new JButton("Play");
        JButton pokedex = new JButton("Pokedex");
        styleButtonExternal(play);
        styleButtonExternal(pokedex);
        modosDeJuego.add(play);
        modosDeJuego.add(pokedex);
        prepareActionsMenuPrincipal(play, pokedex);
    }

    private void prepareActionsMenuPrincipal(JButton play, JButton pokedex){
        pokedex.addActionListener(e -> changePanel("pokedex"));
        play.addActionListener(e -> changePanel("modos de juego"));
    }

    private JPanel modesOfGamePanel(){
        JPanel modesOfGame = background("trainers");
        modesOfGame.setLayout(new BorderLayout());
        modesOfGamePanelButtons(modesOfGame);
        return modesOfGame;
    }

    private void modesOfGamePanelButtons(JPanel modesOfGame){
        JButton back = new JButton("BACK");
        JButton modeNormal = new JButton("MODE NORMAL");
        JButton modeSurvival = new JButton("MODE SURVIVAL");
        styleButtonExternal(back);
        styleButton(modeNormal);
        styleButton(modeSurvival);
        JPanel options = modesOfGames(modeNormal, modeSurvival);
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(options);
        modesOfGame.add(centro, BorderLayout.CENTER);
        JPanel buttonPanel = invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(back);
        modesOfGame.add(buttonPanel,BorderLayout.SOUTH);
        prepareActionsModesOfGamePanel(back, modeNormal, modeSurvival);
    }

    private JPanel modesOfGames(JButton modeNormal, JButton modeSurvival){
        JPanel options = invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        modeNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
        modeSurvival.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(Box.createVerticalStrut(20));
        options.add(modeNormal);
        options.add(Box.createVerticalStrut(15));
        options.add(modeSurvival);
        return options;
    }

    private void prepareActionsModesOfGamePanel(JButton back, JButton normalMode, JButton survivalMode){
        back.addActionListener(e -> changePanel("principal"));
        normalMode.addActionListener(e -> changePanel("normal"));
        survivalMode.addActionListener(e -> changePanel("survival"));
    }

    private JPanel modesOfGamePanelNormal(){
        JPanel modesOfGamePanelNormal = background("trainers");
        modesOfGamePanelNormal.setLayout(new BorderLayout());
        modesOfGamePanelNormalButtons(modesOfGamePanelNormal);
        return modesOfGamePanelNormal;
    }

    private void modesOfGamePanelNormalButtons(JPanel modesOfGamePanelNormal){
        JButton back = new JButton("BACK");
        styleButtonExternal(back);
        JPanel normalMode = modeNormalPanel(back);
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(normalMode);
        JPanel buttonPanel = invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        buttonPanel.add(back);
        modesOfGamePanelNormal.add(buttonPanel,BorderLayout.SOUTH);
        modesOfGamePanelNormal.add(centro,BorderLayout.CENTER);
    }

    private JPanel modeNormalPanel(JButton back){

        JButton playerVsPlayer = new JButton("Player vs Player");
        JButton playerVsMachine = new JButton("Player vs Machine");
        JButton machineVsMachine = new JButton("Machine vs Machine");
        styleButton(playerVsPlayer);
        styleButton(playerVsMachine);
        styleButton(machineVsMachine);
        JPanel options = invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        playerVsMachine.setAlignmentX(Component.CENTER_ALIGNMENT);
        machineVsMachine.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerVsPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(Box.createVerticalStrut(20));
        options.add(playerVsPlayer);
        options.add(Box.createVerticalStrut(15));
        options.add(playerVsMachine);
        options.add(Box.createVerticalStrut(15));
        options.add(machineVsMachine);
        prepareActionsModeNormal(back, playerVsPlayer, playerVsMachine, machineVsMachine);

        return options;
    }

    private void prepareActionsModeNormal(JButton back, JButton playerVsPlayer, JButton playerVsMachine, JButton machineVsMachine){
        back.addActionListener(e -> changePanel("modos de juego"));
        playerVsPlayer.addActionListener(e -> changePanel("player vs player"));
        playerVsMachine.addActionListener(e -> changePanel("player vs machine"));
        machineVsMachine.addActionListener(e -> changePanel("machine vs machine"));
    }

    private JPanel modesOfGamePanelSurvival(){
        JPanel modeSurvivalPanel = background("download");
        modeSurvivalPanel.setLayout(new BorderLayout());
        modesOfGamePanelSurvivalButton(modeSurvivalPanel);
        return modeSurvivalPanel;
    }

    private void modesOfGamePanelSurvivalButton(JPanel modeSurvivalPanel){
        JButton back = new JButton("BACK");
        styleButtonExternal(back);
        JPanel modeSurvival = survivalModeMenu(back);
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(modeSurvival);
        JPanel buttonPanel = invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(back);
        modeSurvivalPanel.add(centro,BorderLayout.CENTER);
        modeSurvivalPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel survivalModeMenu(JButton back){
        JButton playerVsPlayerSurvival = new JButton("Player vs Player");
        styleButton(playerVsPlayerSurvival);
        JPanel options = invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        playerVsPlayerSurvival.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(playerVsPlayerSurvival);
        prepareActionsSurvivalMode(back,playerVsPlayerSurvival);
        return options;
    }

    private void prepareActionsSurvivalMode(JButton back, JButton playerVsPlayerSurvival){
        back.addActionListener(e -> changePanel("modos de juego"));
        playerVsPlayerSurvival.addActionListener(e -> changePanel("player vs player survival"));
    }

    public JPanel invisiblePanelWithoutOpacity(){
        return new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };
    }
    public JPanel invisiblePanelWithOpacity(){
        return new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 180));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };
    }
    private JPanel background(String backgroundImage){
        return new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon back;
                try {
                    back = new ImageIcon(getClass().getResource("/resources/" + backgroundImage+".GIF"));
                } catch (Exception e) {
                    back = new ImageIcon(getClass().getResource("/resources/download.GIF"));
                    LogPOOBKEMON.record(e);
                }
                g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
    public void resetBattles(){
        panelContenedor.remove(playerVSplayerPanel);
        playerVSplayerPanel = new ModePlayerVSPlayer(this);
        panelContenedor.add(playerVSplayerPanel,"player vs player");
            
        panelContenedor.remove(playerVsMachinePanel);
        playerVsMachinePanel = new ModePlayerVsMachine(this);
        panelContenedor.add(playerVsMachinePanel,"player vs machine");

        panelContenedor.remove(machineVsMachinePanel);
        machineVsMachinePanel = new ModeMachineVsMachine(this);
        panelContenedor.add(machineVsMachinePanel,"machine vs machine");

        panelContenedor.remove(panelPvsPSurvival);
        panelPvsPSurvival = new ModePlayerVSPlayerSurvival(this);
        panelContenedor.add(panelPvsPSurvival,"player vs player survival");
    }
    
    public void styleButtonExternal(JButton button) {
        button.setFont(new Font("Times new Roman", Font.BOLD, 14));
        button.setBackground(new Color(30, 30, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(40, 100, 150), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setPreferredSize(new Dimension(180, 35));

        addBasicEffects(button, STANDARD_COLOR, HOVER_COLOR, PRESSED_COLOR);
    }

    public void styleButtonchooser(JButton button){
        try{
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"));
            fuente = fuente.deriveFont(12f);
            button.setFont(fuente);
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 100, 150), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            button.setPreferredSize(new Dimension(180, 35));
        } catch (Exception e) {
            LogPOOBKEMON.record(e);
        }
    }

    public void styleButton(JButton button){
        try{
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/PressStart2P-Regular.ttf"));
            fuente = fuente.deriveFont(12f);
            button.setFont(fuente);
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 100, 150), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            button.setPreferredSize(new Dimension(240, 35));

            addBasicEffects(button, STANDARD_COLOR, HOVER_COLOR, PRESSED_COLOR);
        } catch (Exception e) {
            LogPOOBKEMON.record(e);
        }
    }

    private void addBasicEffects(JButton button, Color normalColor, Color hoverColor, Color pressedColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedColor);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.contains(e.getPoint())) {
                    button.setBackground(hoverColor);
                } else {
                    button.setBackground(normalColor);
                }
            }
        });
    }   


    /*
     * Do the action of close the window
     */
    private void exit(){
        int option = JOptionPane.showConfirmDialog(this, "Estas seguro de que quieres salir?",
                "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public JButton createImageButton(String pokemonName) {
        String imagePath = domain.getPokedexIndexByName(pokemonName) + ".png";
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(getClass().getResource("/resources/" + imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image img = (icon != null) ? icon.getImage() : null;
        JButton button = new ImageButton(pokemonName, img);
        button.setPreferredSize(new Dimension(110, 90));
        button.setMinimumSize(new Dimension(110, 90));
        button.setMaximumSize(new Dimension(110, 90));
        return button;
    }
    
    /*
     * Do the action of save open
     */
    private void saveOpen(){
        int choice = fileChooser.showSaveDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archive = fileChooser.getCurrentDirectory();
            JOptionPane.showMessageDialog(this, "Funcionalidad Guardar en construccion, Lugar donde se guarda: "+ archive.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public POOBkemon getDomain(){
        return domain;
    }


    private void prepareMovementActions(){

        pokedexPanelPrueba.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"principal");
            }
        });
    }


/////////////////////////////////////////////////////////////////////////////////////////
        public void createTrainer(String trainerEscogido ,Color color){
            try{
                if (trainerEscogido == null) {
                    JOptionPane.showMessageDialog(null, "Error: Player trainer name not set");
                    return;
                }
                domain.addNewTrainer(trainerEscogido,color);
            }catch(PoobkemonException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }catch (Exception e) {LogPOOBKEMON.record(e);}
        }

        public void addPokemonsToTrainer(String trainerEscogido,HashMap<String, ArrayList<String>> pokemonsWithAll) throws PoobkemonException{
            HashMap<String, ArrayList<String>> lista = pokemonsWithAll;
            for (Map.Entry<String, ArrayList<String>> entry : lista.entrySet()) {
                domain.addNewPokemon(trainerEscogido, entry.getKey(), entry.getValue().get(0), entry.getValue().get(1),entry.getValue().get(2), entry.getValue().get(3));   
            }
        }

        public ArrayList<String> addItemsToTrainer(String trainerEscogido,ArrayList<String> itemsEscogidos) throws PoobkemonException{
            ArrayList<String> items = itemsEscogidos;
            for (String i:items){
                domain.getTrainer(trainerEscogido).getInventory().addItem(domain.getItems().get(i));
            }
            return domain.getTrainer(trainerEscogido).getInventory().getItemsName();
        }
/////////////////////////////////////////////////////////////////////////////////////////
    public void saveBattle(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try{
                domain.getBattle().save(selectedFile);
            }catch(PoobkemonException h){
                System.out.println(h.getMessage());
        }catch (Exception e) {LogPOOBKEMON.record(e);}
        }
    }
    public void OpenBattle(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this); 
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            domain.deserializateBattle(selectedFile.getAbsolutePath());
        }
    }    
    public ArrayList<String> aleatoryMovements(String namePokemon){
        try{return domain.generateRandomMovementForPokemon(namePokemon);}
        catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } catch (Exception e) {
            LogPOOBKEMON.record(e);
            return new ArrayList<>();
        }
    }

    public static void main(String args []){
        POOBkemonGUI kemon = new POOBkemonGUI();
        kemon.setLocationRelativeTo(null);
        kemon.setVisible(true);
    }
}