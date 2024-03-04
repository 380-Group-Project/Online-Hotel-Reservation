package sample.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label myLabel;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private final String[] menu = {"Select room type", "Reserve Room", "Cancel Room"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(menu);
    }

    public void getMenu(ActionEvent event){

        String myMenu = myChoiceBox.getValue();

    }
    private void choose(){
        // use Platform.runLater() when you need to update the GUI
        // batch (group together) several GUI updates when you can
        Platform.runLater(() -> {
            // this.myComboBox.setItems("Goto Scene 1","Goto Scene 2");

            // you should have to set it in this way
            this.myChoiceBox.setItems(FXCollections.observableArrayList("Select room type","Reserve Room", "Cancel Room"));
        });

        // when you don't have direct access to the stage object

    }


}
