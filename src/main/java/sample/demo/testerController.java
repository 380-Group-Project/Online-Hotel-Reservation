package sample.demo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;

public class testerController {

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private void initialize() {
        myChoiceBox.getItems().addAll("Select room type","Review Room", "Cancel Room");
        myChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                switchScene(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void switchScene(String sceneName) throws IOException {
        Stage stage = (Stage) myChoiceBox.getScene().getWindow();
        Parent root = null;

        switch (sceneName) {
            case "Select room type":
                root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
                break;
            case "Review Room":
                root = FXMLLoader.load(getClass().getResource("ReviewTest.fxml"));
                break;
            case "Cancel Room":
                root = FXMLLoader.load((getClass().getResource("Scene4.fxml")));
                break;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}