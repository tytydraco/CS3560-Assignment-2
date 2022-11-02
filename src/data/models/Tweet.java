package data.models;

/**
 * A Tweet containing a message.
 */
public class Tweet {
    private String content;

    public Tweet(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
