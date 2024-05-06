package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 *This class exists to control the review scene.
 *
 * @author Seng Dieng
 *
 */

public class ReviewController {

    @FXML
    private Label myLabel;

    @FXML
    private Button confirm;

    @FXML
    private TextField name;
    @FXML
    private TextField email;

    @FXML
    public static int id = -1;

    public static Room selectedRoom;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static Date sqlDateStart;
    private static Date sqlDateEnd;

    SceneController change = new SceneController();

    Reservations reservations = new Reservations();

    public void initialize() {

        // Create a variable
        String message = "You have not selected a room.";

        // Set the text of the Label to the value of the variable
        myLabel.setText(message);

        confirm.setVisible(false);
        if(selectedRoom != null){
            output();
        }

        confirm.setOnAction(event -> {

            System.out.println("SQL Start Date value: " + sqlDateStart.toString());
            System.out.println("SQL End Date value: " + sqlDateEnd.toString());
            if (name.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Required");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your name");
                alert.showAndWait();
            }
            if (!name.getText().isEmpty() && id == -1){
                int generated_id = reservations.reserve(selectedRoom.getRoomNumber(), name.getText(), selectedRoom.getBedNum(), sqlDateStart, sqlDateEnd);
                System.out.println(generated_id);
                String text = "Successfully reserved, your id number is: " + generated_id;
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Success");
                dialog.setHeaderText(null);

                TextArea textArea = new TextArea(text);
                textArea.setEditable(false);
                textArea.setWrapText(true);

                dialog.getDialogPane().setContent(textArea);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();

                selectedRoom = null;
                loadHome();
            }
            if(!name.getText().isEmpty() && id != -1){
                reservations.update(id, selectedRoom.getRoomNumber(), name.getText(), selectedRoom.getBedNum(), sqlDateStart, sqlDateEnd);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated with the id number: " + id);
                alert.showAndWait();
                id = -1;
                selectedRoom = null;
                loadHome();
            }


        });
    }

    /**
     * Retrieves the selected room
     *
     * @param selectedRoom the room selected
     */

    public void setRoom(Room selectedRoom) {
        ReviewController.selectedRoom = selectedRoom;
        output();
    }

    /**
     * Retrieves the selected dates
     *
     * @param start the selected starting date
     * @param end the selected ending date
     */

    public static void setDates(LocalDate start, LocalDate end){
        startDate = start;
        endDate = end;
        sqlDateStart = Date.valueOf(start);
        sqlDateEnd = Date.valueOf(end);
    }

    public void setId(int id){
        ReviewController.id = id;
    }

    /**
     * Outputs the selected room's values
     *
     */

    public void output(){
        String message = "You have selected " +
                "\nRoom Type: " + selectedRoom.getRoomType() +
                "\nRoom Number: " + selectedRoom.getRoomNumber() +
                "\nBed Number: " + selectedRoom.getBedNum() +
                "\nPrice Number: " + selectedRoom.getPrice()+
                "\nstarting on " + startDate.toString() +
                "\nending on " + endDate.toString();

        myLabel.setText(message);
        confirm.setVisible(true);
    }

    private void loadHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) confirm.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
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
