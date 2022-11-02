package gui.user;

import data.local.DataManager;
import data.models.UserModel;

import javax.swing.*;
import java.awt.*;

public class FollowingPanel extends JPanel {
    private final UserModel user;

    private final DataManager dataManager = DataManager.getInstance();

    public FollowingPanel(UserModel user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 200));
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
            followUser(userId);
            refresh();
        });
        add(addFollowingButton);
    }

    private void followUser(String userId) {
        // Ensure this user exists.
        UserModel followedUser = dataManager.findUserById(userId);
        if (followedUser == null)
            return;

        // Add this user to our following list.
        user.addFollowing(userId);
        
        // Add ourselves to this user's followers list.
        followedUser.addFollower(user.getId());
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
