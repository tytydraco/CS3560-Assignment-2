package gui.user;

import data.models.identity.User;

import javax.swing.*;
import java.awt.*;

/**
 * The user details page.
 */
public class UserView {
    private final JFrame frame;
    private final User user;

    public UserView(User user) {
        this.user = user;

        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(400, 500);
        frame.setTitle(user.getId());

        FeedPanel feedPanel = new FeedPanel(user);
        FollowingPanel followingPanel = new FollowingPanel(user);
        followingPanel.setOnRefreshListener(feedPanel::refresh);

        frame.add(followingPanel, BorderLayout.NORTH);
        frame.add(feedPanel);
    }

    public void present() {
        frame.setVisible(true);
    }

    public User getUser() {
        return user;
    }
}
