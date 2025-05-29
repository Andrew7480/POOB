package presentation.Selection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import presentation.ModesOfGame.ModePlayerVSPlayer;
import presentation.POOBkemonGUI;
import presentation.Selection.Selection;

public class SelectionPokemonItemPlayers extends JPanel{
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private ModePlayerVSPlayer gameMode;

    private Selection selectionOne;
    private Selection selectionTwo;


    public SelectionPokemonItemPlayers(POOBkemonGUI pooBkemonGUI, ModePlayerVSPlayer father){
        gameMode = father;
        po = pooBkemonGUI;
        prepareElements();
        prepareActions();
    }
    

    private void prepareElements(){
        setLayout(new BorderLayout());
        setOpaque(false);
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.setOpaque(false);
        selectionOne = new Selection(po, new Color(1,2,4,100));
        selectionTwo = new Selection(po, new Color(30,100,30,100));
        temp.add(selectionOne);
        temp.add(selectionTwo);
        add(temp, BorderLayout.CENTER);
        JPanel down = new JPanel(new BorderLayout());
        down.setOpaque(false);
        doneButton = new JButton ("Done!");
        come = new JButton("Back..");
        po.styleButton(doneButton);
        po.styleButton(come);
        JPanel booton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        booton.setOpaque(false);
        booton.add(come);
        booton.add(doneButton);
        down.add(booton,BorderLayout.SOUTH);
        add(down, BorderLayout.SOUTH);
    }

    private void prepareActions(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selectionOne.getPokemonChoosen().size()<1|| selectionOne.getItemsChoosen().size()<1 ||selectionTwo.getPokemonChoosen().size()<1|| selectionTwo.getItemsChoosen().size()<1 ){
                        JOptionPane.showMessageDialog(SelectionPokemonItemPlayers.this, 
                            "Selecciona al menos 1 Pokémon para la batalla y dos pociones! ",
                            "Incompleta", JOptionPane.WARNING_MESSAGE);
                    return;
                    }

                   if(selectionOne.getPokemonChoosen().size()> selectionOne.MAX_POKEMONS|| selectionOne.getItemsChoosen().size()> selectionOne.MAX_POTIONS 
                   ||selectionTwo.getPokemonChoosen().size()> selectionOne.MAX_POKEMONS|| selectionTwo.getItemsChoosen().size()> selectionOne.MAX_POTIONS ){
                        JOptionPane.showMessageDialog(SelectionPokemonItemPlayers.this,
                            "Solo puedes seleccionar máximo " + selectionOne.MAX_POKEMONS + " pokemones y " + selectionOne.MAX_POTIONS + " pociones",
                            "Límite excedido", JOptionPane.WARNING_MESSAGE);
                        return;
                   }
                   gameMode.firstItems = selectionOne.getItemsChoosen();
                   gameMode.secondItems = selectionTwo.getItemsChoosen();
                   gameMode.movements.inicializate(selectionOne.getColor(), selectionOne.getPokemonChoosen(), selectionTwo.getColor(), selectionTwo.getPokemonChoosen());
                   
                   gameMode.changePanel("Movimientos");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SelectionPokemonItemPlayers.this, ex.getMessage());
                }
            }
        });

        come.addActionListener(e -> {
            reset();
            gameMode.changePanel("Datos");
        });
    }

    public JButton getButtonBack(){
        return come;
    }

    public JButton getNextBJButton(){
        return doneButton;
    }

    public void inicializate(Color colorOne, Color colorTwo){
        selectionOne.setColor(colorOne);
        selectionTwo.setColor(colorTwo);
    }

    public void reset(){ 
        System.out.println("resetea todo de la seleccion players");
        selectionOne.reset();
        selectionTwo.reset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
