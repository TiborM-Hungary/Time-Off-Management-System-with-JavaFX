package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.session.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UserCreateUserController implements Initializable {

    /**
     * Create a User object
     * Set the properties of the User object with the data provided on the GUI
     * Insert the user into the DB
     */

    //Make references to the GUI controls
    @FXML
    private TextField newUserEmployeeNumber;

    @FXML
    private TextField newUserEmployeeName;

    @FXML
    private TextField newUserManagerEmployeeNumber;

    @FXML
    private TextField newUserManagerName;

    @FXML
    private ComboBox newUserSecurityRole;
    ObservableList<String> newUserSecurityRoleOptions = FXCollections.observableArrayList("user", "manager", "administrator");

    @FXML
    private ComboBox newUserStatus;
    ObservableList<String> newUserStatusOptions = FXCollections.observableArrayList("Active", "Inactive");

    @FXML
    private TextField newUserDaysTaken;

    @FXML
    private TextField newUserDaysForTheYear;

    @FXML
    private TextField newUserUsername;

    @FXML
    private TextField newUserPassword;

    @FXML
    private Button newUserCreateUser;

    @FXML
    private Button newUserCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newUserSecurityRole.setItems(newUserSecurityRoleOptions);
        newUserStatus.setItems(newUserStatusOptions);
    }

    @FXML
    public void createUser() {

        //Get current time -- use this for Creation date and Latest Change Date
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Run a query that updates the current entry
//        Task<Integer> task = new Task<>() {
//            @Override
//            protected Integer call() throws Exception {
//                return Datasource.getDatasource_instance().insertUser(
//                        Integer.parseInt(newUserEmployeeNumber.getText()),
//                        newUserEmployeeName.getText(),
//                        Integer.parseInt(newUserManagerEmployeeNumber.getText()),
//                        newUserManagerName.getText(),
//                        newUserSecurityRole.getValue().toString(),
//                        newUserStatus.getValue().toString(),
//                        timeStamp,
//                        timeStamp,
//                        UserSession.getUserSession_instance().getUser().getEmployeeName(),
//                        Integer.parseInt(newUserDaysForTheYear.getText()),
//                        Integer.parseInt(newUserDaysTaken.getText()),
//                        Integer.parseInt(newUserDaysForTheYear.getText()),
//                        newUserUsername.getText(),
//                        newUserPassword.getText()
//                        );
//            }
//        };
//        //Start the task
//        new Thread(task).start();

        try {
            Datasource.getDatasource_instance().insertUser(
                    Integer.parseInt(newUserEmployeeNumber.getText()),
                    newUserEmployeeName.getText(),
                    Integer.parseInt(newUserManagerEmployeeNumber.getText()),
                    newUserManagerName.getText(),
                    newUserSecurityRole.getValue().toString(),
                    newUserStatus.getValue().toString(),
                    timeStamp,
                    timeStamp,
                    UserSession.getUserSession_instance().getUser().getEmployeeName(),
                    Integer.parseInt(newUserDaysForTheYear.getText()),
                    Integer.parseInt(newUserDaysTaken.getText()),
                    Integer.parseInt(newUserDaysForTheYear.getText()),
                    newUserUsername.getText(),
                    newUserPassword.getText()
            );
        } catch (SQLException throwables) {
            Utility.infoBox("Create user failed!", null, "System message");
        }


        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", newUserCreateUser, getClass());

        Utility.infoBox("Create user was successful!", null, "System message");

    }

    @FXML
    void cancel() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", newUserCancel, getClass());
    }
}
