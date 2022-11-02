package visitors;

public interface TweetGoodnessVisitable {
    double accept(TweetGoodnessVisitor visitor);
}