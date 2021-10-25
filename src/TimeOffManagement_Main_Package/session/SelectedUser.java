package TimeOffManagement_Main_Package.session;

import TimeOffManagement_Main_Package.datasource.User;

public class SelectedUser {

    /**
     * Singleton class
     * This instance is being updated, when a user is selected for changes.
     */

    private static SelectedUser instance;

    public static SelectedUser getSelectedUser_instance() {
        if (instance == null) {
            instance = new SelectedUser();
        }
        return instance;
    }

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
