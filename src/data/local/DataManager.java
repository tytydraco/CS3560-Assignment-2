package data.local;

import data.models.GroupModel;
import data.models.UserModel;

/**
 * A singleton that holds and manages users and groups.
 */
public class DataManager {
    private static DataManager instance;

    private final GroupModel rootGroup;

    private DataManager() {
        rootGroup = new GroupModel("Root");
        rootGroup.addUser(new UserModel("test1"));
        rootGroup.addUser(new UserModel("test2"));
        rootGroup.addUser(new UserModel("test3"));

        GroupModel subgroup1 = new GroupModel("sub1");
        subgroup1.addUser(new UserModel("test4"));
        subgroup1.addUser(new UserModel("test5"));
        subgroup1.addUser(new UserModel("test6"));
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

    public GroupModel getRootGroup() {
        return rootGroup;
    }

    private GroupModel recursivelyFindGroupById(String id, GroupModel root) {
        if (root.getId().equals(id)) {
            return root;
        }

        for (GroupModel subgroup : root.getSubgroups()) {
            GroupModel result = recursivelyFindGroupById(id, subgroup);
            if (result != null)
                return result;
        }

        return null;
    }

    public GroupModel findGroupById(String id) {
        GroupModel rootGroup = getRootGroup();
        return recursivelyFindGroupById(id, rootGroup);
    }
}
