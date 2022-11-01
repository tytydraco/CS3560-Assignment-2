package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Presents the configuration view.
 */
public class ManagePanel extends JPanel {
    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Manage"));

        JButton addUserButton = new JButton("Add user");
        add(addUserButton);

        JButton addGroupButton = new JButton("Add group");
        add(addGroupButton);

        JButton openUserViewButton = new JButton("Open user view");
        add(openUserViewButton);
    }
}
