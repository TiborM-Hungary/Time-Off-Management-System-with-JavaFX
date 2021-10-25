package TimeOffManagement_Main_Package.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Utility {

    //Confirm correct login
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    //Display alert - wrong login info
    public static void showAlert(Alert.AlertType alertType, Window loginWindow, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(loginWindow);
        alert.show();
    }

    public static void changeScene(String resource, Control controller, Class clazz) {
        try {
            //Source:
            //https://stackoverflow.com/questions/17228487/javafx-location-is-not-set-error-message

            //Loader - loads the FXML (setting the resource)
            FXMLLoader loader = new FXMLLoader(clazz.getResource(resource));
            //Setting up the class loaders on the JVM - should be automatic, but have to tell the JVM what loader to use
            loader.setClassLoader(clazz.getClassLoader());
            //Gets the stage of the controller (button)
            Stage stage = (Stage) controller.getScene().getWindow();
            //Loader loads the FXML, which gives back the current FXML's root; the root gets setted into the scene
            Scene scene = new Scene(loader.load());
            //New scene gets setted onto the Stage
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
