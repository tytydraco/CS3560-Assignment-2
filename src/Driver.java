import gui.controlpanel.ControlView;

/**
 * Launches the admin control panel.
 */
public class Driver {
    public static void main(String[] args) {
        ControlView controlPanel = new ControlView();
        controlPanel.present();
    }
}