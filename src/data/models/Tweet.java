package data.models;

import java.util.Date;

/**
 * A Tweet containing a message.
 */
public class Tweet {
    // TODO: add timestamp, sort tweets by timestamp in [FeedPanel].

    private String content;

    private Date date;

    private UserModel user;

    public Tweet(String content, UserModel user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }

    public Tweet(String content, UserModel user, Date date) {
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
