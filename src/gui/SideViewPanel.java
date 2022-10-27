package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Presents the tree view of users and groups.
 */
public class SideViewPanel extends JPanel {
    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Test");
        top.add(new DefaultMutableTreeNode("hi"));

        JTree topTree = new JTree(top);
        add(topTree);
    }
}
