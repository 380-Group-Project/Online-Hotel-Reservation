package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ReviewController {

    @FXML
    private Label myLabel;

    @FXML
    private Button confirm;

    private Room selectedRoom;
    private static LocalDate startDate;
    private static LocalDate endDate;
    static java.sql.Date sqlDateStart;
    static java.sql.Date sqlDateEnd;

    SceneController change = new SceneController();

    Reservations reservations = new Reservations();

    public void initialize() {

        // Create a variable
        String message = "You have selected ";

        // Set the text of the Label to the value of the variable
        myLabel.setText(message);

        confirm.setOnAction(event -> {
            reservations.reserve(selectedRoom.getRoomNumber(), "John Smith", selectedRoom.getBedNum(), sqlDateStart, sqlDateEnd);
            System.out.println("SQL Date value: " + sqlDateStart.toString());
            System.out.println("SQL Date value: " + sqlDateEnd.toString());
            loadHome();

        });
    }

    public void setRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
        output();
    }

    public static void setDates(LocalDate start, LocalDate end){
        startDate = start;
        endDate = end;
        sqlDateStart = java.sql.Date.valueOf(start);
        sqlDateEnd = java.sql.Date.valueOf(end);
    }

    public void output(){
        String message = "You have selected " +
                "\nRoom Type: " + selectedRoom.getRoomType() +
                "\nRoom Number: " + selectedRoom.getRoomNumber() +
                "\nBed Number: " + selectedRoom.getBedNum() +
                "\nPrice Number: " + selectedRoom.getPrice()+
                "\nstarting on " + startDate.toString() +
                "\nending on " + endDate.toString();

        // Set the text of the Label to the value of the variable
        myLabel.setText(message);
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

    public void switchToHome(ActionEvent event) throws IOException {

        change.switchToHome(event);
    }

}
