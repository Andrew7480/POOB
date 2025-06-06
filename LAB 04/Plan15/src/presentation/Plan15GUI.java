package presentation; 
 
import domain.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @version ECI 2025
 */
public class Plan15GUI extends JFrame{


    private static final Dimension PREFERRED_DIMENSION =
                         new Dimension(700,700);

    private Plan15 plan;

    /*List*/
    private JButton buttonList;
    private JButton buttonRestartList;
    private JTextArea textDetails;
    
    /*Add*/
    private JTextField code;
    private JTextField name;   
    private JTextField credits;
    private JTextField inPerson;
    private JTextArea  basics;
    private JButton buttonAdd;
    private JButton buttonRestartAdd;
   
    /*Search*/
    private JTextField textSearch;
    private JTextArea textResults;
    
    
    private Plan15GUI(){
        try{plan=new Plan15();}
        catch(Plan15Exception e){Log.record(e);
            JOptionPane.showMessageDialog(null, "Perdón usuario, lo sentimos. Ocurrio un ⚠️ Error: " + e.getMessage()
        , "Error en el sistema", JOptionPane.ERROR_MESSAGE);}
        prepareElements();
        prepareActions();
    }


    private void prepareElements(){
        setTitle("Plan 15");
        code = new JTextField(50);
        name = new JTextField(50);
        credits = new JTextField(50);
        inPerson = new JTextField(50);
        basics = new JTextArea(10, 50);
        basics.setLineWrap(true);
        basics.setWrapStyleWord(true);
        
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareAreaList());
        etiquetas.add("Adicionar",  prepareAreaAdd());
        etiquetas.add("Buscar", prepareSearchArea());
        getContentPane().add(etiquetas);
        setSize(PREFERRED_DIMENSION);
        
    }


    /**
     * Prepare list area
     * @return 
     */
    private JPanel prepareAreaList(){

        textDetails = new JTextArea(10, 50);
        textDetails.setEditable(false);
        textDetails.setLineWrap(true);
        textDetails.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textDetails,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        buttonList = new JButton("Listar");
        buttonRestartList = new JButton("Limpiar");
        botones.add(buttonList);
        botones.add(buttonRestartList);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Prepare adiction area
     * @return adiction area
     */
    private JPanel prepareAreaAdd(){

        JPanel fields = new JPanel(new GridLayout(9,1));
        fields.add(new JLabel("Sigla"));
        fields.add(code);
        fields.add(new JLabel("Nombre"));
        fields.add(name);
        fields.add(new JLabel("Creditos o porcentaje"));
        fields.add(credits);        
        fields.add(new JLabel("Horas presenciales (solo para cursos)"));
        fields.add(inPerson); 
        fields.add(new JLabel("Cursos (solo para nucleos)"));
       
        JPanel textDetailsPanel = new JPanel();
        textDetailsPanel.setLayout(new BorderLayout());
        textDetailsPanel.add(fields, BorderLayout.NORTH);
        textDetailsPanel.add(basics, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        buttonAdd = new JButton("Adicionar");
        buttonRestartAdd = new JButton("Limpiar");

        botones.add(buttonAdd);
        botones.add(buttonRestartAdd);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textDetailsPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }


   /**
     * 
     * @return 
     */
    private JPanel prepareSearchArea(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        textSearch = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(textSearch);
        
        textResults = new JTextArea(10,50);
        textResults.setEditable(false);
        textResults.setLineWrap(true);
        textResults.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(textResults,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonListea = new JPanel();
        buttonListea.setLayout(new BorderLayout());
        buttonListea.add(busquedaArea, BorderLayout.NORTH);
        buttonListea.add(scrollArea, BorderLayout.CENTER);

        return buttonListea;
    }


    private void prepareActions(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*List*/
        buttonList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionList();
            }
        });

        buttonRestartList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textDetails.setText("");
            }
        });
        
        /*Add*/
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionAdd();                    
            }
        });
        
        buttonRestartAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                code.setText("");
                name.setText("");
                credits.setText("");
                inPerson.setText("");
                basics.setText("");
            }
        });
        
        /*Search*/
        textSearch.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                actionSearch();
            }
           
            public void insertUpdate(DocumentEvent ev){
                actionSearch();
            }
            
            public void removeUpdate(DocumentEvent ev){
                actionSearch();
            }
        });
    }    

    
    private void actionList(){
        textDetails.setText(plan.toString());
    }
    
    private void  actionAdd(){
        if(basics.getText().trim().equals("") && code.getText().trim().equals("") 
        && name.getText().trim().equals("")&&credits.getText().trim().equals("")
        && inPerson.getText().trim().equals(""));
        else if (basics.getText().trim().equals("")){
            try{plan.addCourse(code.getText(),name.getText(),credits.getText(),inPerson.getText());
            }
            catch(Plan15Exception e){
                JOptionPane.showMessageDialog(null, "Usuario,  " + e.getMessage()
        , "Error en el sistema", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());}
        }else{ 
            try{plan.addCore(code.getText(),name.getText(),credits.getText(),basics.getText());}
            catch(Plan15Exception e)
            {JOptionPane.showMessageDialog(null, "Usuario,  " + e.getMessage()
            , "Error en el sistema", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());}
        }
    }

    private void actionSearch(){
        String patronBusqueda=textSearch.getText();
        String answer = "";
        try{
            if (patronBusqueda.equals("courses")){
                answer = plan.search();
            }
            else if(patronBusqueda.length() > 0) {
                answer = plan.search(patronBusqueda);
            }
            if(answer.length()<13) answer = "No hay coincidencias. ";
            textResults.setText(answer);}
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Perdón usuario, lo sentimos. Ocurrio un ⚠️ Error: " + e.getMessage()
            , "Error en el sistema", JOptionPane.ERROR_MESSAGE);
            Log.record(e);
        } 
    } 
    
   public static void main(String args[]){
    try{
       Plan15GUI gui=new Plan15GUI();
       gui.setVisible(true);}
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Perdón usuario, lo sentimos. Ocurrio un ⚠️ Error: " + e.getMessage()
        , "Error en el sistema", JOptionPane.ERROR_MESSAGE);
        Log.record(e);

        }
   }    
}
