package visitors;

/**
 * A class that can be visited by a TweetVisitor.
 */
public interface TweetVisitable {
    /**
     * Accept a TweetVisitor class and allow it to perform operations.
     *
     * @param visitor The TweetVisitor class to use.
     * @return True if the operation succeeded, false if it did not.
     */
    boolean accept(TweetVisitor visitor);
}