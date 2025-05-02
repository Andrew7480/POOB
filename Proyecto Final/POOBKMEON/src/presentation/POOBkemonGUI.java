package presentation;
import domain.*;

import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class POOBkemonGUI extends JFrame {
    private JMenuItem leave;
    private JMenuItem save;
    private JFileChooser fileChooser;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private PrincipalPanel menuPrincipal;
    private JButton regresarMvsM;
    private PokedexPanel pokedexPanelPrueba;
    private ModesOfGamePanel modesOfGamePanel;
    private ModesOfGamePanelNormal modesOfGamePanelNormal;
    private ModesOfGamePanelSurvival modesOfGamePanelSurvival;
    private ModePlayerVSPlayer playerVSplayerPanel;
    private ModePlayerVSPlayer playerVSplayerPanelSurvival;
    private ModePlayerVsMachine playerVsMachinePanel;
    private BattlePanel panelBattle;
    private ModeMachineVsMachine machineVsMachinePanel;
    private ListPokemonAvailable listPokemonsPanel;
    private InventoryPanel panelInvetory;

    protected POOBkemon domain;

    /**
     * Constructor of POOBkemon
     */
    public POOBkemonGUI(){
        domain = new POOBkemon();
        prepareElements();
        prepareActions();
    }
    
    /*
     * prepare elements of the innermost layer
     */
    private void prepareElements(){
        setTitle("POOBkemon Esmeralda");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
        fileChooser = new JFileChooser();
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
        menuPrincipal.jugar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "modos de juego");
            }
        });

        modesOfGamePanel.getButtonNormal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "normal");
            }
        });

        modesOfGamePanelNormal.getButtonRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "modos de juego");
            }
        });
        modesOfGamePanelSurvival.getButtonRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "modos de juego");
            }
        });

        modesOfGamePanelNormal.getButtonPvsM().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                cardLayout.show(panelContenedor,"player vs machine");
            }
        });

        modesOfGamePanelNormal.getButtonPvsP().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"player vs player");
            }
        });

        modesOfGamePanelSurvival.getButtonPvsP().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"player vs player survival");
            }
        });

        modesOfGamePanelNormal.getButtonMvsM().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"machine vs machine");
            }
        });

        machineVsMachinePanel.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });

        playerVSplayerPanel.getBtnRegresarNormal().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });

        menuPrincipal.pokedex.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "pokedex");
            }
        });

        pokedexPanelPrueba.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"principal");
            }
        });
        
        modesOfGamePanel.getButtonRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"principal");
            }
        });
        
        
        modesOfGamePanel.getButtonNormal().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });


        modesOfGamePanel.getButtonSurvival().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"survival");
            }
        });


        playerVSplayerPanelSurvival.getButtonRegresarSurvival().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"survival");
            }
        });

        playerVSplayerPanel.getButtonContinuar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"battle");
            }
        });


        playerVSplayerPanel.getBtnRegresarNormal().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });

        panelBattle.getRunButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"principal");
                //resetiar batalla??
            }
        });

        panelBattle.getFighButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            
                //panelBattle.actualizar(90,20);
            }
        });

        playerVsMachinePanel.getButtonRegresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });

        panelBattle.getFighButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Mostrar lista de movimientos ->
            }
        });
        panelBattle.getInventoryButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Mostrar Inventario
            }
        });

        panelBattle.getPokemonButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"pokemon list");
            }
        });

        panelBattle.getInventoryButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"inventory");
            }
        });

        panelInvetory.getButtonBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"battle");
            }
        });

        listPokemonsPanel.getButtonBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"battle");
            }
        });

        panelBattle.getFighButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelBattle.showMovesPanel();               
            }
        });

        panelBattle.getBackOptions().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelBattle.showBattleOptionsPanel();
            }
        });


    }

    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu menuDesplegable = new JMenu("Menu");
        leave = new JMenuItem("Exit");
        save = new JMenuItem("Save");
        menuDesplegable.add(leave);
        menuDesplegable.addSeparator();
        menuDesplegable.add(save);
        menu.add(menuDesplegable);
        setJMenuBar(menu);
    }

    private void prepareElementsModesOfGame(){
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        menuPrincipal = new PrincipalPanel(this);
        panelContenedor.add(menuPrincipal, "principal");

        pokedexPanelPrueba = new PokedexPanel(this);
        panelContenedor.add(pokedexPanelPrueba,"pokedex");

        modesOfGamePanel = new ModesOfGamePanel(this);
        panelContenedor.add(modesOfGamePanel, "modos de juego");

        modesOfGamePanelNormal = new ModesOfGamePanelNormal(this);
        panelContenedor.add(modesOfGamePanelNormal,"normal");

        modesOfGamePanelSurvival = new ModesOfGamePanelSurvival(this);
        panelContenedor.add(modesOfGamePanelSurvival,"survival");

        playerVSplayerPanel = new ModePlayerVSPlayer(this,true);
        panelContenedor.add(playerVSplayerPanel,"player vs player");

        playerVSplayerPanelSurvival = new ModePlayerVSPlayer(this,false);
        panelContenedor.add(playerVSplayerPanelSurvival,"player vs player survival");

        playerVsMachinePanel = new ModePlayerVsMachine(this);
        panelContenedor.add(playerVsMachinePanel,"player vs machine");

        machineVsMachinePanel = new ModeMachineVsMachine(this);
        panelContenedor.add(machineVsMachinePanel,"machine vs machine");

        panelBattle = new BattlePanel(this);
        panelContenedor.add(panelBattle,"battle");

        listPokemonsPanel = new ListPokemonAvailable(this);
        panelContenedor.add(listPokemonsPanel,"pokemon list");

        panelInvetory = new InventoryPanel(this);
        panelContenedor.add(panelInvetory,"inventory");

    }

    private JPanel modesMachineVsMachine(String backgroundImage,String panelName){
        JPanel game = background(backgroundImage);
        game.setLayout(new BorderLayout());
        JButton btnRegresar = new JButton("Volver");
        styleButton(btnRegresar);
        regresarMvsM = btnRegresar;
        JPanel buttonPanel = invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresar);
        game.add(buttonPanel, BorderLayout.SOUTH);
        return game;
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
                    back = new ImageIcon(getClass().getResource("/resources/" + backgroundImage));
                } catch (Exception e) {
                    back = new ImageIcon(getClass().getResource("/resources/download.GIF"));
                }
                g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
    
    public void styleButtonExternal(JButton button) {
        button.setFont(new Font("Times new Roman", Font.BOLD, 14));
        button.setBackground(new Color(30, 30, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(180, 35));
    }

    public void styleButton(JButton button){
        button.setFont(new Font("Times new Roman", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(180, 35));
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
    
    public static void main(String args []){
        POOBkemonGUI kemon = new POOBkemonGUI();
        kemon.setLocationRelativeTo(null);
        kemon.setVisible(true);
    }
}