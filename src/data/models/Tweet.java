package data.models;

import data.models.identity.User;
import visitors.Visitable;
import visitors.Visitor;

import java.util.Date;

/**
 * A concrete implementation of the ITweet class with support for visitation and formatting.
 */
public class Tweet extends ITweet implements Visitable {
    public Tweet(String content, User user) {
        super(content, user);
    }

    public Tweet(String content, User user, Date date) {
        super(content, user, date);
    }

    @Override
    public String getFormattedContent() {
        return "[" + getDate() + "] " + getUser().getId() + ": " + getContent();
    }

    @Override
    public boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
