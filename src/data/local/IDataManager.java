package data.local;

import data.models.Tweet;
import data.models.identity.Group;
import data.models.identity.User;

/**
 * A class that holds the universal root group and provides simple data-related query methods.
 */
public interface IDataManager {
    /**
     * @return The root group.
     */
    Group getRootGroup();

    /**
     * @param id The Group ID.
     * @return The Group, or null if not found.
     */
    Group findGroupById(String id);

    /**
     * @param id The User ID.
     * @return The User, or null if not found.
     */
    User findUserById(String id);

    /**
     * @return A flat array of all Users part of the root Group.
     */
    User[] getAllUsers();

    /**
     * @return A flat array of all Groups part of the root Group (including the root Group itself).
     */
    Group[] getAllGroups();

    /**
     * @return A flat array of all Tweets from all Users part of the root Group.
     */
    Tweet[] getAllTweets();

    /**
     * @return The user that updated their feed most recently.
     */
    User getMostRecentlyUpdatedUser();
}
