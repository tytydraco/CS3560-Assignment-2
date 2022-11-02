package data.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Feed {
    private final TreeSet<Tweet> tweets = new TreeSet<>(Comparator.comparing(Tweet::getDate));

    private final CopyOnWriteArrayList<Watcher> watchers = new CopyOnWriteArrayList<>();

    public Tweet[] getTweets() {
        Tweet[] fixedTweets = new Tweet[tweets.size()];
        return tweets.toArray(fixedTweets);
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets.clear();
        addTweets(tweets);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        notifyWatchers();
    }

    public void addTweets(Tweet[] tweets) {
        Collections.addAll(this.tweets, tweets);
        notifyWatchers();
    }

    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    private void notifyWatchers() {
        watchers.forEach(Watcher::update);
    }

    /**
     * An observer of a feed that notifies when the feed changes.
     */
    public interface Watcher {
        void update();
    }
}
