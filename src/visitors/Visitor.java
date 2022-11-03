package visitors;

import data.models.Tweet;

public interface Visitor {
    boolean visit(Tweet tweet);
}
