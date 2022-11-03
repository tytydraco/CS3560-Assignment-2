package data.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class Feed {
    private final TreeSet<Tweet> tweets = new TreeSet<>(Comparator.comparing(Tweet::getDate));

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
    }

    public void addTweets(Tweet[] tweets) {
        Collections.addAll(this.tweets, tweets);
    }
}
