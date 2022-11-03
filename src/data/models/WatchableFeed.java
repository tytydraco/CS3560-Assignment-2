package data.models;

import util.Watcher;

import java.util.concurrent.CopyOnWriteArrayList;

public class WatchableFeed extends Feed {
    private final CopyOnWriteArrayList<Watcher> watchers = new CopyOnWriteArrayList<>();

    @Override
    public void addTweet(Tweet tweet) {
        super.addTweet(tweet);
        notifyWatchers();
    }

    @Override
    public void addTweets(Tweet[] tweets) {
        super.addTweets(tweets);
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
}
