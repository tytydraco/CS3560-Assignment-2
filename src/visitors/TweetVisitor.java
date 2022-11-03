package visitors;

import data.models.Tweet;

/**
 * A class that can visit TweetVisitables and perform operations.
 */
public interface TweetVisitor {
    /**
     * Performs an operation on a Tweet.
     *
     * @param tweet The tweet to operate on.
     * @return True if the operation succeeded, false if it did not.
     */
    boolean visit(Tweet tweet);
}
