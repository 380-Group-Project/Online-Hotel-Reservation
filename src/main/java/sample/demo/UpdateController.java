package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.IOException;

/**
 *This class exists to control the update scene.
 *
 * @author Seng Dieng
 *
 */

public class UpdateController {
    Reservations reservations = new Reservations();
    SceneController change = new SceneController();
    ReviewController reviewController = new ReviewController();


    @FXML
    private TextField text;


    /**
     * Handles when the enter button is clicked on
     */
    public void handleEnter() {
            int resId = Integer.parseInt(text.getText());
            ArrayList<Object> reservation = reservations.getReservation(resId);
            if (reservation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Reservation not found");
                alert.setContentText("No reservation found with ID: " + resId);
                alert.showAndWait();
            }
            else {
                Alert alert = getAlert(reservation);
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setOnAction(event -> {
                    reviewController.setId(resId);
                    nextScene();
                });

                alert.showAndWait();
            }
    }

    private static Alert getAlert(ArrayList<Object> reservation) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Details");
        alert.setHeaderText("Reservation Found");
        String content = "Reservation Number: " + reservation.get(0) + "\n" +
                "Room Number: " + reservation.get(1) + "\n" +
                "Name: " + reservation.get(2) + "\n" +
                "Guest Number: " + reservation.get(3) + "\n" +
                "Check-in Date: " + reservation.get(4) + "\n" +
                "Check-out Date: " + reservation.get(5) + "\n";
        alert.setContentText(content);
        return alert;
    }

    private void nextScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) text.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Changes the page to the Homepage
     *
     * @param event the current page
     */
    public void switchToHome(ActionEvent event) throws IOException {
        change.switchToHome(event);
    }

}
