package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.datasource.TimeOff;
import TimeOffManagement_Main_Package.session.SelectedUser;
import TimeOffManagement_Main_Package.session.TimeOffSession;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class TimeOffAdminController {

    /**
     * Handles updates by admins on Time Offs
     */

    @FXML
    private TableView tableViewTimeOffAdmin;

    @FXML
    private DatePicker datepicker_start_of_leave;

    @FXML
    private DatePicker datepicker_end_of_leave;

    @FXML
    private Button timeOffAdminButtonListAllTimeOff;

    @FXML
    private Button timeOffAdminButtonListBetweenDates;

    @FXML
    private Button timeOffAdminButtonDeleteTimeOff;

    @FXML
    private Button timeOffAdminButtonUpdateTimeOff;

    @FXML
    private Button timeOffAdminButtonSelectUser;

    @FXML
    private Button timeOffAdminButtonUserMode;


    //Select user
    @FXML
    public void selectUser() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin_select_user.fxml", timeOffAdminButtonSelectUser, getClass());
    }

    @FXML
    public void switchToUserScene() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_user.fxml", timeOffAdminButtonUserMode, getClass());
    }

    //Get the timeoff's off the selected user via the List Time Offs Button
    @FXML
    public void listSelectedUserTimeOffs() {
        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<TimeOff>> task = new getAllSelectedUserTimeOffs();

        //Bind the call results to the tableview's items property
        //We bind the result of the task to the tableview's items property
        tableViewTimeOffAdmin.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();
    }

    //    class getAllSelectedUserTimeOffs extends Task<ObservableList<TimeOff>> {
    class getAllSelectedUserTimeOffs extends Task {

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
                            (Datasource.getDatasource_instance().leavesAll(
                                    SelectedUser.getSelectedUser_instance().getUser().getEmployeeNumber()));
        }
    }

    @FXML
    public void listSelectedUserTimeOffsBetweenDates() {
        Task<ObservableList<TimeOff>> task = new TimeOffAdminController.getimeOffsBetweenDates();

        tableViewTimeOffAdmin.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();

    }

    class getimeOffsBetweenDates extends Task {

        @Override
        public ObservableList<TimeOff> call() throws Exception {
            return FXCollections.observableArrayList
                    (Datasource.getDatasource_instance().leavesBetweenTwoDates
                            (
                                    datepicker_start_of_leave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    datepicker_end_of_leave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    SelectedUser.getSelectedUser_instance().getUser().getEmployeeNumber()
                            )
                    );
        }
    }

    @FXML
    public void updateTimeOff() {
        //Get the selected item into the right kind of object
        final TimeOff selectedTimeOff = (TimeOff) tableViewTimeOffAdmin.getSelectionModel().getSelectedItem();
        //Get the above object into the Singleton class instance
        TimeOffSession.getTimeOffSession_instance().setTimeOff(selectedTimeOff);
        //Change the scene - other scene will handle the update
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin_update_time_off.fxml", timeOffAdminButtonUpdateTimeOff, getClass());
    }

    @FXML
    public void deleteTimeOff() {

        final TimeOff selectedTimeOff = (TimeOff) tableViewTimeOffAdmin.getSelectionModel().getSelectedItem();

        try {
            Datasource.getDatasource_instance().deleteTimeOff
                    (
                            selectedTimeOff.getEntryNumber()
                    );
            Utility.infoBox("Task ran successfully!", null, "System message");

        } catch (SQLException e) {
            Utility.infoBox("Delete time off failed!", null, "System message");
            return;
        }

        listSelectedUserTimeOffs();
    }
}
