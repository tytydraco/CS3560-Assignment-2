package data.models;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Holds the user information.
 */
public class UserModel {
    private String id;

    private HashSet<String> followerIds = new HashSet<>();

    private HashSet<String> followingIds = new HashSet<>();

    private ArrayList<Tweet> feed = new ArrayList<>();

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

    public String[] getFollowingIds() {
        String[] fixedFollowingIds = new String[followingIds.size()];
        return followingIds.toArray(fixedFollowingIds);
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
        Tweet[] fixedFeed = new Tweet[feed.size()];
        return feed.toArray(fixedFeed);
    }

    public void setFeed(ArrayList<Tweet> feed) {
        this.feed = feed;
    }

    public void postTweet(Tweet tweet) {
        feed.add(tweet);
    }
}
