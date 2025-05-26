package presentation.helpers;

import javax.swing.Timer;
import javax.swing.JLabel;
import presentation.POOBkemonGUI;

public class BattleTimer {
    private Timer battleTimer;
    private POOBkemonGUI pooBkemonGUI;
    private JLabel tiempoLabel;

    public BattleTimer(POOBkemonGUI poobkemonjGUi, JLabel label) {
        this.pooBkemonGUI = poobkemonjGUi;
        this.tiempoLabel = label;
    }

    public void iniciarTemporizadorDeBatalla() {
        battleTimer = new Timer(1000, e -> {
            pooBkemonGUI.domain.reduceTimeBattle(); 
            tiempoLabel.setText("" + pooBkemonGUI.domain.getTurnTimer());
        });
        battleTimer.start();
    }

    public void detenerTemporizadorDeBatalla() {
        if (battleTimer != null) battleTimer.stop();
    }
}