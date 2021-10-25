package TimeOffManagement_Main_Package.session;

import TimeOffManagement_Main_Package.datasource.User;

/**
 * This is class is a "Singleton" type class
 * <p>
 * https://www.geeksforgeeks.org/singleton-class-java/
 * <p>
 * In object-oriented programming, a singleton class is a class that can have only one object (an instance of the class)
 * at a time.
 * After first time, if we try to instantiate the Singleton class, the new variable also points to the
 * first instance created. So whatever modifications we do to any variable inside the class through any instance,
 * it affects the variable of the single instance created and is visible if we access that variable
 * through any variable of that class type defined.
 * To design a singleton class:
 * <p>
 * Make constructor as private.
 * Write a static method that has return type object of this singleton class.
 */

public class UserSession {

    private UserSession() {
    }

    private static UserSession instance;

    public static UserSession getUserSession_instance() {
        if (instance == null) {
            instance = new UserSession();
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
