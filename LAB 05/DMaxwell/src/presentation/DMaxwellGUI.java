package presentation;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;


public class DMaxwellGUI extends JFrame{
    private JMenuItem leave;
    private JMenuItem openItem;
    private JMenuItem save;
    private JMenuItem newFile;
    private JFileChooser fileChooser;
    public DMaxwellGUI(){
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        setTitle("Maxwell Discreto");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/4, (int) screenSize.getHeight()/4);
        //Dimension frameSize = getSize();
        //setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
        setLocationRelativeTo(null);
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
        openItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOpen();
            }
        });

        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOpen();
            }
        });
        
    }
    private void exit(){
        int option = JOptionPane.showConfirmDialog(this,"Estas seguro de que quieres salir?",
        "Confirmar salida",JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
        
    }
    private void fileOpen(){
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad Abrir en construccion, Archivo seleccionado: "+ archivo.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar ();
        JMenu menuDesplegable = new JMenu("Menu");
        
        leave = new JMenuItem("Exit");
        leave.addActionListener(null);
        openItem = new JMenuItem("Abrir");
        openItem.addActionListener(null); 
        save = new JMenuItem("Salvar");

        newFile = new JMenuItem("Nuevo");

        menuDesplegable.add(newFile);
        menuDesplegable.addSeparator();
        menuDesplegable.add(openItem);
        menuDesplegable.add(save);
        menuDesplegable.addSeparator();
        menuDesplegable.add(leave);
        menu.add(menuDesplegable);
        setJMenuBar(menu);
    }
    public static void main(String args []){
        DMaxwellGUI max = new DMaxwellGUI();
        max.setVisible(true);

    }
    
}







