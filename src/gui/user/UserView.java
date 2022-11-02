package gui.user;

import data.models.UserModel;

import javax.swing.*;
import java.awt.*;

/**
 * The user details page.
 */
public class UserView {
    private final JFrame frame;
    private final UserModel user;

    public UserView(UserModel user) {
        this.user = user;

        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(400, 500);
        frame.setTitle(user.getId());

        frame.add(new FollowingPanel(user), BorderLayout.NORTH);
        frame.add(new FeedPanel(user));
    }

    public void present() {
        frame.setVisible(true);
    }

    public UserModel getUser() {
        return user;
    }
}
