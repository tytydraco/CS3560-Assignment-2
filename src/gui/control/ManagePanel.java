package gui.control;

import data.local.DataManager;
import data.models.Tweet;
import data.models.identity.Group;
import data.models.identity.User;
import visitors.TweetGoodnessVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Presents the configuration view.
 */
public class ManagePanel extends JPanel {
    private final DataManager dataManager = DataManager.getInstance();
    private Runnable onRefreshListener;
    private Runnable onOpenUserViewListener;

    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Manage"));

        JButton addUserButton = new JButton("Add user");
        addUserButton.addActionListener(actionEvent -> {
            JTextField userIdField = new JTextField();
            JTextField groupIdField = new JTextField();
            Object[] request = {
                    "User ID", userIdField,
                    "Group ID", groupIdField,
            };

            JOptionPane.showMessageDialog(null, request, "Add User", JOptionPane.QUESTION_MESSAGE);

            String userId = userIdField.getText();
            String groupId = groupIdField.getText();

            addUser(userId, groupId);

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addUserButton);

        JButton addGroupButton = new JButton("Add group");
        addGroupButton.addActionListener(actionEvent -> {
            JTextField groupIdField = new JTextField();
            JTextField parentGroupIdField = new JTextField();
            Object[] request = {
                    "Group ID", groupIdField,
                    "Parent Group ID", parentGroupIdField,
            };

            JOptionPane.showMessageDialog(null, request, "Add Group", JOptionPane.QUESTION_MESSAGE);

            String groupId = groupIdField.getText();
            String parentGroupId = parentGroupIdField.getText();

            addGroup(groupId, parentGroupId);

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addGroupButton);

        JButton openUserViewButton = new JButton("Open user view");
        openUserViewButton.addActionListener(actionEvent -> {
            if (onOpenUserViewListener != null)
                onOpenUserViewListener.run();
        });
        add(openUserViewButton);

        JButton checkGoodnessButton = new JButton("Check goodness");
        checkGoodnessButton.addActionListener(actionEvent -> {
            double totalGoodness = 0;
            int tweetCount = 0;

            TweetGoodnessVisitor tweetGoodnessVisitor = new TweetGoodnessVisitor();

            Tweet[] allTweets = dataManager.getAllTweets();
            for (Tweet tweet : allTweets) {
                boolean good = tweet.accept(tweetGoodnessVisitor);
                if (good)
                    totalGoodness++;

                tweetCount += 1;
            }

            if (tweetCount != 0)
                totalGoodness /= tweetCount;

            JOptionPane.showMessageDialog(null, "Total goodness: " + totalGoodness * 100 + "%");
        });
        add(checkGoodnessButton);

        JButton statsButton = new JButton("Show stats");
        statsButton.addActionListener(actionEvent -> {
            Group[] allGroups = dataManager.getAllGroups();
            User[] allUsers = dataManager.getAllUsers();
            Tweet[] allTweets = dataManager.getAllTweets();

            JOptionPane.showMessageDialog(null, "Total groups: " + allGroups.length + "\nTotal users: " + allUsers.length + "\nTotal tweets: " + allTweets.length);
        });
        add(statsButton);

        JButton validateButton = new JButton("Validate IDs");
        validateButton.addActionListener(actionEvent -> {
            User[] allUsers = dataManager.getAllUsers();
            Group[] allGroups = dataManager.getAllGroups();

            boolean usersValid;
            boolean usersHadSpaces = false;
            Set<String> uniqueUserIds = new HashSet<>();
            for (User user : allUsers) {
                String id = user.getId();
                uniqueUserIds.add(id);

                if (id.contains(" "))
                    usersHadSpaces = true;
            }
            usersValid = uniqueUserIds.size() == allUsers.length && !usersHadSpaces;

            boolean groupsValid;
            boolean groupsHadSpaces = false;
            Set<String> uniqueGroupIds = new HashSet<>();
            for (Group group : allGroups) {
                String id = group.getId();
                uniqueGroupIds.add(id);

                if (id.contains(" "))
                    groupsHadSpaces = true;
            }
            groupsValid = uniqueGroupIds.size() == allGroups.length && !groupsHadSpaces;

            JOptionPane.showMessageDialog(null, "User validity: " + usersValid + "\nGroups validity: " + groupsValid);
        });
        add(validateButton);

        JButton getLatestUser = new JButton("Get most recent user");
        getLatestUser.addActionListener(actionEvent -> {
            User mostRecentUser = dataManager.getMostRecentlyUpdatedUser();

            if (mostRecentUser == null) {
                JOptionPane.showMessageDialog(null, "No users!");
            } else {
                JOptionPane.showMessageDialog(null, "User ID: " + mostRecentUser.getId() + "\nUpdate time: " + mostRecentUser.getFeed().getLastUpdateTimeMs());
            }
        });
        add(getLatestUser);
    }

    private void addUser(String userId, String groupId) {
        if (userId.isEmpty())
            return;

        User user = new User(userId);
        Group group;

        if (groupId.isEmpty()) {
            group = dataManager.getRootGroup();
        } else {
            group = dataManager.findGroupById(groupId);
        }

        if (group != null) {
            group.addUser(user);
        }
    }

    private void addGroup(String groupId, String parentGroupId) {
        if (groupId.isEmpty())
            return;

        Group group = new Group(groupId);
        Group parentGroup;

        if (parentGroupId.isEmpty()) {
            parentGroup = dataManager.getRootGroup();
        } else {
            parentGroup = dataManager.findGroupById(parentGroupId);
        }

        if (parentGroup != null) {
            parentGroup.addSubgroup(group);
        }
    }

    public void setOnRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnOpenUserViewListener(Runnable onOpenUserViewListener) {
        this.onOpenUserViewListener = onOpenUserViewListener;
    }
}
