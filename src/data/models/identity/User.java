package data.models.identity;

import data.models.Feed;

import java.util.HashSet;
import java.util.Set;

/**
 * Holds the user information.
 */
public class User extends Identifiable {
    private final Feed feed = new Feed();
    private Set<User> followers = new HashSet<>();
    private Set<User> following = new HashSet<>();

    public User(String id) {
        super(id);
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void addFollower(User user) {
        this.followers.add(user);
    }

    public void removeFollower(User user) {
        this.followers.remove(user);
    }

    public User[] getFollowing() {
        return following.toArray(User[]::new);
    }

    public void setFollowing(Set<User> following) {
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
