package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.datasource.TimeOff;
import TimeOffManagement_Main_Package.datasource.User;
import TimeOffManagement_Main_Package.session.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TimeOffManagerController implements Initializable {

    @FXML
    private Button managerButtonSwitchViewToUser;

    @FXML
    private Button managerButtonPendingTimeOffs;

    @FXML
    private Button managerButtonApprove;

    @FXML
    private Button managerButtonDecline;

    @FXML
    private TableView table_manager_list_time_off;

    //Populating the table with data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<TimeOff>> task = new getAllPendingApprovalTimeOffs();

        //Bind the call results to the tableview's items property
        //We bind the result of the task to the tableview's items property
        table_manager_list_time_off.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();
    }

    class getAllPendingApprovalTimeOffs extends Task {
        @Override
        //Return an observable list; the observable list notices changes in the data and updates itself, that's the benefit
        public ObservableList<TimeOff> call() throws Exception {
            return FXCollections.observableArrayList
                    //If needed, initialize the task with values required to perform the action (enter variables for the query)
                    //Get the singleton class instance, call the queryArtists method, which runs the SQL query
                    /**
                     * Getting the employee number of the private User object in the UserSession Singletion class
                     * #01: get the query through the singleton instance of the Datasource
                     * #02: get the input for the query thorough the singleton of the UserSession,
                     * which has an instance of the User class and can be set for the values returned by query
                     */
                            (Datasource.getDatasource_instance().toBeApproved(UserSession.getUserSession_instance().getUser().getEmployeeNumber()));
        }
    }


    /**
     * List all time offs for given manager
     * sessionUser.secRole = Manager and is listed as manager for the employee
     */

    @FXML
    public void listManagerAllEmployeeTimeOffs() {
        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<TimeOff>> task = new getAllManagerEmployeeTimeOffs();

        //Bind the call results to the table view's items property
        //We bind the result of the task to the table view's items property
        table_manager_list_time_off.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();
    }

    class getAllManagerEmployeeTimeOffs extends Task {
        @Override
        //Return an observable list; the observable list notices changes in the data and updates itself, that's the benefit
        public ObservableList<TimeOff> call() throws Exception {
            return FXCollections.observableArrayList
                    //If needed, initialize the task with values required to perform the action (enter variables for the query)
                    //Get the singleton class instance, call the queryArtists method, which runs the SQL query
                    /**
                     * Getting the employee number of the private User object in the UserSession Singletion class
                     * #01: get the query through the singleton instance of the Datasource
                     * #02: get the input for the query thorough the singleton of the UserSession,
                     * which has an instance of the User class and can be set for the values returned by query
                     */
                            (Datasource.getDatasource_instance().allEmployeeTimeOffs(UserSession.getUserSession_instance().getUser().getEmployeeNumber()));
        }
    }

    @FXML
    public void approveTimeOff() {

        //Get the currently selected item
        final TimeOff timeOff = (TimeOff) table_manager_list_time_off.getSelectionModel().getSelectedItem();

        //Needs the date of latest change, changed by, Entry number of time off
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Run a query that updates the current entry

        try {
            Datasource.getDatasource_instance().approveTimeOff
                    (
                            timeOff.getEntryNumber(),
                            timeStamp,
                            UserSession.getUserSession_instance().getUser().getEmployeeName()
                    );
            Utility.infoBox("Approval was successful!", null, "System message");

        } catch (SQLException throwables) {
            Utility.infoBox("Approval failed!", null, "System message");
        }

        //Refresh the list once the update is done
        listManagerAllEmployeeTimeOffs();

    }

    @FXML
    public void declineTimeOff() {

        //Get the currently selected item
        final TimeOff timeOff = (TimeOff) table_manager_list_time_off.getSelectionModel().getSelectedItem();

        //Needs the date of latest change, changed by, Entry number of time off
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        try {
            Datasource.getDatasource_instance().declineTimeOff
                    (
                            timeOff.getEntryNumber(),
                            timeStamp,
                            UserSession.getUserSession_instance().getUser().getEmployeeName()
                    );
        } catch (SQLException throwables) {
            Utility.infoBox("Decline failed!", null, "System message");
        }

        //Calc the days available to be set on the employee profile
        int durationOfDeclinedLeave = timeOff.getDurationOfLeave();

        //Get the given employees days available
        User givenEmployee = Datasource.getDatasource_instance().specificUserEmployeeNumber(timeOff.getEmployeeNumber());

        int restoredNumberOfDays = givenEmployee.getUserDaysAvailable() + durationOfDeclinedLeave;

        //Run a query that updates the current entry
        try {
            Datasource.getDatasource_instance().updateUserDaysAvailableBasedOnEmployeeNumber
                    (
                            timeOff.getEmployeeNumber(),
                            restoredNumberOfDays
                    );
        } catch (SQLException e) {
            Utility.infoBox("Task failed!", null, "System message");
        }

        //Refresh the list once the update is done
        listManagerAllEmployeeTimeOffs();
        Utility.infoBox("Task ran successfully!", null, "System message");
    }

    @FXML
    public void switchToUserScene() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_user.fxml", managerButtonSwitchViewToUser, getClass());
    }


}
