package presentation;
import java.io.File;
import javax.swing.*;
import domain.DMaxwell;
import domain.DMaxwellException;
import java.awt.*;
import java.awt.event.*;


/**
 * DMaxwellGUI
 * 
 * @author  Tulio Riaño and Andrés Cardozo
 * @version 95%
 */


public class DMaxwellGUI extends JFrame{
    private JMenuItem leave;
    private JMenuItem openItem;
    private JMenuItem save;
    private JMenuItem newFile;
    private JButton north;
    private JButton south1;
    private JButton west;
    private JButton east;
    private JButton coloor1;
    private JButton coloor2;
    private JButton newOne;
    private JButton reboot;
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JLabel informacion;
    private Maxwell tablero;
    private DMaxwell domain;
    /**
     * Constructor of DMaxwellGUI
     */
    public DMaxwellGUI(){
        domain = new DMaxwell();
        prepareElements();
        prepareActions();
    }
    /*
     * prepare elements of the innermost layer
     */
    private void prepareElements(){
        setTitle("Maxwell Discreto");
        setLayout(new GridLayout(2,1));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/4, (int) screenSize.getHeight()/4);
        fileChooser = new JFileChooser();
        colorChooser = new JColorChooser();
        prepareElementsMenu();
        prepareElementsBoard();
        setFocusable(true); //par teclas
    }
    /**
     * do the actions by means of listeners (actions that the user do with the GUI)
     * 
     */
    private void prepareActions(){
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
        newFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                newDMaxwell();
            }
        });
        newOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                newDMaxwell();
            }
        });
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar() == 'w') movement('u');
                if (e.getKeyChar() == 's') movement('d');
                if (e.getKeyChar() == 'd') movement('r');
                if (e.getKeyChar() == 'a') movement('l');
            }
        });

    }

    /*
     * prepare the elements of the menu of GUI
     */
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
    /*
     * prepare elements of the board 
     * buttons and jpanel
     */
    private void prepareElementsBoard(){
        tablero = new Maxwell(domain.container());
        add(tablero);
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));

        JPanel panelBotones = new JPanel(new BorderLayout());
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
        informacion = new JLabel("Azules:" + domain.results()[0]+ "%  Rojas: "+  domain.results()[1]+ "% Total:" +  domain.results()[2]+ "% Perdidas:"+ domain.results()[3]+ "% ");
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
        add(south);
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
     * Do the action of file Open
     */
    private void fileOpen(){
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad Abrir en construccion, Archivo seleccionado: "+ archivo.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
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
    /*
     * Do the action of change color of the particles
     */
    private void changeColor(int numero){
        Color choice = colorChooser.showDialog(this,"Selecciona tu color.", Color.BLUE);
        if(numero == 1 ) tablero.setColor1(choice);
        if(numero == 2 ) tablero.setColor2(choice);
        refresh();
    }
    /*
     * do the refresh after every move or when resets the board
     */
    private void refresh(){
        tablero.refresh(domain.container());
        informacion.setText("Azules:" + domain.results()[0]+ "%  Rojas: "+  domain.results()[1]+ "% Total:" +  domain.results()[2]+ "% Perdidas:"+ domain.results()[3]+ "% ");
        if(domain.results()[2] == 100){ 
            JOptionPane.showMessageDialog(this, "Ganaste!", "Informacion ", JOptionPane.INFORMATION_MESSAGE); 
            resetDMaxwell();
        }
        repaint();
    }
    /*
     * do the movement, calling the domain
     */
    private void movement(char mov){
        try{
            domain.movement(mov);
            refresh();
        }
        catch(DMaxwellException e){
            JOptionPane.showMessageDialog(this, "No se puede hacer ese movimiento. ", "Informacion ", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /*
     * get the new parameters for the new board of DMaxwell
     */
    private void newDMaxwell(){        
        JPanel entradaDatos = new JPanel();
        JLabel textH = new JLabel("ingrese h: ");
        JLabel textW = new JLabel("ingrese w: ");
        JLabel textR = new JLabel("ingrese rojas: ");
        JLabel textB = new JLabel("ingrese azules: ");
        JLabel textO = new JLabel("ingrese holes: ");
        JTextField newH = new JTextField();
        JTextField newW = new JTextField();
        JTextField newR = new JTextField();
        JTextField newB = new JTextField();
        JTextField newO = new JTextField();
        entradaDatos.setLayout(new GridLayout(5,2));
        entradaDatos.add(textH);
        entradaDatos.add(newH);
        entradaDatos.add(textW);
        entradaDatos.add(newW);
        entradaDatos.add(textR);
        entradaDatos.add(newR);
        entradaDatos.add(textB);
        entradaDatos.add(newB);
        entradaDatos.add(textO);
        entradaDatos.add(newO);
        int resultado = JOptionPane.showConfirmDialog(null,entradaDatos,
            "Ingrese los datos",JOptionPane.OK_CANCEL_OPTION);

        if (resultado == 0){
            try{
                int h = esSoloNumeros( newH.getText());
                int w = esSoloNumeros(newW.getText());
                int r = esSoloNumeros(newR.getText());
                int b = esSoloNumeros(newB.getText());
                int o = esSoloNumeros(newO.getText());

                domain = new DMaxwell(h,2*w,r,b,o);
                remove(tablero);
                tablero = new Maxwell(h,w,domain.container());
                add(tablero,0);
                refresh();
            }
            catch (DMaxwellException e){
                JOptionPane.showMessageDialog(this, "No se puede esta configuracion. ", "Informacion ", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e){
                System.out.println("No se puede. " + e.getMessage());
            }
        }
    }
    
    

    /***
     * verify if the string includes only integers
     * @param texto
     * @return 
     * @throws Exception
     */

    private static int esSoloNumeros(String texto) throws Exception{
        if (texto.matches("\\d+")) {
            return Integer.parseInt(texto);
        } else {
            throw new Exception("El valor ingresado no es un número válido.");
        }
    }
    /*
     * Resets DMaxwell
     */
    private void resetDMaxwell(){
        domain = new  DMaxwell();
        remove(tablero);
        tablero = new Maxwell(domain.container());
        tablero.reset();
        add(tablero,0);
        refresh();
    }
    
    public static void main(String args []){
        DMaxwellGUI max = new DMaxwellGUI();
        max.pack();
        max.setLocationRelativeTo(null);
        max.setVisible(true);

    }
    
}







