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

        JList<String> list = new JList<>(user.getFollowingIds());
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);
    }

    public UserModel getUser() {
        return user;
    }
}
