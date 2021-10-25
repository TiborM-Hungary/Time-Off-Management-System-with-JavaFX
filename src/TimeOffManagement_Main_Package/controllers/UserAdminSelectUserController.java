package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.datasource.User;
import TimeOffManagement_Main_Package.session.SelectedUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserAdminSelectUserController implements Initializable {
    /**
     * Initialize the TableView with data
     * Select the user from the DB
     * Returns a USER object to the TimeOffAdminController class
     */

    @FXML
    private TableView selectUserTableView;

    @FXML
    private Button selectUserSelectUser;

    @FXML
    private Button selectUserCreateUser;

    @FXML
    private Button selectUserUpdate;

    @FXML
    private Button selectUserDeleteUser;

    @FXML
    private Button selectUserCancel;


    //I want to initialize via the implementation of the "initialize" by extending the Initializable class
    //https://stackoverflow.com/questions/42942505/how-to-run-a-method-in-javafx-upon-the-opening-of-a-new-scene
    //Example:
    //https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method

//    void initialize(URL location,
//                    ResourceBundle resources)
//    Called to initialize a controller after its root element has been completely processed.
//            Parameters:
//    location - The location used to resolve relative paths for the root object, or null if the location is not known.
//    resources - The resources used to localize the root object, or null if the root object was not localized.

    //Populating the table with data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<User>> task = new getAllUsers();

        //Bind the call results to the tableview's items property
        //We bind the result of the task to the tableview's items property
        selectUserTableView.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();
    }

    class getAllUsers extends Task {

        @Override
        //Return an observable list; the observable list notices changes in the data and updates itself, that's the benefit
        public ObservableList<User> call() throws Exception {
            return FXCollections.observableArrayList
                    //If needed, initialize the task with values required to perform the action (enter variables for the query)
                    //Get the singleton class instance, call the queryArtists method, which runs the SQL query
                    /**
                     * Getting the employee number of the private User object in the UserSession Singletion class
                     * #01: get the query through the singleton instance of the Datasource
                     * #02: get the input for the query thorough the singleton of the UserSession,
                     * which has an instance of the User class and can be set for the values returned by query
                     */
                            (Datasource.getDatasource_instance().selectUserFromAllUsers());
        }
    }


    //Select user method - get the selected User item from the table and return it
    @FXML
    public void selectedUser() {
        final User selectedUser = (User) selectUserTableView.getSelectionModel().getSelectedItem();
        SelectedUser.getSelectedUser_instance().setUser(selectedUser);
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", selectUserSelectUser, getClass());
    }

    //Cancel method - return to previous scene
    @FXML
    void cancel() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", selectUserCancel, getClass());
    }

    @FXML
    void createUser () {

        Utility.changeScene("/TimeOffManagement_Main_Package/gui/user_admin_create_user.fxml", selectUserCreateUser, getClass());
    }

    @FXML
    void updateUser () {
        //Get the selected item into the right kind of object
        final User selectedUser = (User) selectUserTableView.getSelectionModel().getSelectedItem();
        //Get the above object into the Singleton class instance
        SelectedUser.getSelectedUser_instance().setUser(selectedUser);
        //Change the scene
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/user_admin_update_user.fxml", selectUserUpdate, getClass());
    }

    @FXML
    public void deleteUser() {

        final User selectedUser = (User) selectUserTableView.getSelectionModel().getSelectedItem();

//        Task<Boolean> task = new Task<Boolean>() {
//            @Override
//            protected Boolean call() throws Exception {
//                return Datasource.getDatasource_instance().deleteUser
//                        (
//                                selectedUser.getIdUser()
//                        );
//            }
//        };
//        new Thread(task).start();

        try {
            Datasource.getDatasource_instance().deleteUser
                    (
                            selectedUser.getIdUser()
                    );
        } catch (SQLException throwables) {
            Utility.infoBox("Insert user failed!", null, "System message");
        }

        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", selectUserDeleteUser, getClass());

        Utility.infoBox("Insert user was successful!", null, "System message");

    }

}
