package data.models;

import java.util.HashSet;

/**
 * Holds the user information.
 */
public class UserModel {
    private final Feed feed = new Feed();
    private String id;
    private HashSet<UserModel> followers = new HashSet<>();
    private HashSet<UserModel> following = new HashSet<>();

    public UserModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashSet<UserModel> getFollowers() {
        return followers;
    }

    public void setFollowers(HashSet<UserModel> followers) {
        this.followers = followers;
    }

    public void addFollower(UserModel user) {
        this.followers.add(user);
    }

    public void removeFollower(UserModel user) {
        this.followers.remove(user);
    }

    public UserModel[] getFollowing() {
        UserModel[] fixedFollowing = new UserModel[following.size()];
        return following.toArray(fixedFollowing);
    }

    public void setFollowing(HashSet<UserModel> following) {
        this.following = following;
    }

    public void addFollowing(UserModel user) {
        this.following.add(user);
        user.addFollower(this);
    }

    public void removeFollowing(UserModel user) {
        this.following.remove(user);
        user.removeFollower(this);
    }

    public Feed getFeed() {
        return feed;
    }
}
