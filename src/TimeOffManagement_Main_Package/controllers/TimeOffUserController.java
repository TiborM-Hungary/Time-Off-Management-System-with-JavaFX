package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.datasource.TimeOff;
import TimeOffManagement_Main_Package.datasource.User;
import TimeOffManagement_Main_Package.session.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeOffUserController implements Initializable {

    //Reference to the TableView on the time_off_user.fxml
    @FXML
    private TableView table_user_list_time_off;

    @FXML
    private Button userButtonRequestTimeOff;

    @FXML
    private Button userButtonListAllTimeOff;

    @FXML
    private Button userButtonListTimeOffBetweenDates;

    @FXML
    DatePicker datepicker_start_of_leave;

    @FXML
    DatePicker datepicker_end_of_leave;

    @FXML
    Label userLabelDaysAvailable;

    //Populating the table with data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<TimeOff>> task = new getAllTimeOffs();

        //Bind the call results to the tableview's items property
        //We bind the result of the task to the tableview's items property
        table_user_list_time_off.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();

        //Load the number of days available onto the userLabelDaysAvailable
        Integer daysAvailable = UserSession.getUserSession_instance().getUser().getUserDaysAvailable();
        userLabelDaysAvailable.setText(daysAvailable.toString());
    }

    //#03: Implement the Task.call() to implement the action
    @FXML
    public void listallTimeOffs() {
        //Creating a task, that gets back the data, using the call method from the class
        Task<ObservableList<TimeOff>> task = new getAllTimeOffs();

        //#04: Bind the call results to the tableview's items property
        //We bind the result of the task to the tableview's items property
        table_user_list_time_off.itemsProperty().bind(task.valueProperty());

        //start the task
        new Thread(task).start();
    }

    //#01: Create a class/task (class that extends Task) to perform the database function: query,insert..
    class getAllTimeOffs extends Task {

        @Override
        //Return an observable list; the observable list notices changes in the data and updates itself, that's the benefit
        public ObservableList<TimeOff> call() throws Exception {
            return FXCollections.observableArrayList
                    //#02: If needed, initialize the task with values required to perform the action (enter variables for the query)
                    //Get the singleton class instance, call the queryArtists method, which runs the SQL query
                    /**
                     * Getting the employee number of the private User object in the UserSession Singletion class
                     * #01: get the query through the singleton instance of the Datasource
                     * #02: get the input for the query thorough the singleton of the UserSession,
                     * which has an instance of the User class and can be set for the values returned by query
                     */
                            (Datasource.getDatasource_instance().leavesAll(UserSession.getUserSession_instance().getUser().getEmployeeNumber()));
        }
    }

    @FXML
    public void listTimeOffsBetweenDates() {
        Task<ObservableList<TimeOff>> task = new getimeOffsBetweenDates();
        table_user_list_time_off.itemsProperty().bind(task.valueProperty());
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
                                    UserSession.getUserSession_instance().getUser().getEmployeeNumber()
                            )
                    );
        }
    }


    @FXML
    public void requestTimeOff() {
        /**
         * Get the 2 dates from the DatePickers
         * Calculate the weekdays between them
         * Check, if a time off is already exist for that period
         * Make the update to the DB
         */

        //Get current time
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Get dates back from the 2 datepicker on the UI
        LocalDate startDate = datepicker_start_of_leave.getValue();
        LocalDate endDate = datepicker_end_of_leave.getValue();

        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        if (startDate.compareTo(endDate) > 0) {
            Utility.infoBox("End date is prior to Start Date! Please change it!", null, "Error in request");
            return;
        }

        final long weekDaysBetween = startDate.datesUntil(endDate)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count();

        //Check to see, if user has enough days
        if (UserSession.getUserSession_instance().getUser().getUserDaysAvailable() < weekDaysBetween) {
            Utility.infoBox("Not enough days, please check the dates", null, "Error in request");
            return;
        }

        try {
            Datasource.getDatasource_instance().insertTimeOff
                    (
                            UserSession.getUserSession_instance().getUser().getEmployeeNumber(),
                            UserSession.getUserSession_instance().getUser().getEmployeeName(),
                            UserSession.getUserSession_instance().getUser().getManagerEmployeeNumber(),
                            UserSession.getUserSession_instance().getUser().getManagerName(),
                            datepicker_start_of_leave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            datepicker_end_of_leave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            "Pending approval",
                            timeStamp,
                            timeStamp,
                            UserSession.getUserSession_instance().getUser().getEmployeeName(),
                            weekDaysBetween
                    );

        } catch (SQLException e) {
            Utility.infoBox("Insert time off failed!", null, "System message");
        }

        //Update user available days
        //Get new number of days available

        User givenEmployee = Datasource.getDatasource_instance().specificUserEmployeeNumber(UserSession.getUserSession_instance().getUser().getEmployeeNumber());

        long newDaysAvailable = givenEmployee.getUserDaysAvailable() - weekDaysBetween;
        int newDaysAvailableForQuery = (int) newDaysAvailable;

        //Run a query that updates the current entry
        try {
            Datasource.getDatasource_instance().updateUserDaysAvailableBasedOnUserID(
                    UserSession.getUserSession_instance().getUser().getIdUser(),
                    newDaysAvailableForQuery
            );
            Utility.infoBox("Task ran successfully!", null, "System message");
        } catch (SQLException e) {
            Utility.infoBox("Task failed!", null, "System message");
        }

        //Refresh the table
        listallTimeOffs();

        //Load the number of days available onto the userLabelDaysAvailable

        givenEmployee = Datasource.getDatasource_instance().specificUserEmployeeNumber(UserSession.getUserSession_instance().getUser().getEmployeeNumber());

        Integer daysAvailable = newDaysAvailableForQuery;
        userLabelDaysAvailable.setText(String.valueOf(givenEmployee.getUserDaysAvailable()));

    }
}
