package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class cancel {

    @FXML
    private TextField idField;

    @FXML
    private Button enterButton;

    Reservations reservations = new Reservations();
    SceneController change = new SceneController();

    @FXML
    private void initialize() {
        idField.setOnKeyPressed(event -> {
            // Check if the pressed key is Enter
            if (event.getCode() == KeyCode.ENTER) {
                handleEnter();
            }
        });

        enterButton.setOnAction(event -> handleEnter());
    }

    private void handleEnter() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            showAlert("Error", "ID is empty");
            return;
        }

        int id = Integer.parseInt(idText);

        reservations.cancel(id);


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        change.switchToHome(event);
    }
}

