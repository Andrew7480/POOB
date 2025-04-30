package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ModePlayerVSPlayer extends JPanel {
    private String backgroundImage = "443244fb22ae8c0c7e530a4ea33af69a";
    private JButton btnRegresarNormal;
    private JButton btnRegresarSurvival;
    private JButton continuar;
    private POOBkemonGUI po;

    public ModePlayerVSPlayer(POOBkemonGUI newPo, boolean normal){
        po = newPo;
        setLayout(new BorderLayout());
        JButton regresar = new JButton("Back");
        if(normal){
            btnRegresarNormal = regresar;
        }
        else{
            btnRegresarSurvival = regresar;
        }
        continuar = new JButton("Continue");
        po.styleButton(regresar);
        po.styleButton(continuar);

        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(continuar);
        buttonPanel.add(regresar);
        add(buttonPanel,BorderLayout.SOUTH);

        JPanel s = new JPanel();
        s.setOpaque(false);
        s.setLayout(new GridLayout(1,2));
        JPanel player1 = playerPanel();
        JPanel player2 = playerPanel();
        s.add(player1);
        s.add(player2);
        add(s,BorderLayout.NORTH);

    }

    private JPanel playerPanel(){
        JPanel player1 = new JPanel();
        player1.setOpaque(false);
        player1.setLayout(new BoxLayout(player1, BoxLayout.Y_AXIS));
        JLabel textPlayerOne = new JLabel("Select");
        player1.add(textPlayerOne);
        return player1;
    }


    public JButton getBtnRegresarNormal(){
        return btnRegresarNormal;
    }
    public JButton getButtonContinuar(){
        return continuar;
    }
    public JButton getButtonRegresarSurvival(){
        return btnRegresarSurvival;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".JPG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
