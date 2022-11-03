package data.models;

import data.models.identity.User;
import visitors.Visitable;
import visitors.Visitor;

import java.util.Date;

/**
 * A Tweet containing a message.
 */
public class Tweet implements Visitable {
    private String content;

    private Date date;

    private User user;

    public Tweet(String content, User user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }

    public Tweet(String content, User user, Date date) {
        this.content = content;
        this.user = user;
        this.date = date;
    }

    public String getFormattedContent() {
        return "[" + getDate() + "] " + user.getId() + ": " + getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
