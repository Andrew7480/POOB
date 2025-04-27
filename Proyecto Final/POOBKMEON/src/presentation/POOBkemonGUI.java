package presentation;
import domain.*;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class POOBkemonGUI extends JFrame {
    private JMenuItem leave;
    private JMenuItem save;
    private POOBkemon domain;
    private JFileChooser fileChooser;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private PrincipalPanel menuPrincipal;
    private JButton regresarNormal;
    private JButton regresarSurvival;
    
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
        menuPrincipal.modoNormal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "normal");
            }
        });
        menuPrincipal.modoSurvival.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "survival");
            }
        });
        regresarNormal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "principal");
            }
        });
        regresarSurvival.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(panelContenedor, "principal");
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

        menuPrincipal = new PrincipalPanel();
        panelContenedor.add(menuPrincipal, "principal");

        JPanel modoNormal = modesOfGamePanel("bb8jr133l9sa1.GIF", "normal", true);
        panelContenedor.add(modoNormal, "normal");
        
        JPanel modoSurvival = modesOfGamePanel("survival.GIF", "survival", false);
        panelContenedor.add(modoSurvival, "survival");
    }
    
    private JPanel modesOfGamePanel(String backgroundImage, String panelName, boolean isNormal) {
        JPanel gamePanel = new JPanel() {
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
        gamePanel.setLayout(new BorderLayout());
        JButton btnRegresar = new JButton("Volver al Menú Principal");
        styleButton(btnRegresar);
        if (isNormal) {
            regresarNormal = btnRegresar;
        } else {
            regresarSurvival = btnRegresar;
        }

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 180));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresar);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return gamePanel;
    }
    
    private void styleButton(JButton button) {
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