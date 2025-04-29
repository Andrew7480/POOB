package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ModesOfGamePanelSurvival extends JPanel {
    private String backgroundImage = "download";
    private JButton btnRegresar;
    private JButton playerVsPlayerSurvival;
    private POOBkemonGUI po;

    public ModesOfGamePanelSurvival(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        btnRegresar = new JButton("Volver a los Modos De Juego");
        po.styleButton(btnRegresar);
        JPanel mN = modeSurvival();
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(mN);
        add(centro,BorderLayout.CENTER);
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresar);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getButtonRegresar(){
        return btnRegresar;
    }
    public JButton getButtonPvsP(){
        return playerVsPlayerSurvival;
    }

    private JPanel modeSurvival(){

        playerVsPlayerSurvival = new JButton("Player vs Player");
        po.styleButton(playerVsPlayerSurvival);
        JPanel options = po.invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        playerVsPlayerSurvival.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(playerVsPlayerSurvival);
    
        return options;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
