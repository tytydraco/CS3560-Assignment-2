package data.models;

import util.Watchable;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * An observable feed of Tweets, sorted by their post date.
 */
public class Feed extends Watchable {
    private final TreeSet<Tweet> tweets = new TreeSet<>(Comparator.comparing(Tweet::getDate));

    private long lastUpdateTimeMs = -1;

    public Tweet[] getTweets() {
        return tweets.toArray(Tweet[]::new);
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets.clear();
        addTweets(tweets);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        lastUpdateTimeMs = System.currentTimeMillis();
        notifyWatchers();
    }

    public void addTweets(Tweet[] tweets) {
        Collections.addAll(this.tweets, tweets);
        lastUpdateTimeMs = System.currentTimeMillis();
        notifyWatchers();
    }

    public long getLastUpdateTimeMs() {
        return lastUpdateTimeMs;
    }

    public void setLastUpdateTimeMs(long lastUpdateTimeMs) {
        this.lastUpdateTimeMs = lastUpdateTimeMs;
    }
}
