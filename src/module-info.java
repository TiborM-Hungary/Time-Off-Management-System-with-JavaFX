module Time.Off.Management.System.with.JavaFX {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.sql.rowset;

    //Packages has to be opened
    //https://stackoverflow.com/questions/53035454/javafx-module-javafx-graphics
    opens TimeOffManagement_Main_Package;
    opens TimeOffManagement_Main_Package.gui;
    opens TimeOffManagement_Main_Package.datasource;
    opens TimeOffManagement_Main_Package.controllers;
    opens TimeOffManagement_Main_Package.session;

}


