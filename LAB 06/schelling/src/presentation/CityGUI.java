package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CityGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private JFileChooser fileChooser;
    private PhotoCity photo;
    private JMenuItem leave;
    private JMenuItem openItem;
    private JMenuItem save;
    private JMenuItem newFile;

    private JMenuItem importar;
    private JMenuItem exportar;
    private City theCity;
   
    //Nuevo, Abrir, Guardar como, Importar, Exportar como, Salir

    private CityGUI() {
        theCity=new City();
        SIZE=theCity.getSize();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Schelling City");
        photo=new PhotoCity(this);
        ticTacButton=new JButton("Tic-tac");
        fileChooser = new JFileChooser();
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
        prepareElementsMenu();
         
    }

    private void prepareActions(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                optionExit();
            }
        });
       
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });
        prepareActionsMenu();

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
        save = new JMenuItem("Guardar como");
        newFile = new JMenuItem("Nuevo");
        importar= new JMenuItem("Importar");
        exportar = new JMenuItem("Exportar como");
        menuDesplegable.add(newFile);
        menuDesplegable.addSeparator();
        menuDesplegable.add(openItem);
        menuDesplegable.add(save);
        menuDesplegable.addSeparator();
        menuDesplegable.add(importar);
        menuDesplegable.add(exportar);
        menuDesplegable.addSeparator();
        menuDesplegable.add(leave);
        menu.add(menuDesplegable);
        setJMenuBar(menu);
    }

    private void prepareActionsMenu(){
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionExit();
            }
        });
        openItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionOpen();
            }
        });

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionSave();
            }
        });
        newFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionNew();
            }
        });


        importar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionImport();
            }
        });


        exportar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionExport();
            }
        });
    }

    private void optionImport(){
        try {
            int choice = fileChooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            theCity = theCity.importar01(archivo);
            photo.repaint();
        }
        } catch (CityException e) {
            System.out.println(e.getMessage());
        }
    }
    private void optionExport(){
        try{
            int choice = fileChooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            theCity.export(archivo);
            }
        }catch(CityException e){
            System.out.println(e.getMessage());        
        }
    }
    private void ticTacButtonAction() {
        theCity.ticTac();
        photo.repaint();
    }

    public City gettheCity(){
        return theCity;
    }
    
    public static void main(String[] args) {
        CityGUI cg=new CityGUI();
        cg.setVisible(true);
    }
    //Option new creates a new city
    private void optionNew(){
        theCity = new City();
        photo.repaint();

    }
    /*
     * Do the action of close the window without any confirmation
     */
    private void optionExit(){
        /*
        int option = JOptionPane.showConfirmDialog(this,"Estas seguro de que quieres salir?",
        "Confirmar salida",JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
        */
        dispose();
        System.exit(0);
        
    }
    /*
     * Do the action of file Open
     */
    private void optionOpen(){
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            try{
                theCity = theCity.open(archivo);
                photo.repaint();
            }
            catch (CityException e){
                //JOptionPane.showMessageDialog(this, "Funcionalidad Abrir en construccion, Archivo seleccionado: "+ archivo.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }
    /*
     * Do the action of save open
     */
    private void optionSave(){
        int choice = fileChooser.showSaveDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION){
            File archive = fileChooser.getSelectedFile();
            try{
                theCity.save(archive);
            }
            catch(CityException e){
                //JOptionPane.showMessageDialog(this, "Funcionalidad Guardar en construccion, Lugar donde se guarda: "+ archive.getName(), "Informacion ", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }
}

class PhotoCity extends JPanel{
    private CityGUI gui;

    public PhotoCity(CityGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    }


    public void paintComponent(Graphics g){
        City theCity=gui.gettheCity();
        super.paintComponent(g);
         
        for (int c=0;c<=theCity.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theCity.getSize()*gui.SIDE);
        }
        for (int f=0;f<=theCity.getSize();f++){
            g.drawLine(0,f*gui.SIDE,theCity.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<theCity.getSize();f++){
            for(int c=0;c<theCity.getSize();c++){
                if (theCity.getItem(f,c)!=null){
                    g.setColor(theCity.getItem(f,c).getColor());
                    if (theCity.getItem(f,c).shape()==Item.SQUARE){                  
                        if (theCity.getItem(f,c).isActive()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }else{
                            g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);    
                        }
                    }else {
                        if (theCity.getItem(f,c).isActive()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } else {
                            g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        }
                    }
                    if (theCity.getItem(f,c).isAgent()){
                        g.setColor(Color.red);
                        if (((Agent)theCity.getItem(f,c)).isHappy()){
                            g.drawString("u",gui.SIDE*c+6,gui.SIDE*f+15);
                        } else if (((Agent)theCity.getItem(f,c)).isIndifferent()){ 
                            g.drawString("_",gui.SIDE*c+7,gui.SIDE*f+10);
                        } else if (((Agent)theCity.getItem(f,c)).isDissatisfied()){
                            g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                        }
                    }    
                }
            }
        }
    }
}