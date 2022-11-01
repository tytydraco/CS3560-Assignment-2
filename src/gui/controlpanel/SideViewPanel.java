package gui.controlpanel;

import data.local.DataManager;
import data.models.GroupModel;
import data.models.UserModel;

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

        DataManager dataManager = DataManager.getInstance();
        GroupModel rootGroup = dataManager.getRootGroup();
        DefaultMutableTreeNode root = buildTreeNode(rootGroup);

        JTree topTree = new JTree(root);
        add(topTree);
    }

    /**
     * Build the tree nodes from the root group.
     */
    private DefaultMutableTreeNode buildTreeNode(GroupModel group) {
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(group.getId());
        for (UserModel user : group.getUsers()) {
            topNode.add(new DefaultMutableTreeNode(user.getId()));
        }

        for (GroupModel subgroup : group.getSubgroups()) {
            DefaultMutableTreeNode subgroupNode = buildTreeNode(subgroup);
            topNode.add(subgroupNode);
        }

        return topNode;
    }
}
