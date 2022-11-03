package data.local;

import data.models.identity.Group;
import data.models.identity.User;

public interface IDataManager {
    Group getRootGroup();

    Group findGroupById(String id);

    User findUserById(String id);

    User[] getAllUsers();

    Group[] getAllGroups();
}
