package gui.user;

import data.models.Feed;
import data.models.Tweet;
import data.models.identity.User;
import visitors.TweetLengthValidatorVisitor;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FeedPanel extends JPanel {
    private final User user;
    private final Set<User> followingWatching = new HashSet<>();

    public FeedPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildUI();
    }

    /**
     * Remove this observer from all current user feeds.
     */
    public void removeAllWatchers() {
        for (User following : followingWatching) {
            following.getFeed().removeWatcher(this::refresh);
        }
        followingWatching.clear();
    }

    /**
     * Register watchers on all following user feeds so that we can refresh if they are changed.
     */
    private void updateWatchers() {
        removeAllWatchers();

        for (User following : user.getFollowing()) {
            Feed feed = following.getFeed();
            feed.addWatcher(this::refresh);
            followingWatching.add(following);
        }

        // Watch this user's feed as well.
        Feed feed = user.getFeed();
        feed.addWatcher(this::refresh);
        followingWatching.add(user);
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
        updateWatchers();

        JList<String> list = new JList<>(feed);
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addTweetButton = new JButton("Add Tweet");
        addTweetButton.addActionListener(actionEvent -> {
            String content = JOptionPane.showInputDialog("Content");
            Tweet tweet = new Tweet(content, user);
            boolean isValidLength = tweet.accept(new TweetLengthValidatorVisitor());

            if (isValidLength)
                user.getFeed().addTweet(tweet);
            else
                JOptionPane.showMessageDialog(null, "Tweet must be less than " + TweetLengthValidatorVisitor.MAX_TWEET_LENGTH + " characters long.");
        });
        add(addTweetButton);

        long creationTimeMs = user.getCreationTimeMs();
        long lastUpdateTimeMs = user.getFeed().getLastUpdateTimeMs();
        JTextArea timestampText = new JTextArea("User creation time: " + creationTimeMs + "\nLast update time: " + lastUpdateTimeMs);
        add(timestampText);
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
