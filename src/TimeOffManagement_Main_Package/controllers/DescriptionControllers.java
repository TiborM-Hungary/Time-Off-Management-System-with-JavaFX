package TimeOffManagement_Main_Package.controllers;

/**
 * This is the event handler class (event handlers are also called event listeners (mainly in android dev.).
 * This class contains the code, when an event is raised (e.g. button is clicked),
 * what code should be executed.
 *
 * The event handler needs to be associated with the object that fires the event (e.g. the button that gets clicked). *
 *
 * In order to manipulate the objects in our FXML file, we need to assign a fx:id to the object such as text fields.
 * This way we can reference them in our code, in the controller class.
 * Also, you need to create an instance variable in controller class for it.
 * IMPORTANT: the variable name in the controller HAS TO BE SAME as the fx:id in the FXML file and annotate it with @FMXL.
 * e.g.
 * @FXML
 * private TextField nameField;
 *
 */

/**
 * Steps of the doing the DB query method:
 *
 * #01: Create a class/task (class that extends Task) to perform the database function: query,insert..
 * #02: If needed, initialize the task with values required to perform the action (enter variables for the query)
 * #03: Implement the Task.call() to implement the action
 * #04: Bind the call results to the tableview's items property
 * #05: Invoke the task
 */

public class DescriptionControllers {

    //Create a private variable, to handle the data handled by the control in the FXML file
    //IMPORTANT: name of the variable here, has to match the fx:id in the FXML file
    //IMPORTANT: need to do the import @FXML, that will tell Java to associate the variable here, with the one in the FXML file
    //doing that, it will search the fx:id tags for the variable name we added here


//    //We don't want the buttons to show by default
//    //We do 2 things:
//    //-use the initialize method, make them disappear
//    //-use the handleKeyReleased method below, to check if the text field still empty during the program run
//    @FXML
//    public void initialize() {
//        helloButton.setDisable(true);
//        byeButton.setDisable(true);
//    }

//    //Event Handler for key release -- we only want buttons to show, when there is character in the field
//    //we are adding this method to listed to atomic characters in the field
//    @FXML
//    public void handleKeyReleased() {
//        String text = nameField.getText();
//        //trim version is needed, in case we only add spaces
//        boolean disabledButtons = text.isEmpty() || text.trim().isEmpty();
//        helloButton.setDisable(disabledButtons);
//        byeButton.setDisable(disabledButtons);
//
//    }

}
