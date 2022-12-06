package data.models.identity;

/**
 * An object that can be identified using a unique ID.
 */
public abstract class Identifiable {
    private String id;
    private long creationTimeMs;

    public Identifiable(String id) {
        this.id = id;
        this.creationTimeMs = System.currentTimeMillis();
    }

    public Identifiable(String id, long creationTimeMs) {
        this.id = id;
        this.creationTimeMs = creationTimeMs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreationTimeMs() {
        return creationTimeMs;
    }

    public void setCreationTimeMs(long creationTimeMs) {
        this.creationTimeMs = creationTimeMs;
    }
}
