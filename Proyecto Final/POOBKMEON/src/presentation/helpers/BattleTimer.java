package presentation.helpers;

import javax.swing.Timer;
import javax.swing.JLabel;
import presentation.POOBkemonGUI;

public abstract class BattleTimer {
    protected Timer battleTimer;
    private POOBkemonGUI pooBkemonGUI;

    public BattleTimer(POOBkemonGUI poobkemonjGUi) {
        pooBkemonGUI = poobkemonjGUi;
    }

    public abstract void iniciarTemporizadorDeBatalla();

    public void detenerTemporizadorDeBatalla() {
        if (battleTimer != null) battleTimer.stop();
    }
}