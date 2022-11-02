package data.models;

import java.util.Date;

/**
 * A Tweet containing a message.
 */
public class Tweet {
    // TODO: add timestamp, sort tweets by timestamp in [FeedPanel].

    private String content;

    private Date date;

    public Tweet(String content) {
        this.content = content;
        this.date = new Date();
    }

    public Tweet(String content, Date date) {
        this.date = date;
        this.content = content;
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
}
