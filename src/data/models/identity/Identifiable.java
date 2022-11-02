package data.models.identity;

/**
 * An object that can be identified using a unique ID.
 */
public abstract class Identifiable {
    private String id;

    public Identifiable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
