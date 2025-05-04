package presentation;
import domain.*;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class POOBkemonGUI extends JFrame {
    private JMenuItem leave;
    private JMenuItem save;
    private JFileChooser fileChooser;
    protected CardLayout cardLayout;
    protected JPanel panelContenedor;
    private PrincipalPanel menuPrincipal;
    private JButton regresarMvsM;
    private PokedexPanel pokedexPanelPrueba;
    private ModesOfGamePanel modesOfGamePanel;
    private ModesOfGamePanelNormal modesOfGamePanelNormal;
    private ModesOfGamePanelSurvival modesOfGamePanelSurvival;
    protected ModePlayerVSPlayer playerVSplayerPanel;
    protected ModePlayerVSPlayer playerVSplayerPanelSurvival;
    protected ModePlayerVsMachine playerVsMachinePanel;
    private BattlePanel panelBattle;
    private ModeMachineVsMachine machineVsMachinePanel;
    protected ListPokemonAvailable listPokemonsPanel;
    private InventoryPanel panelInvetory;
    protected SelectionPokemon chooser;
    protected ListOfMovementsPanel listMovements;
    private JColorChooser colorChooser;

    private Inicio inicio;

    protected POOBkemon domain = new POOBkemon();
    protected TreeMap<String,Pokemon> pokemones;
    protected TreeMap<String, Movement> movimientos;
    protected ArrayList<String> pokemonesEscogidos;

    /**
     * Constructor of POOBkemon
     */
    public POOBkemonGUI(){
        domain = domain.deserializateGame();
        pokemones = domain.getPokedex();
        movimientos = domain.getMovements();
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
        colorChooser = new JColorChooser();
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

        panelBattle.getFighButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            
                //panelBattle.actualizar(90,20);
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
        inicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panelContenedor,"principal");;
            }
            });

            prepareMovementActions();
    }

    private void changePanel(String namePanel){
        cardLayout.show(panelContenedor,namePanel);
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

        //chooser = new SelectionPokemon(this, new Color(0, 0, 255));
        //panelContenedor.add(chooser, "chooser");

        inicio = new Inicio(this);
        panelContenedor.add(inicio, "Inicio");

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

        panelInvetory = new InventoryPanel(this);
        panelContenedor.add(panelInvetory,"inventory");

         /*por ahora se borra dps */
        //Color azulOpaco = new Color(0, 0, 255); // RGB puro, completamente opaco
        //Color rojoOpaco = new Color(255, 0, 0); // RGB puro, completamente opaco

        chooser = new SelectionPokemon(this);
        panelContenedor.add(chooser, "chooser");

        listMovements = new ListOfMovementsPanel(this);
        panelContenedor.add(listMovements,"movimientos");

        listPokemonsPanel = new ListPokemonAvailable(this,new Color(0, 0, 255));
        panelContenedor.add(listPokemonsPanel,"pokemon list");

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
    public POOBkemon getDomain(){
        return domain;
    }
    private void prepareMovementActions(){
        menuPrincipal.jugar.addActionListener(e -> changePanel("modos de juego"));

        modesOfGamePanel.getButtonNormal().addActionListener(e -> changePanel("normal"));

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
                chooseDifficult();
                cardLayout.show(panelContenedor,"player vs machine"); //aquilocambie
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

        chooser.getButtonBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"player vs machine");
                
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
                listMovements.resetPokemonChosen();
                cardLayout.show(panelContenedor,"principal");
            }
        });
        playerVsMachinePanel.getButtonRegresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"normal");
            }
        });

        playerVsMachinePanel.getChoserColorNext().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                playerVsMachinePanel.changeColor();
                chooser.setColor();
            }
        });

        playerVsMachinePanel.getnexButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"chooser");
            }
        });

        panelBattle.getPokemonButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //listPokemonsPanel.inicializate(pokemonesEscogidos);
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
        
        listPokemonsPanel.getBackButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"battle");
            }
        });

        listPokemonsPanel.getDoneButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor,"battle");
                //HACE EL CAMBIO DE POKEMON EN DADO CASO?
            }
        });
        

        listMovements.getNextButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                if (!listMovements.isSelectedMovements()) {
                    return;
                }
                listMovements.resetPokemonChosen();
                cardLayout.show(panelContenedor,"battle");
            }
        });
        listMovements.getComeButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                listMovements.resetPokemonChosen();
                cardLayout.show(panelContenedor,"chooser");
            }
        });
        

    }

    private void chooseDifficult(){
        String [] options = {"Changing Trainer","Expert Trainer","Attacking Trainer","Defensive Trainer"};
        int seleccion = JOptionPane.showOptionDialog(null, "Choose Machine Difficult", "Selector Difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (seleccion >= 0){
            JOptionPane.showMessageDialog(null, "Elegiste: " + options[seleccion]);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se seleccionó");
        }
    }

    
    public static void main(String args []){
        POOBkemonGUI kemon = new POOBkemonGUI();
        kemon.setLocationRelativeTo(null);
        kemon.setVisible(true);
    }
}