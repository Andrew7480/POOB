package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ModesOfGamePanel extends JPanel{
    private String backgroundImage = "bb8jr133l9sa1";
    private JButton btnRegresar;
    private JButton modeNormal;
    private JButton modeSurvival;
    private POOBkemonGUI po;

    public ModesOfGamePanel(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        btnRegresar = new JButton("Volver al Menú Principal");
        po.styleButton(btnRegresar);
        JPanel mN = modesOfGames();
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(mN);
        add(centro,BorderLayout.CENTER);
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresar);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public JButton getButtonRegresar(){
        return btnRegresar;
    }
    public JButton getButtonNormal(){
        return modeNormal;
    }
    public JButton getButtonSurvival(){
        return modeSurvival;
    }

    private JPanel modesOfGames(){
        modeNormal = new JButton("Mode Normal");
        modeSurvival = new JButton("Mode Survival");

        po.styleButton(modeNormal);
        po.styleButton(modeSurvival);

        JPanel options = po.invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
    
        modeNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
        modeSurvival.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        options.add(Box.createVerticalStrut(20));
        options.add(modeNormal);
        options.add(Box.createVerticalStrut(15));
        options.add(modeSurvival);

        return options;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
