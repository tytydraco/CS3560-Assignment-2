package gui.user;

import data.models.Feed;
import data.models.Tweet;
import data.models.identity.User;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;

public class FeedPanel extends JPanel {
    private final User user;
    private final HashSet<User> followingWatching = new HashSet<>();

    public FeedPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildUI();
    }

    /**
     * Register watchers on all following user feeds so that we can refresh if they are changed.
     */
    private void updateWatchers() {
        for (User following : followingWatching) {
            following.getFeed().removeWatcher(feedUpdateWatcher);
        }

        followingWatching.clear();
        for (User following : user.getFollowing()) {
            Feed feed = following.getFeed();
            feed.addWatcher(feedUpdateWatcher);
            followingWatching.add(following);
        }

        // Watch this user's feed as well.
        Feed feed = user.getFeed();
        feed.addWatcher(feedUpdateWatcher);
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
            user.getFeed().addTweet(new Tweet(content, user));
        });
        add(addTweetButton);
    }    private final Feed.Watcher feedUpdateWatcher = this::refresh;

    public void refresh() {
        buildUI();
        validate();
        repaint();
    }

    public User getUser() {
        return user;
    }




}
