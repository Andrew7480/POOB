package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ModesOfGamePanelNormal extends JPanel {
    private String backgroundImage = "trainers";
    private JButton btnRegresar;
    private JButton playerVsMachine;
    private JButton machineVsMachine;
    private JButton playerVsPlayer;
    private POOBkemonGUI po;

    public ModesOfGamePanelNormal(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        btnRegresar = new JButton("Modos De Juego");
        po.styleButton(btnRegresar);
        JPanel mN = modeNormal();
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
    
    public JButton getButtonMvsM(){
        return machineVsMachine;
    }

    public JButton getButtonPvsP(){
        return playerVsPlayer;
    }
    public JButton getButtonPvsM(){
        return playerVsMachine;
    }


    private JPanel modeNormal() {

        playerVsPlayer = new JButton("Player vs Player");
        playerVsMachine = new JButton("Player VS Machine");
        machineVsMachine = new JButton("Machine vs Machine");
    
        po.styleButton(playerVsMachine);
        po.styleButton(machineVsMachine);
        po.styleButton(playerVsPlayer);
    
        JPanel options = po.invisiblePanelWithoutOpacity();
        options.setOpaque(false);
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
    
        playerVsMachine.setAlignmentX(Component.CENTER_ALIGNMENT);
        machineVsMachine.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerVsPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        options.add(Box.createVerticalStrut(20));
        options.add(playerVsPlayer);
        options.add(Box.createVerticalStrut(15));
        options.add(playerVsMachine);
        options.add(Box.createVerticalStrut(15));
        options.add(machineVsMachine);
    
        return options;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
