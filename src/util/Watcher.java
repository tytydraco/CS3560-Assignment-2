package util;

/**
 * An observer of a feed that notifies when the feed changes.
 */
public interface Watcher {
    /**
     * Perform changes when data is marked as changed.
     */
    void update();
}