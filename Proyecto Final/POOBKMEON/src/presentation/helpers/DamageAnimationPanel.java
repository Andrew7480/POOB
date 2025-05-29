package presentation.helpers;

import javax.swing.*;
import java.awt.*;

public class DamageAnimationPanel extends JPanel {
    private JLabel damageLabel;
    private Timer timer;
    private int animationSteps = 30;
    private int deltaY = 2;
    private int deltaAlpha = 255 / animationSteps;
    private int startY = 0;
    private int step = 0;

    public DamageAnimationPanel() {
        setOpaque(false);
        setLayout(null);
    }

    /**
     * Muestra una animación de daño sobre este panel.
     * El número sube y se desvanece gradualmente.
     *
     * @param damage Daño a mostrar
     */
    public void showDamage(int damage) {
        if (damageLabel != null) {
            remove(damageLabel);
            damageLabel = null;
        }
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        // Centrar el label en el panel
        int w = getWidth();
        int h = getHeight();
        int labelWidth = 80;
        int labelHeight = 40;
        int x = (w - labelWidth) / 2;
        startY = (h - labelHeight) / 2;
        damageLabel = new JLabel("-" + damage, SwingConstants.CENTER);
        damageLabel.setFont(new Font("Arial", Font.BOLD, 28));
        damageLabel.setForeground(new Color(255, 0, 0, 255)); // Rojo, opaco
        damageLabel.setBounds(x, startY, labelWidth, labelHeight);
        damageLabel.setOpaque(false);
        add(damageLabel);
        repaint();
        step = 0;

        timer = new Timer(30, e -> {
            step++;
            int newY = startY - deltaY * step;
            int currentAlpha = 255 - deltaAlpha * step;
            if (currentAlpha <= 0) {
                remove(damageLabel);
                damageLabel = null;
                repaint();
                timer.stop();
            } else {
                damageLabel.setLocation(x, newY);
                damageLabel.setForeground(new Color(255, 0, 0, Math.max(0, currentAlpha)));
                repaint();
            }
        });
        timer.start();
    }
}