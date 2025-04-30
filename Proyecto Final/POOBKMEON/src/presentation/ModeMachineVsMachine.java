package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ModeMachineVsMachine extends JPanel {
    private String backgroundImage = "PZ4ODE";
    private JButton btnRegresar;
    private POOBkemonGUI po;

    public ModeMachineVsMachine(POOBkemonGUI newPo){
        po = newPo;
        setLayout(new BorderLayout());
        btnRegresar = new JButton("Volver");
        po.styleButton(btnRegresar);
        JPanel buttonPanel = po.invisiblePanelWithOpacity();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.add(btnRegresar);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnRegresar(){
        return btnRegresar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon back = new ImageIcon(getClass().getResource("/resources/"+ backgroundImage+".GIF"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
