module org.Application {
    requires javafx.controls;
    requires javafx.fxml;

    requires opencsv;
    requires javafx.media;
    requires javacsv;

    opens org.Application to javafx.graphics, javafx.fxml;
    exports org.Application;
    opens org.Application.controller to javafx.fxml;
    exports org.Application.controller;
}