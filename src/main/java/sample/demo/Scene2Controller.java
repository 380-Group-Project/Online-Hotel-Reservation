package sample.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.*;

public class Scene2Controller {

    @FXML
    private ListView<String> listView;
    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private Room selectedRoom;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void initialize() {
        // Connect to the database and retrieve data
        connectToDatabase();

        // Add listener to ListView selection
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



    private void connectToDatabase() {
        String url = "jdbc:sqlite:hotel.db"; // Change this to your SQLite database file path
        String query = "SELECT * FROM room";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getString("Room_number"),
                        resultSet.getString("Room_Type"),
                        resultSet.getInt("Bednum"),
                        resultSet.getDouble("Price"),
                        resultSet.getBoolean("Reserved")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Populate ListView
        for (Room room : rooms) {
            listView.getItems().add(room.toString());
        }
    }

    private void showAlert(Room room) {
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

        // Customize buttons
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        // Show the alert and wait for user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                navigateToAnotherScene();
            }
        });
    }

    private void navigateToAnotherScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
            Parent root = loader.load();

            // Show the new scene
            Stage stage = (Stage) listView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Another Scene");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene1(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(SceneTest.class.getResource("Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }

    public void switchToHome(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(SceneTest.class.getResource("sample.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }




    static class Room {
        private String roomNumber;
        private String roomType;
        private int bedNum;
        private double price;
        private boolean reserved;

        public Room(String roomNumber, String roomType, int bedNum, double price, boolean reserved) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.bedNum = bedNum;
            this.price = price;
            this.reserved = reserved;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public int getBedNum() {
            return bedNum;
        }

        public double getPrice() {
            return price;
        }

        public boolean isReserved() {
            return reserved;
        }

        @Override
        public String toString() {
            return "Room Number: " + roomNumber +
                    ", Room Type: " + roomType +
                    ", Bed Num: " + bedNum +
                    ", Price: " + price +
                    ", Reserved: " + reserved;
        }
    }
}
