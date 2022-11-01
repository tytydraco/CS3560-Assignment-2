package gui.controlpanel;

import data.local.DataManager;
import data.models.GroupModel;
import data.models.UserModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Presents the tree view of users and groups.
 */
public class SideViewPanel extends JPanel {

    private JTree tree;

    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));
        buildTree();
    }

    public void buildTree() {
        DataManager dataManager = DataManager.getInstance();
        GroupModel rootGroup = dataManager.getRootGroup();
        DefaultMutableTreeNode root = buildTreeNode(rootGroup);

        tree = new JTree(root);
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                if (value instanceof DefaultMutableTreeNode) {
                    // Set the name of the tree cell to the ID if it is a user or group.
                    // TODO: replace with OOP method, common parent class
                    Object userValue = ((DefaultMutableTreeNode) value).getUserObject();

                    if (userValue instanceof UserModel) {
                        setText(((UserModel) userValue).getId());
                    }

                    if (userValue instanceof GroupModel) {
                        setText(((GroupModel) userValue).getId());
                    }
                }
                return this;
            }
        });

        removeAll();
        add(tree);
    }

    public void refresh() {
        buildTree();
        validate();
        repaint();
    }

    public UserModel getSelectedUser() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node != null) {
            Object userObject = node.getUserObject();
            if (userObject instanceof UserModel)
                return (UserModel) userObject;
        }

        return null;
    }

    /**
     * Build the tree nodes from the root group.
     */
    private DefaultMutableTreeNode buildTreeNode(GroupModel group) {
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(group);
        for (UserModel user : group.getUsers()) {
            topNode.add(new DefaultMutableTreeNode(user));
        }

        for (GroupModel subgroup : group.getSubgroups()) {
            DefaultMutableTreeNode subgroupNode = buildTreeNode(subgroup);
            topNode.add(subgroupNode);
        }

        return topNode;
    }
}
