package gui.user;

import data.models.UserModel;

import javax.swing.*;
import java.awt.*;

public class FollowingPanel extends JPanel {
    private final UserModel user;

    public FollowingPanel(UserModel user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Following"));

        buildUI();
    }

    private void buildUI() {
        removeAll();

        JList<String> list = new JList<>(user.getFollowingIds());
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addFollowingButton = new JButton("Follow user");
        addFollowingButton.addActionListener(actionEvent -> {
            String userId = JOptionPane.showInputDialog("User ID");
            user.addFollowing(userId);
            refresh();
        });
        add(addFollowingButton);
    }

    public void refresh() {
        buildUI();
        validate();
        repaint();
    }

    public UserModel getUser() {
        return user;
    }
}
