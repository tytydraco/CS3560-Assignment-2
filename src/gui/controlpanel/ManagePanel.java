package gui.controlpanel;

import data.local.DataManager;
import data.models.identity.Group;
import data.models.identity.User;

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

            JOptionPane.showConfirmDialog(null, request, "Add User", JOptionPane.OK_CANCEL_OPTION);

            addUser(userIdField.getText(), groupIdField.getText());

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addUserButton);

        JButton addGroupButton = new JButton("Add group");
        addGroupButton.addActionListener(actionEvent -> {
            JTextField groupIdField = new JTextField();
            JTextField parentGroupIdField = new JTextField();
            Object[] request = {
                    "Group ID", groupIdField,
                    "Parent Group ID", parentGroupIdField,
            };

            JOptionPane.showConfirmDialog(null, request, "Add Group", JOptionPane.OK_CANCEL_OPTION);

            addGroup(groupIdField.getText(), parentGroupIdField.getText());

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addGroupButton);

        JButton openUserViewButton = new JButton("Open user view");
        openUserViewButton.addActionListener(actionEvent -> {
            if (onOpenUserViewListener != null)
                onOpenUserViewListener.run();
        });
        add(openUserViewButton);
    }

    private void addUser(String userId, String groupId) {
        User user = new User(userId);
        Group group;

        if (groupId.isEmpty()) {
            group = dataManager.getRootGroup();
        } else {
            group = dataManager.findGroupById(groupId);
        }

        if (group != null) {
            group.addUser(user);
        }
    }

    private void addGroup(String groupId, String parentGroupId) {
        Group group = new Group(groupId);
        Group parentGroup;

        if (parentGroupId.isEmpty()) {
            parentGroup = dataManager.getRootGroup();
        } else {
            parentGroup = dataManager.findGroupById(parentGroupId);
        }

        if (parentGroup != null) {
            parentGroup.addSubgroup(group);
        }
    }

    public void setOnRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnOpenUserViewListener(Runnable onOpenUserViewListener) {
        this.onOpenUserViewListener = onOpenUserViewListener;
    }
}
