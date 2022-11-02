package gui.user;

import data.models.Tweet;
import data.models.UserModel;

import javax.swing.*;
import java.util.Arrays;

public class FeedPanel extends JPanel {
    private final UserModel user;

    public FeedPanel(UserModel user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildUI();
    }

    private String[] getUserFeedContent() {
        Tweet[] tweets = user.getFeed();
        return Arrays.stream(tweets).map(Tweet::getContent).toArray(String[]::new);
    }

    private void buildUI() {
        removeAll();

        String[] feed = getUserFeedContent();
        JList<String> list = new JList<>(feed);
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addTweetButton = new JButton("Add Tweet");
        addTweetButton.addActionListener(actionEvent -> {
            String content = JOptionPane.showInputDialog("Content");
            user.postTweet(new Tweet(content));
            refresh();
        });
        add(addTweetButton);
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