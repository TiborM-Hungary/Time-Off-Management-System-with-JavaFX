package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.session.SelectedUser;
import TimeOffManagement_Main_Package.session.TimeOffSession;
import TimeOffManagement_Main_Package.session.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Controls the update of timeo ffs
 */

public class TimeOffAdminUpdateController implements Initializable {

    @FXML
    private TextField timeOffAdminUpdateEmployeeID;

    @FXML
    private TextField timeOffAdminUpdateEmployeeName;

    @FXML
    private TextField timeOffAdminUpdateManagerEmployeeNumber;

    @FXML
    private TextField timeOffAdminUpdateManagerName;

    @FXML
    private DatePicker timeOffAdminUpdateStartOfLeave;

    @FXML
    private DatePicker timeOffAdminUpdateEndOfLeave;

    @FXML
    private ComboBox timeOffAdminUpdateLeaveStatus;
    ObservableList<String> data = FXCollections.observableArrayList("Pending Approval", "Approved", "Declined");

    @FXML
    private Button timeOffAdminUpdateButtonUpdate;

    @FXML
    private Button timeOffAdminUpdateButtonCancel;

//    Implement Initialize - initialize to pre-load data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timeOffAdminUpdateEmployeeID.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getEmployeeNumber()));

        timeOffAdminUpdateEmployeeName.setText(SelectedUser.getSelectedUser_instance().getUser().getEmployeeName());

        timeOffAdminUpdateManagerEmployeeNumber.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getManagerEmployeeNumber()));

        timeOffAdminUpdateManagerName.setText(SelectedUser.getSelectedUser_instance().getUser().getManagerName());

        //Combobox works!
        timeOffAdminUpdateLeaveStatus.setItems(data);
    }

    @FXML
    public void updateTimeOff () {

        //Get the selected dates from the DatePickers - Located needed for the calculation
        LocalDate startDate = timeOffAdminUpdateStartOfLeave.getValue();
        LocalDate endDate = timeOffAdminUpdateEndOfLeave.getValue();

        //Get current time
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Calculate the new weekday number
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        //The result of the weekDaysBetween is fed to the LAMBDA expression as "d",
        // then filter with formula:
        // if "d" doesn't contain the days set in the weekend Set, then count them
        final long weekDaysBetween = startDate.datesUntil(endDate)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count();

        try {
            Datasource.getDatasource_instance().updateTimeOff
                    (
                            TimeOffSession.getTimeOffSession_instance().getTimeOff().getEntryNumber(),
                            SelectedUser.getSelectedUser_instance().getUser().getEmployeeNumber(),
                            timeOffAdminUpdateStartOfLeave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            timeOffAdminUpdateEndOfLeave.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            timeOffAdminUpdateLeaveStatus.getValue().toString(),
                            timeStamp,
                            UserSession.getUserSession_instance().getUser().getEmployeeName(),
                            weekDaysBetween
                    );
            Utility.infoBox("Task ran successfully!", null, "System message");

        } catch (SQLException throwables) {
            Utility.infoBox("Update Time Off failed!", null, "System message");
        }

        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", timeOffAdminUpdateButtonUpdate, getClass());

    }

    @FXML
    public void cancel () {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", timeOffAdminUpdateButtonCancel, getClass());
    }

}
