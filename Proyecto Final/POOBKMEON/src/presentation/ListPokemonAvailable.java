package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ListPokemonAvailable extends JPanel{
    private String backgroundImage = "download";
    private JButton come;
    private POOBkemonGUI po;
    public ListPokemonAvailable(POOBkemonGUI newPo){
        po = newPo;
        come = new JButton("Back");
        po.styleButton(come);
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
        buttonPanel.add(come);
        add(buttonPanel,BorderLayout.SOUTH);
    }


    public JButton getButtonBack(){
        return come;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}