module Test {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.swt;
    requires java.sql;

    exports main to javafx.graphics;
    /*exports model to javafx.graphics;
    exports view to javafx.graphics;*/
    exports controller to javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.base;
}