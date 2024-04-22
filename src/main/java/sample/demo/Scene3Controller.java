package sample.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Seng Dieng
 *
 */

public class Scene3Controller {

    @FXML
    private ListView<String> listView;
    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private String selectedOption;
    private Room selectedRoom;
    SceneController change = new SceneController();

    public void initialize() {

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < rooms.size()) {
                    selectedRoom = rooms.get(selectedIndex);
                    showAlert(selectedRoom);
                }
            }
        });
    }


    /**
     * Filters the options from the selected option
     *
     * @param option the selected option
     */

    public void setOption(String option) {
        this.selectedOption = option;
        connectToDatabase();
    }



    /**
     * Retrieves data from the database
     *
     */

    private void connectToDatabase() {
        String url = "jdbc:sqlite:hotel.db";
        String query = "SELECT * FROM room";

        if (selectedOption != null && !selectedOption.isEmpty()) {
            query += " WHERE room_type = ?";
        }

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (selectedOption != null && !selectedOption.isEmpty()) {
                statement.setString(1, selectedOption);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt("Room_number"),
                        resultSet.getString("Room_Type"),
                        resultSet.getInt("Bednum"),
                        resultSet.getDouble("Price"),
                        resultSet.getBoolean("Reserved")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Populate ListView
        for (Room room : rooms) {
            listView.getItems().add(room.toString());
        }
    }

    /**
     * Shows the alert
     *
     * @param room the selected room from the selection
     */

    private void showAlert(Room room) {
        Alert alert = getAlert(room);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        // Show the alert and wait for user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                nextScene();
            }
            if(response == ButtonType.NO){
                selectedRoom = null;
            }
        });
    }

    /**
     * Sets the alert message
     *
     * @param room the room selected
     */

    private static Alert getAlert(Room room) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Selected Room");
        alert.setContentText(
                "Are you sure you want to select this room?\n\n" +
                        "Room Number: " + room.getRoomNumber() +
                        "\nRoom Type: " + room.getRoomType() +
                        "\nBed Num: " + room.getBedNum() +
                        "\nPrice: " + room.getPrice() +
                        "\nReserved: " + room.isReserved()
        );
        return alert;
    }


    /**
     * Goes to the next scene
     *
     */
    private void nextScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewTest.fxml"));
            Parent root = loader.load();

            ReviewController reviewController = loader.getController();
            reviewController.setRoom(selectedRoom);

            Stage stage = (Stage) listView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Changes the page to the scene1
     *
     * @param event the current page
     */
    public void switchToScene1(ActionEvent event) throws IOException {

        change.switchToScene1(event);

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
