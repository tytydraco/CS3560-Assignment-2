package gui.control;

import data.models.identity.User;
import gui.user.UserView;

import javax.swing.*;
import java.awt.*;

/**
 * The main control panel.
 */
public class ControlView extends JFrame {
    public ControlView() {
        setResizable(false);
        setSize(800, 500);

        SideViewPanel sideViewPanel = new SideViewPanel();
        add(sideViewPanel, BorderLayout.WEST);

        ManagePanel managePanel = new ManagePanel();
        managePanel.setOnRefreshListener(sideViewPanel::refresh);
        managePanel.setOnOpenUserViewListener(() -> {
            User selectedUser = sideViewPanel.getSelectedUser();

            // Ignore if no user is selected.
            if (selectedUser == null)
                return;

            UserView userView = new UserView(selectedUser);
            userView.present();
        });
        add(managePanel, BorderLayout.EAST);
    }

    public void present() {
        setVisible(true);
    }
}
