package data.models.identity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds the group information.
 */
public class Group extends Identifiable {
    private final List<User> users = new ArrayList<>();
    private final List<Group> subgroups = new ArrayList<>();

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
        return users.toArray(User[]::new);
    }

    public void setUsers(User[] users) {
        this.users.clear();
        Collections.addAll(this.users, users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Group[] getSubgroups() {
        return subgroups.toArray(Group[]::new);
    }

    public void setSubgroups(Group[] subgroups) {
        this.subgroups.clear();
        Collections.addAll(this.subgroups, subgroups);
    }

    public void addSubgroup(Group subgroup) {
        this.subgroups.add(subgroup);
    }
}
