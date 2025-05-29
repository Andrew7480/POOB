package presentation.Selection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import presentation.POOBkemonGUI;
import presentation.ModesOfGame.ModePlayerVsMachine;

public class SelectionPokemonItemPlayer extends JPanel{
    private  String backgroundImage = "emerald";
    private POOBkemonGUI po;
    private JButton come;
    private JButton doneButton; 
    private ModePlayerVsMachine gameMode;

    private Selection selection;


    public SelectionPokemonItemPlayer(POOBkemonGUI pooBkemonGUI, ModePlayerVsMachine father){
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
        selection = new Selection(po, new Color(1,2,4,100));
        temp.add(selection);

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
                    if (selection.getPokemonChoosen().size()<1|| selection.getItemsChoosen().size()<1){
                        JOptionPane.showMessageDialog(SelectionPokemonItemPlayer.this, 
                            "Selecciona al menos 1 Pokémon para la batalla y dos pociones! ",
                            "Incompleta", JOptionPane.WARNING_MESSAGE);
                    return;
                    }

                   if(selection.getPokemonChoosen().size()> selection.MAX_POKEMONS|| selection.getItemsChoosen().size()> selection.MAX_POTIONS ){
                        JOptionPane.showMessageDialog(SelectionPokemonItemPlayer.this,
                            "Solo puedes seleccionar máximo " + selection.MAX_POKEMONS + " pokemones y " + selection.MAX_POTIONS + " pociones",
                            "Límite excedido", JOptionPane.WARNING_MESSAGE);
                        return;
                   }
                   gameMode.itemsEscogidos = selection.getItemsChoosen();
                   gameMode.movements.inicializate(selection.getColor(), selection.getPokemonChoosen());
                   
                   gameMode.changePanel("Movimientos");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SelectionPokemonItemPlayer.this, ex.getMessage());
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

    public void inicializate(Color color1){
        selection.setColor(color1);
    }
    

    public void reset(){ 
        System.out.println("resetea todo de la seleccion players");
        selection.reset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
