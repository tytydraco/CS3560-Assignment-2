package data.models;

import java.util.HashSet;

/**
 * Holds the user information.
 */
public class UserModel {
    private String id;

    private HashSet<String> followerIds;

    private HashSet<String> followingIds;

    private Tweet[] feed;

    public UserModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashSet<String> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(HashSet<String> followerIds) {
        this.followerIds = followerIds;
    }

    public void addFollower(String id) {
        this.followerIds.add(id);
    }

    public void removeFollower(String id) {
        this.followerIds.remove(id);
    }

    public HashSet<String> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(HashSet<String> followingIds) {
        this.followingIds = followingIds;
    }

    public void addFollowing(String id) {
        this.followingIds.add(id);
    }

    public void removeFollowing(String id) {
        this.followingIds.remove(id);
    }

    public Tweet[] getFeed() {
        return feed;
    }

    public void setFeed(Tweet[] feed) {
        this.feed = feed;
    }
}
