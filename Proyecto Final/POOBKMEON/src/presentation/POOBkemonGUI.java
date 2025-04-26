package presentation;
import domain.*;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
* POOBkemonGUI
 */
public class POOBkemonGUI extends JFrame {
    private JMenuItem leave;
    private JMenuItem openItem;
    private JMenuItem save;
    private JButton normal;
    private JButton survival;
    private POOBkemon domain;
    private JFileChooser fileChooser;
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
        add(new PrincipalPanel());
        fileChooser = new JFileChooser();
        prepareElementsMenu();
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
    }


    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu menuDesplegable = new JMenu("Menu");
        leave = new JMenuItem("Exit");
        leave.addActionListener(null);
        save = new JMenuItem("Save");
        save.addActionListener(null);
        menuDesplegable.add(leave);
        menuDesplegable.addSeparator();
        menuDesplegable.add(save);
        menu.add(menuDesplegable);
        setJMenuBar(menu);
    }


    /*
     * Do the action of close the window
     */
    private void exit(){
        int option = JOptionPane.showConfirmDialog(this,"Estas seguro de que quieres salir?",
        "Confirmar salida",JOptionPane.YES_NO_OPTION);
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