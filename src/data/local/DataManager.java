package data.local;

import data.models.identity.Group;
import data.models.identity.User;

/**
 * A singleton that holds and manages users and groups.
 */
public class DataManager {
    private static DataManager instance;

    private final Group rootGroup;

    private DataManager() {
        User test1 = new User("test1");
        User test2 = new User("test2");
        User test3 = new User("test3");
        User test4 = new User("test4");
        User test5 = new User("test5");
        User test6 = new User("test6");

        test1.addFollowing(test2);
        test1.addFollowing(test3);

        //test1.getFeed().addTweet(new Tweet("Hello from test1!"));
        //test2.getFeed().addTweet(new Tweet("Hello from test2!"));
        //test3.getFeed().addTweet(new Tweet("Hello from test3!"));
        //test4.getFeed().addTweet(new Tweet("Hello from test4!"));
        //test5.getFeed().addTweet(new Tweet("Hello from test5!"));
        //test6.getFeed().addTweet(new Tweet("Hello from test6!"));

        rootGroup = new Group("Root");
        rootGroup.addUser(test1);
        rootGroup.addUser(test2);
        rootGroup.addUser(test3);

        Group subgroup1 = new Group("sub1");
        subgroup1.addUser(test4);
        subgroup1.addUser(test5);
        subgroup1.addUser(test6);
        rootGroup.addSubgroup(subgroup1);
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

    public User findUserById(String id) {
        Group rootGroup = getRootGroup();
        return recursivelyFindUserById(id, rootGroup);
    }
}
