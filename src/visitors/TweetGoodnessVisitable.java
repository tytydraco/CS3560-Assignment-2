package visitors;

public interface TweetGoodnessVisitable {
    boolean accept(TweetGoodnessVisitor visitor);
}