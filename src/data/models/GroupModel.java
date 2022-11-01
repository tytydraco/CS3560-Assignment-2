package data.models;

import java.util.ArrayList;

/**
 * Holds the group information.
 */
public class GroupModel {
    private String id;

    private ArrayList<UserModel> users = new ArrayList<>();
    private ArrayList<GroupModel> subgroups = new ArrayList<>();

    public GroupModel(String id) {
        this.id = id;
    }

    public GroupModel(String id, ArrayList<UserModel> users) {
        this.id = id;
        this.users = users;
    }

    public GroupModel(String id, ArrayList<UserModel> users, ArrayList<GroupModel> subgroups) {
        this.id = id;
        this.users = users;
        this.subgroups = subgroups;
    }

    public UserModel[] getUsers() {
        UserModel[] fixedUsers = new UserModel[users.size()];
        return users.toArray(fixedUsers);
    }

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;
    }

    public void addUser(UserModel user) {
        users.add(user);
    }

    public GroupModel[] getSubgroups() {
        GroupModel[] fixedSubgroups = new GroupModel[subgroups.size()];
        return subgroups.toArray(fixedSubgroups);
    }

    public void setSubgroups(ArrayList<GroupModel> subgroups) {
        this.subgroups = subgroups;
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
