package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Scene2Controller {

    @FXML
    private RadioButton basic;

    @FXML
    private RadioButton family;

    @FXML
    private RadioButton suite;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button nextButton;

    private ToggleGroup toggleGroup;
    SceneController change = new SceneController();

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        basic.setToggleGroup(toggleGroup);
        family.setToggleGroup(toggleGroup);
        suite.setToggleGroup(toggleGroup);

        nextButton.setOnAction(event -> {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (selectedRadioButton != null && startDate != null && endDate != null) {
                ReviewController.setDates(startDate, endDate);
                String selectedOption = selectedRadioButton.getText();
                loadScene3(selectedOption);
            }
        });
    }

    private void loadScene3(String selectedOption) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
            Parent root = loader.load();

            Scene3Controller scene3Controller = loader.getController();
            scene3Controller.setOption(selectedOption);

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToHome(ActionEvent event) throws IOException{
        change.switchToHome(event);
    }
}
