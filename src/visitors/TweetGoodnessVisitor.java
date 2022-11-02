package visitors;

import data.models.Tweet;

import java.util.Arrays;

public class TweetGoodnessVisitor {
    static final String[] GOOD_WORDS = {"good", "happy", "smile", "nice", "cool", "kind"};

    public double visit(Tweet tweet) {
        String content = tweet.getContent();
        String[] words = content.split(" ");

        if (words.length == 0)
            return 0;

        int goodWords = 0;
        for (String word : words) {
            if (Arrays.asList(GOOD_WORDS).contains(word.toLowerCase())) {
                goodWords++;
            }
        }

        return (double) goodWords / words.length;
    }
}
