package gui.controlpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Presents the configuration view.
 */
public class ManagePanel extends JPanel {
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
            System.out.println(userIdField.getText());
            System.out.println(groupIdField.getText());
            // TODO: add new user and refresh
            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addUserButton);

        JButton addGroupButton = new JButton("Add group");
        addGroupButton.addActionListener(actionEvent -> {
            String id = JOptionPane.showInputDialog("Group ID");
            System.out.println(id);
            // TODO: add new group and refresh
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

    public void setOnRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnOpenUserViewListener(Runnable onOpenUserViewListener) {
        this.onOpenUserViewListener = onOpenUserViewListener;
    }
}
