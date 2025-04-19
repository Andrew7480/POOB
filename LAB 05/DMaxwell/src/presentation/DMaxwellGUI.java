package presentation;
import java.io.File;
import javax.swing.*;
import domain.DMaxwell;
import domain.DMaxwellException;
import java.awt.*;
import java.awt.event.*;


public class DMaxwellGUI extends JFrame{
    private JMenuItem leave;
    private JMenuItem openItem;
    private JMenuItem save;
    private JMenuItem newFile;
    private JButton north;
    private JButton south1;
    private JButton west;
    private JButton east;
    //private JButton nada;
    private JButton coloor1;
    private JButton coloor2;
    private JButton newOne;
    private JButton reboot;
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JLabel informacion;
    private maxwell tablero;

    private DMaxwell domain;

    public DMaxwellGUI(){
        domain = new DMaxwell();
        prepareElements();
        prepareActions();
    }
    private void prepareElements(){
        setTitle("Maxwell Discreto");
        setLayout(new GridLayout(2,1));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/4, (int) screenSize.getHeight()/4);
        fileChooser = new JFileChooser();
        colorChooser = new JColorChooser();
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
        coloor1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                changeColor(1);
            }
        });
        coloor2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                changeColor(2);
            }
        });
        north.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                movement('u');
            }
        });
        south1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                movement('d');
            }
        });
        west.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                movement('l');
            }
        });
        east.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                movement('r');
            }
        });
        reboot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                resetDMaxwell();
            }
        });
        newOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                newDMaxwell();
            }
        });
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
        tablero = new maxwell(domain.container());
        add(tablero);//, BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JPanel panelBotones = new JPanel(new BorderLayout());
        //panelBotones.setSize(20);
        north = new JButton("↑");
        south1 = new JButton("↓");
        west = new JButton("←");
        east = new JButton("→");
        JButton nada = new JButton("(▀̿Ĺ̯▀̿ )");
        nada.setEnabled(false);    
        panelBotones.add(north, BorderLayout.NORTH);
        panelBotones.add(south1, BorderLayout.SOUTH);
        panelBotones.add(west, BorderLayout.WEST);
        panelBotones.add(east, BorderLayout.EAST);
        panelBotones.add(nada, BorderLayout.CENTER);
        south.add(panelBotones);

        
        JPanel panelInformacion = new JPanel(new GridLayout(4,1));
        informacion = new JLabel("Informacion:  Azules:" + domain.results()[0]+ "%  Rojas: "+  domain.results()[1]+ "% Total:" +  domain.results()[2]+ "% Perdidas:"+ domain.results()[3]+ "% ");
        JPanel botonesColor = new JPanel(new GridLayout(1,2));
        coloor1 = new JButton("Color Particulas 1");
        coloor2 = new JButton("Color Particulas 2");
        newOne = new JButton("Genera uno nuevo");
        reboot = new JButton("reset");
        panelInformacion.add(informacion);
        botonesColor.add(coloor1);
        botonesColor.add(coloor2);
        panelInformacion.add(botonesColor);
        panelInformacion.add(newOne);
        panelInformacion.add(reboot);
        south.add(panelInformacion);
        add(south);//,BorderLayout.SOUTH);

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

    private void changeColor(int numero){
        Color choice = colorChooser.showDialog(this,"Selecciona tu color.", Color.BLUE);
        if(numero == 1 ) tablero.setColor1(choice);
        if(numero == 2 ) tablero.setColor2(choice);
        refresh();
    }

    private void refresh(){
        tablero.refresh(domain.container());
        informacion.setText("Informacion:  Azules:" + domain.results()[0]+ "%  Rojas: "+  domain.results()[1]+ "% Total:" +  domain.results()[2]+ "% Perdidas:"+ domain.results()[3]+ "% ");
        repaint();
    }

    private void movement(char mov){
        try{
            domain.movement(mov);
            refresh();
        }
        catch(DMaxwellException e){
            JOptionPane.showMessageDialog(this, "No se puede hacer ese movimiento. ", "Informacion ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void newDMaxwell(){
        //JOptionPane.showInputDialog(null, "ingrese: ");
    }

    private void resetDMaxwell(){
        domain = new  DMaxwell();
        refresh();
    }

    public static void main(String args []){
        DMaxwellGUI max = new DMaxwellGUI();
        max.pack();
        max.setLocationRelativeTo(null);
        max.setVisible(true);

    }
    
}







