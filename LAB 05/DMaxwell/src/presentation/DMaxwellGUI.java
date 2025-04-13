package presentation;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;



import java.util.ArrayList;
import java.awt.*;
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
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/4, (int) screenSize.getHeight()/4);
        //Dimension frameSize = getSize();
        //setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
        setLocationRelativeTo(null);
        fileChooser = new JFileChooser();
        prepareElementsMenu();
        prepareElementsBoard();
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
            public void actionPerformed(ActionEvent e) {
                fileOpen();
            }
        });

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                saveOpen();
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
    private void saveOpen(){
        int choice = fileChooser.showSaveDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archive = fileChooser.getCurrentDirectory();
            JOptionPane.showMessageDialog(this, "Funcionalidad Guardar en construccion, Lugar donde se guarda: "+ archive.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
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

    private void prepareElementsBoard(){
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        JPanel panelBotones = new JPanel(new BorderLayout());
        //panelBotones.setSize(20);
        JButton north = new JButton("North");
        JButton south1 = new JButton("South");
        JButton west = new JButton("West");
        JButton east = new JButton("East");
        panelBotones.add(north, BorderLayout.NORTH);
        panelBotones.add(south1, BorderLayout.SOUTH);
        panelBotones.add(west, BorderLayout.WEST);
        panelBotones.add(east, BorderLayout.EAST);
        south.add(panelBotones);

        
        JPanel panelInformacion = new JPanel(new GridLayout(3,1));
        JLabel informacion = new JLabel("Informacion");
        JButton coloor = new JButton("Color");
        JButton newOne = new JButton("Genera uno nuevo");
        panelInformacion.add(informacion);
        panelInformacion.add(coloor);
        panelInformacion.add(newOne);
        south.add(panelInformacion);
        add(south,BorderLayout.SOUTH);

        
        JPanel centro = new JPanel(new GridLayout(1,3));
        centro.add(new PanelIzquierdo());
        centro.add(new PanelCentral());
        centro.add(new PanelDerecho());
        centro.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        add(centro, BorderLayout.CENTER);
    }

    class PanelIzquierdo extends JPanel {
    public PanelIzquierdo() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(20, 20, 20, 20);
        g.setColor(Color.BLUE);
        g.fillRect(70, 50, 20, 20);
        g.setColor(Color.RED);
        g.fillRect(30, 70, 20, 20);
    }
}

    class PanelDerecho extends JPanel {
        public PanelDerecho() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(30, 20, 20, 20);
            g.setColor(Color.BLUE);
            g.fillRect(100, 20, 20, 20);
            g.setColor(Color.BLUE);
            g.fillRect(50, 80, 20, 20);
            g.setColor(Color.RED);
            g.fillRect(110, 50, 20, 20);
        }
    }


    class PanelCentral extends JPanel {
        public PanelCentral() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(50, 0, 20, getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(50, 50, 20, 20);
        }
    }



    public static void main(String args []){
        DMaxwellGUI max = new DMaxwellGUI();
        max.setVisible(true);

    }
    
}







