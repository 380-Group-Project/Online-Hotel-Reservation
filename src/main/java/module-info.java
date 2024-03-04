module sample.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.demo to javafx.fxml;
    exports sample.demo;
}