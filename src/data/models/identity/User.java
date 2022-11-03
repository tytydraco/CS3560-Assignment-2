package data.models.identity;

import data.models.Feed;

import java.util.HashSet;

/**
 * Holds the user information.
 */
public class User extends Identifiable {
    private final Feed feed = new Feed();
    private HashSet<User> followers = new HashSet<>();
    private HashSet<User> following = new HashSet<>();

    public User(String id) {
        super(id);
    }

    public HashSet<User> getFollowers() {
        return followers;
    }

    public void setFollowers(HashSet<User> followers) {
        this.followers = followers;
    }

    public void addFollower(User user) {
        this.followers.add(user);
    }

    public void removeFollower(User user) {
        this.followers.remove(user);
    }

    public User[] getFollowing() {
        User[] fixedFollowing = new User[following.size()];
        return following.toArray(fixedFollowing);
    }

    public void setFollowing(HashSet<User> following) {
        this.following = following;
    }

    public void addFollowing(User user) {
        this.following.add(user);

        // Add ourselves as a follower to the target user.
        user.addFollower(this);
    }

    public void removeFollowing(User user) {
        this.following.remove(user);

        // Remove ourselves as a follower to the target user.
        user.removeFollower(this);
    }

    public Feed getFeed() {
        return feed;
    }
}
