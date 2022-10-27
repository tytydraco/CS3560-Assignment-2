import gui.ControlPanel;

/**
 * Launches the admin control panel.
 */
public class Driver {
    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.present();
    }
}