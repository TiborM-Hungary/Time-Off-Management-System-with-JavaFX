package TimeOffManagement_Main_Package.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.session.SelectedUser;
import TimeOffManagement_Main_Package.session.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controls update of users
 */

public class UserUpdateUserController implements Initializable {

    //Make references to the GUI controls
    @FXML
    private TextField updateUserEmployeeNumber;

    @FXML
    private TextField updateUserEmployeeName;

    @FXML
    private TextField updateUserManagerEmployeeNumber;

    @FXML
    private TextField updateUserManagerName;

    @FXML
    private ComboBox updateUserSecurityRole;
    ObservableList<String> updateUserSecurityRoleOptions = FXCollections.observableArrayList
            ("user", "manager", "administrator");

    @FXML
    private ComboBox updateUserStatus;
    ObservableList<String> updateUserStatusOptions = FXCollections.observableArrayList
            ("Active", "Inactive");

    @FXML
    private TextField updateUserDaysTaken;

    @FXML
    private TextField updateUserDaysForTheYear;

    @FXML
    private TextField updateUserUsername;

    @FXML
    private TextField updateUserPassword;

    @FXML
    private Button updateUserUpdateUser;

    @FXML
    private Button updateUserCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateUserEmployeeNumber.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getEmployeeNumber()));
        updateUserEmployeeName.setText(SelectedUser.getSelectedUser_instance().getUser().getEmployeeName());
        updateUserManagerEmployeeNumber.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getManagerEmployeeNumber()));
        updateUserManagerName.setText(SelectedUser.getSelectedUser_instance().getUser().getManagerName());
        updateUserDaysTaken.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getUserDaysTaken()));
        updateUserDaysForTheYear.setText(String.valueOf(SelectedUser.getSelectedUser_instance().getUser().getUserDaysForYear()));
        updateUserUsername.setText(SelectedUser.getSelectedUser_instance().getUser().getUserName());
        updateUserPassword.setText(SelectedUser.getSelectedUser_instance().getUser().getPassword());
        updateUserSecurityRole.setItems(updateUserSecurityRoleOptions);
        updateUserStatus.setItems(updateUserStatusOptions);
    }

    @FXML
    public void updateUser() {

        //Get current time -- use this for Creation date and Latest Change Date
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Run a query that updates the current entry
//        Task<Integer> task = new Task<>() {
//            @Override
//            protected Integer call() throws Exception {
//                return Datasource.getDatasource_instance().updateUser(
//                        Integer.parseInt(updateUserEmployeeNumber.getText()),
//                        updateUserEmployeeName.getText(),
//                        Integer.parseInt(updateUserManagerEmployeeNumber.getText()),
//                        updateUserManagerName.getText(),
//                        updateUserSecurityRole.getValue().toString(),
//                        updateUserStatus.getValue().toString(),
//                        timeStamp,
//                        UserSession.getUserSession_instance().getUser().getEmployeeName(),
//                        Integer.parseInt(updateUserDaysForTheYear.getText()),
//                        Integer.parseInt(updateUserDaysTaken.getText()),
//                        Integer.parseInt(updateUserDaysForTheYear.getText()),
//                        updateUserUsername.getText(),
//                        updateUserPassword.getText(),
//                        SelectedUser.getSelectedUser_instance().getUser().getIdUser()
//                );
//            }
//        };
//        //Start the task
//        new Thread(task).start();

        try {
            Datasource.getDatasource_instance().updateUser(
                    Integer.parseInt(updateUserEmployeeNumber.getText()),
                    updateUserEmployeeName.getText(),
                    Integer.parseInt(updateUserManagerEmployeeNumber.getText()),
                    updateUserManagerName.getText(),
                    updateUserSecurityRole.getValue().toString(),
                    updateUserStatus.getValue().toString(),
                    timeStamp,
                    UserSession.getUserSession_instance().getUser().getEmployeeName(),
                    Integer.parseInt(updateUserDaysForTheYear.getText()),
                    Integer.parseInt(updateUserDaysTaken.getText()),
                    Integer.parseInt(updateUserDaysForTheYear.getText()),
                    updateUserUsername.getText(),
                    updateUserPassword.getText(),
                    SelectedUser.getSelectedUser_instance().getUser().getIdUser()
            );
        } catch (SQLException throwables) {
            Utility.infoBox("Update user failed!", null, "System message");
        }

        Utility.infoBox("Update user was successful!", null, "System message");

        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", updateUserUpdateUser, getClass());

    }

    @FXML
    void cancel() {
        Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", updateUserCancel, getClass());
    }

}
