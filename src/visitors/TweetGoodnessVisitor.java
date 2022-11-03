package visitors;

import data.models.Tweet;

import java.util.Arrays;

/**
 * A concrete Visitor that checks if a Tweet is "good" or not.
 */
public class TweetGoodnessVisitor implements TweetVisitor {
    /**
     * Marked "good" words that makes a Tweet "good".
     */
    static final String[] GOOD_WORDS = {"good", "happy", "smile", "nice", "cool", "kind"};

    @Override
    public boolean visit(Tweet tweet) {
        String content = tweet.getContent();
        String[] words = content.split(" ");

        if (words.length != 0) {
            for (String word : words) {
                if (Arrays.asList(GOOD_WORDS).contains(word.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }
}
