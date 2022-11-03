package data.local;

import data.models.Tweet;
import data.models.identity.Group;
import data.models.identity.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete singleton implementation of the IDataManager interface.
 */
public class DataManager implements IDataManager {
    private static DataManager instance;

    private final Group rootGroup = new Group("Root");

    private DataManager() {
    }

    /**
     * Gets the singleton instance.
     *
     * @return the instance if it exists. Otherwise, create a new instance.
     */
    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    @Override
    public Group getRootGroup() {
        return rootGroup;
    }

    private Group recursivelyFindGroupById(String id, Group root) {
        if (root.getId().equals(id)) {
            return root;
        }

        for (Group subgroup : root.getSubgroups()) {
            Group result = recursivelyFindGroupById(id, subgroup);
            if (result != null)
                return result;
        }

        return null;
    }

    @Override
    public Group findGroupById(String id) {
        Group rootGroup = getRootGroup();
        return recursivelyFindGroupById(id, rootGroup);
    }

    private User recursivelyFindUserById(String id, Group root) {
        for (User user : root.getUsers()) {
            if (user.getId().equals(id))
                return user;
        }

        for (Group subgroup : root.getSubgroups()) {
            User result = recursivelyFindUserById(id, subgroup);
            if (result != null)
                return result;
        }

        return null;
    }

    @Override
    public User findUserById(String id) {
        Group rootGroup = getRootGroup();
        return recursivelyFindUserById(id, rootGroup);
    }

    private User[] recursivelyGetAllUsers(Group group) {
        ArrayList<User> users = new ArrayList<>();

        User[] groupUsers = group.getUsers();

        if (groupUsers.length > 0)
            Collections.addAll(users, groupUsers);

        for (Group subgroup : group.getSubgroups()) {
            Collections.addAll(users, recursivelyGetAllUsers(subgroup));
        }

        User[] fixedUsers = new User[users.size()];
        return users.toArray(fixedUsers);
    }

    @Override
    public User[] getAllUsers() {
        return recursivelyGetAllUsers(rootGroup);
    }

    private Group[] recursivelyGetAllGroups(Group group) {
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(group);

        Group[] subgroups = group.getSubgroups();

        for (Group subgroup : subgroups) {
            Collections.addAll(groups, recursivelyGetAllGroups(subgroup));
        }

        Group[] fixedGroups = new Group[groups.size()];
        return groups.toArray(fixedGroups);
    }

    @Override
    public Group[] getAllGroups() {
        return recursivelyGetAllGroups(rootGroup);
    }

    @Override
    public Tweet[] getAllTweets() {
        ArrayList<Tweet> tweets = new ArrayList<>();

        User[] allUsers = getAllUsers();
        for (User user : allUsers) {
            Collections.addAll(tweets, user.getFeed().getTweets());
        }

        Tweet[] fixedTweets = new Tweet[tweets.size()];
        return tweets.toArray(fixedTweets);
    }
}
