package util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Marks a class as observable, and adds methods to add, remove, and notify observers.
 * This class is concurrency-safe.
 */
public abstract class Watchable {
    private final List<Watcher> watchers = new CopyOnWriteArrayList<>();

    /**
     * Add a Watcher to the list of Watchers.
     *
     * @param watcher The Watcher to add.
     */
    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    /**
     * Remove a Watcher to the list of Watchers.
     *
     * @param watcher The Watcher to remove.
     */
    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    /**
     * Notify all watchers that data has changed.
     */
    public void notifyWatchers() {
        watchers.forEach(Watcher::update);
    }
}
