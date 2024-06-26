package sample.demo;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *This class exists primarily to control the scenes that is viewing.
 * @author Seng Dieng
 */



public class SceneController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    /**
     * Changes the page to scene1
     *
     * @param event the current page
     *
     */
    public void switchToScene1(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }


    /**
     * Changes the page to scene2
     *
     * @param event the current page
     */
    public void switchToScene2(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the page to scene3
     *
     * @param event the current page
     */
    public void switchToScene3(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the page to scene4
     *
     * @param event the current page
     */

    public void switchToScene4(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Scene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the page to Manager
     *
     * @param event the current page
     */
    public void switchToManager(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(getClass().getResource("Manager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the page to the Homepage
     *
     * @param event the current page
     */

    public void switchToHome(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
