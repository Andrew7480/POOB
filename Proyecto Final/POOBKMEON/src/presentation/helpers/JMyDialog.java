package presentation.helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JMyDialog extends JDialog {

    public static final Color BG_COLOR = new Color(30, 30, 180, 230);
    public static final Color BORDER_COLOR = new Color(40, 100, 150);
    public static final Color BUTTON_COLOR = new Color(70, 130, 180);
    public static final Color BUTTON_HOVER = new Color(100, 160, 210);
    public static final Color BUTTON_PRESSED = new Color(50, 110, 160);

    private Font customFont;

    public JMyDialog(JFrame owner, String title, String message, int messageType) {
        super(owner, title, true);
        setUndecorated(true);
        setSize(400, 180);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        setBackground(new Color(0,0,0,0));
        loadFont();

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(BG_COLOR);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3, true));
        mainPanel.setOpaque(false);

        JLabel iconLabel = new JLabel();
        if (messageType == JOptionPane.ERROR_MESSAGE) {
            iconLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        } else if (messageType == JOptionPane.WARNING_MESSAGE) {
            iconLabel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        } else if (messageType == JOptionPane.INFORMATION_MESSAGE) {
            iconLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        } // Puedes agregar más tipos si quieres

        String fontName = (customFont != null) ? customFont.getFontName() : "Arial";
        JLabel messageLabel = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        messageLabel.setFont(new Font(fontName, Font.PLAIN, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(iconLabel, BorderLayout.WEST);
        center.add(messageLabel, BorderLayout.CENTER);

        mainPanel.add(center, BorderLayout.CENTER);

        // Botón OK
        JButton ok = createStyledButton("OK");
        ok.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(ok);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont((customFont != null) ? customFont.deriveFont(12f) : new Font("Arial", Font.BOLD, 12));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setPreferredSize(new Dimension(130, 35));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(BUTTON_PRESSED);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
        });

        return button;
    }

    private void loadFont() {
        try {
            File fontFile = new File("src/font/PressStart2P-Regular.ttf");
            if (fontFile.exists()) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                customFont = font.deriveFont(14f);
            }
        } catch (Exception e) {
            customFont = null; 
        }
    }

    public static void showMessage(Component parent, String title, String message, int messageType) {
        JFrame frame = (parent instanceof JFrame) ? (JFrame) parent : null;
        JMyDialog dialog = new JMyDialog(frame, title, message, messageType);
        dialog.setVisible(true);
    }

    public static boolean showConfirm(Component parent, String title, String message) {
        JFrame frame = (parent instanceof JFrame) ? (JFrame) parent : null;
        final boolean[] confirmed = { false };
        JMyDialog dialog = new JMyDialog(frame, title, message, JOptionPane.QUESTION_MESSAGE);

        JButton ok = dialog.createStyledButton("OK");
        JButton cancel = dialog.createStyledButton("Cancelar");
        ok.addActionListener(e -> {
            confirmed[0] = true;
            dialog.dispose();
        });
        cancel.addActionListener(e -> {
            confirmed[0] = false;
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        ((JPanel)dialog.getContentPane().getComponent(0)).add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setVisible(true);

        return confirmed[0];
    }
}