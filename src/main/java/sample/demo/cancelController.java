package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;


/**
 *This class exists primarily to control the cancel scene.
 * @author Seng Dieng
 */


public class cancelController {

    @FXML
    private TextField idField;

    @FXML
    private Button enterButton;

    Reservations reservations = new Reservations();
    SceneController change = new SceneController();

    @FXML
    private void initialize() {
        idField.setOnKeyPressed(event -> {
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

        if (!idText.matches("\\d+")) {
            showAlert("Error", "ID must contain only numbers");
            return;
        }

        int id = Integer.parseInt(idText);

        if(reservations.cancel(id)){
            showAlert("Success", "Reservation with ID " + id + " has been successfully canceled.");
        }
        else {
            showAlert("Error", "No record found with ID: " + id);
        }


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Changes the page to the Homepage
     *
     * @param event the current page
     */

    public void switchToHome(ActionEvent event) throws IOException {
        change.switchToHome(event);
    }

    /**
     * Changes the id value in ReviewController
     */
    public void cancelCurrent(){
        ReviewController.selectedRoom = null;
        if(ReviewController.id != -1){
            ReviewController.id = -1;
        }
    }
}

