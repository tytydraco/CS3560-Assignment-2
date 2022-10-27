package data.models;

/**
 * Holds the group information.
 */
public class GroupModel {
    private UserModel[] users;
    private GroupModel[] subgroups;

    public GroupModel(UserModel[] users) {
        this.users = users;
    }

    public GroupModel(UserModel[] users, GroupModel[] subgroups) {
        this.users = users;
        this.subgroups = subgroups;
    }

    public UserModel[] getUsers() {
        return users;
    }

    public void setUsers(UserModel[] users) {
        this.users = users;
    }

    public GroupModel[] getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(GroupModel[] subgroups) {
        this.subgroups = subgroups;
    }
}
