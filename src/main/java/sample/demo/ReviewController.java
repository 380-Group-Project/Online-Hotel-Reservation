package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ReviewController {

    @FXML
    private Label myLabel;

    @FXML
    private Button confirm;

    @FXML
    private TextField name;
    @FXML
    private TextField email;

    private static Room selectedRoom;
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
            if (name != null){
                System.out.println(reservations.reserve(selectedRoom.getRoomNumber(), name.getText(), selectedRoom.getBedNum(), sqlDateStart, sqlDateEnd));
                selectedRoom = null;
                loadHome();
            }


        });
    }

    public void setRoom(Room selectedRoom) {
        ReviewController.selectedRoom = selectedRoom;
        output();
    }

    public static void setDates(LocalDate start, LocalDate end){
        startDate = start;
        endDate = end;
        sqlDateStart = Date.valueOf(start);
        sqlDateEnd = Date.valueOf(end);
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

    public void switchToHome(ActionEvent event) throws IOException {

        change.switchToHome(event);
    }

}
