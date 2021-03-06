package TimeOffManagement_Main_Package;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import TimeOffManagement_Main_Package.datasource.Datasource;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //We would like to display data, when the application starts
    //Therefore we are making the connection when the app opens, and closing the connection when the app stops
    //Overriding the init and stop methods, to open and close the DB when the app is opened or closed
    @Override
    public void init() throws Exception {
        super.init();
        //Datasource.getInstance(). is how we refer to the methods available to the our Datasource (singleton) class
        if (!Datasource.getDatasource_instance().open()) {
            //This is normally a pop-up dialogue instead of a System out, but not coding it now..
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    //Overriding to close connection upon exiting the application
    @Override
    public void stop() throws Exception {
        super.stop();
        //Datasource.getInstance(). is how we refer to the methods available to the our Datasource (singleton) class
        Datasource.getDatasource_instance().close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Main flow
         */

        //Loader -> can load the FXML on a Parent
        //Parent -> can be loaded with the FXML via the Loader (this is a Scene?)
        //Scene -> can be set with the Parent that has loaded the FXML
        //Stage -> can be set to display the Scene
        //.show -> Stage can be made visible (invisible by default)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/login_page.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ASSH - TimeOff Management System V1.0");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();

    }
}
