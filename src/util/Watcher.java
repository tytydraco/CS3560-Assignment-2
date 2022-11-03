package util;

/**
 * An observer of a feed that notifies when the feed changes.
 */
public interface Watcher {
    void update();
}