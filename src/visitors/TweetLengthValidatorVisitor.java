package visitors;

import data.models.Tweet;

/**
 * A concrete Visitor that checks if a Tweet is "good" or not.
 */
public class TweetLengthValidatorVisitor implements TweetVisitor {
    /**
     * Maximum tweet length characters.
     */
    public static final int MAX_TWEET_LENGTH = 100;

    @Override
    public boolean visit(Tweet tweet) {
        String content = tweet.getContent();
        return content.length() <= MAX_TWEET_LENGTH;
    }
}
