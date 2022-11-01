package gui.controlpanel;

import data.local.DataManager;
import data.models.GroupModel;
import data.models.UserModel;

import javax.swing.*;
import java.awt.*;

/**
 * Presents the configuration view.
 */
public class ManagePanel extends JPanel {
    private final DataManager dataManager = DataManager.getInstance();
    private Runnable onRefreshListener;
    private Runnable onOpenUserViewListener;

    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Manage"));

        JButton addUserButton = new JButton("Add user");
        addUserButton.addActionListener(actionEvent -> {
            JTextField userIdField = new JTextField();
            JTextField groupIdField = new JTextField();
            Object[] request = {
                    "User ID", userIdField,
                    "Group ID", groupIdField,
            };

            JOptionPane.showConfirmDialog(null, request);

            addUser(userIdField.getText(), groupIdField.getText());

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addUserButton);

        JButton openUserViewButton = new JButton("Open user view");
        openUserViewButton.addActionListener(actionEvent -> {
            if (onOpenUserViewListener != null)
                onOpenUserViewListener.run();
        });
        add(openUserViewButton);
    }

    private void addUser(String userId, String groupId) {
        UserModel user = new UserModel(userId);
        GroupModel group;

        if (groupId.isEmpty()) {
            group = dataManager.getRootGroup();
        } else {
            group = dataManager.findGroupById(groupId);
        }

        if (group != null) {
            group.addUser(user);
        }
    }

    public void setOnRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnOpenUserViewListener(Runnable onOpenUserViewListener) {
        this.onOpenUserViewListener = onOpenUserViewListener;
    }
}
