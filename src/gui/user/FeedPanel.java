package gui.user;

import data.models.Feed;
import data.models.Tweet;
import data.models.identity.User;

import javax.swing.*;
import java.util.Arrays;

public class FeedPanel extends JPanel {
    private final User user;

    public FeedPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildUI();
    }

    private String[] getUserFeedContent() {
        Feed feed = new Feed();

        // Add our own tweets.
        feed.addTweets(user.getFeed().getTweets());

        // Add tweets from our followings.
        for (User following : user.getFollowing()) {
            feed.addTweets(following.getFeed().getTweets());
        }

        return Arrays.stream(feed.getTweets()).map(Tweet::getFormattedContent).toArray(String[]::new);
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
            user.getFeed().addTweet(new Tweet(content, user));
            refresh();
        });
        add(addTweetButton);
    }

    public void refresh() {
        buildUI();
        validate();
        repaint();
    }

    public User getUser() {
        return user;
    }
}
