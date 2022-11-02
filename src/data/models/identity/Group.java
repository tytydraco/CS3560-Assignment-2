package data.models.identity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds the group information.
 */
public class Group extends Identifiable {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Group> subgroups = new ArrayList<>();

    public Group(String id) {
        super(id);
    }

    public Group(String id, User[] users) {
        super(id);
        setUsers(users);
    }

    public Group(String id, User[] users, Group[] subgroups) {
        super(id);
        setUsers(users);
        setSubgroups(subgroups);
    }

    public User[] getUsers() {
        User[] fixedUsers = new User[users.size()];
        return users.toArray(fixedUsers);
    }

    public void setUsers(User[] users) {
        this.users.clear();
        Collections.addAll(this.users, users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Group[] getSubgroups() {
        Group[] fixedSubgroups = new Group[subgroups.size()];
        return subgroups.toArray(fixedSubgroups);
    }

    public void setSubgroups(Group[] subgroups) {
        this.subgroups.clear();
        Collections.addAll(this.subgroups, subgroups);
    }

    public void addSubgroup(Group subgroup) {
        this.subgroups.add(subgroup);
    }
}
