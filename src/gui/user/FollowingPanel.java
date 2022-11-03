package gui.user;

import data.local.DataManager;
import data.models.identity.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FollowingPanel extends JPanel {
    private final User user;

    private final DataManager dataManager = DataManager.getInstance();

    private Runnable onRefreshListener;

    public FollowingPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 200));
        setBorder(BorderFactory.createTitledBorder("Following"));

        buildUI();
    }

    private String[] getFollowingIds() {
        User[] following = user.getFollowing();
        return Arrays.stream(following).map(User::getId).toArray(String[]::new);
    }

    private void buildUI() {
        removeAll();

        String[] followingIds = getFollowingIds();
        JList<String> list = new JList<>(followingIds);
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addFollowingButton = new JButton("Follow user");
        addFollowingButton.addActionListener(actionEvent -> {
            String userId = JOptionPane.showInputDialog("User ID");

            if (dataManager.findUserById(userId) != null) {
                followUser(userId);
                refresh();
            } else {
                JOptionPane.showMessageDialog(null, "No user with that ID exists.");
            }
        });
        add(addFollowingButton);
    }

    private void followUser(String userId) {
        // Ensure this user exists.
        User followedUser = dataManager.findUserById(userId);
        if (followedUser == null)
            return;

        // Add this user to our following list.
        user.addFollowing(followedUser);

        // Add ourselves to this user's followers list.
        followedUser.addFollower(user);
    }

    public void refresh() {
        buildUI();
        validate();
        repaint();

        if (onRefreshListener != null)
            onRefreshListener.run();
    }

    public User getUser() {
        return user;
    }

    public void setOnRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }
}
