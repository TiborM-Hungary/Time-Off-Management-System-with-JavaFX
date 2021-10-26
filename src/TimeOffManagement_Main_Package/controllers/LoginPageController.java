package TimeOffManagement_Main_Package.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import TimeOffManagement_Main_Package.datasource.Datasource;
import TimeOffManagement_Main_Package.datasource.User;
import TimeOffManagement_Main_Package.session.UserSession;

import java.sql.SQLException;

public class LoginPageController {

    @FXML
    public void initialize() {
        loginButton.setDisable(true);
    }

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void LoginButtonClicked(ActionEvent event) throws SQLException {

        //Get the window of the login button
        Window loginWindow = loginButton.getScene().getWindow();

        //Pop-up error, if the username is not correct
        if (userNameField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, loginWindow, "Form Error!",
                    "Please enter your email id");
            return;
        }
        //Pop-up error, if the password is not correct
        if (passwordField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, loginWindow, "Form Error!",
                    "Please enter a password");

            return;
        }

        //Get login info into the variables
        String userName = userNameField.getText();
        String password = passwordField.getText();

        //Calling on the instance of the Datasource class, calling the function and passing in the variables
        User activeUser = Datasource.getDatasource_instance().userVerification(userName,password);

        if (activeUser == null) {
            Utility.infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            //Save the logged in user as the active user for the session (UserSession class);
            // in order to get it's properties later when needed
            UserSession.getUserSession_instance().setUser(activeUser);

            switch (UserSession.getUserSession_instance().getUser().getSecurityRole()) {
                case "user":
                    Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_user.fxml", loginButton, getClass());
                    break;

                case "manager":
                    Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_manager.fxml", loginButton, getClass());
                    break;

                case "administrator":
                    Utility.changeScene("/TimeOffManagement_Main_Package/gui/time_off_admin.fxml", loginButton, getClass());
                    break;
            }
        }
    }

    @FXML
    public void handleKeyReleased() {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        boolean disableButtons = userName.isEmpty() || userName.trim().isEmpty() ||
                                 password.isEmpty() || password.trim().isEmpty();
        loginButton.setDisable(disableButtons);
    }

}

