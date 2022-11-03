package visitors;

public interface Visitable {
    boolean accept(Visitor visitor);
}