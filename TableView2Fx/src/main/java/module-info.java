module leen.tableview2fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens leen.tableview2fx to javafx.fxml;
    exports leen.tableview2fx;
    exports controller;
    exports pojo;
}
