package data.models;

/**
 * Holds the user information.
 */
public class UserModel {
    private String id;

    public UserModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
