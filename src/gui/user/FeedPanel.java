package gui.user;

import data.models.Tweet;
import data.models.UserModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class FeedPanel extends JPanel {
    private final UserModel user;

    public FeedPanel(UserModel user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildUI();
    }

    private String[] getUserFeedContent() {
        ArrayList<Tweet> tweets = new ArrayList<>();

        // Add our own tweets.
        Collections.addAll(tweets, user.getTweets());

        // Add tweets from our followings.
        for (UserModel following : user.getFollowing()) {
            Collections.addAll(tweets, following.getTweets());
        }

        return tweets.stream().map(Tweet::getContent).toArray(String[]::new);
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
