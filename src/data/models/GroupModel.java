package data.models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds the group information.
 */
public class GroupModel {
    private final ArrayList<UserModel> users = new ArrayList<>();
    private final ArrayList<GroupModel> subgroups = new ArrayList<>();
    private String id;

    public GroupModel(String id) {
        this.id = id;
    }

    public GroupModel(String id, UserModel[] users) {
        this.id = id;
        setUsers(users);
    }

    public GroupModel(String id, UserModel[] users, GroupModel[] subgroups) {
        this.id = id;
        setUsers(users);
        setSubgroups(subgroups);
    }

    public UserModel[] getUsers() {
        UserModel[] fixedUsers = new UserModel[users.size()];
        return users.toArray(fixedUsers);
    }

    public void setUsers(UserModel[] users) {
        this.users.clear();
        Collections.addAll(this.users, users);
    }

    public void addUser(UserModel user) {
        users.add(user);
    }

    public GroupModel[] getSubgroups() {
        GroupModel[] fixedSubgroups = new GroupModel[subgroups.size()];
        return subgroups.toArray(fixedSubgroups);
    }

    public void setSubgroups(GroupModel[] subgroups) {
        this.subgroups.clear();
        Collections.addAll(this.subgroups, subgroups);
    }

    public void addSubgroup(GroupModel subgroup) {
        this.subgroups.add(subgroup);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
