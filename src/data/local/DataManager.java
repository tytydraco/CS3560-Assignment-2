package data.local;

import data.models.GroupModel;

/**
 * A singleton that holds and manages users and groups.
 */
public class DataManager {
    private static DataManager instance;

    private final GroupModel rootGroup;

    private DataManager() {
        rootGroup = new GroupModel();
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
}
