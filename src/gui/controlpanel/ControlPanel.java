package gui.controlpanel;

import gui.user.UserView;

import javax.swing.*;
import java.awt.*;

/**
 * The main control panel.
 */
public class ControlPanel {
    private final JFrame frame;

    public ControlPanel() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 500);

        SideViewPanel sideViewPanel = new SideViewPanel();
        frame.add(sideViewPanel, BorderLayout.WEST);

        ManagePanel managePanel = new ManagePanel();
        managePanel.setOnOpenUserViewListener(() -> {
            // TODO: pass selected user
            UserView userView = new UserView(null);
            userView.present();
        });
        frame.add(managePanel, BorderLayout.EAST);
    }

    public void present() {
        frame.setVisible(true);
    }
}
