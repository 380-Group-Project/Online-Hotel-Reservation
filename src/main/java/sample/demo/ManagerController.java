package sample.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *This class exists to control the Manager scene.
 *
 * @author Seng Dieng
 *
 */

public class ManagerController {


    @FXML
    private ListView<String> listView;

    private final ManagerBackend conn = new ManagerBackend();
    SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        try {
            ResultSet resultSet = conn.retrieveData();
            displayData(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayData(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        StringBuilder columnNames = new StringBuilder();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.append(metaData.getColumnName(i));
            if (i < columnCount) {
                columnNames.append("\t\t");
            }
        }
        listView.getItems().add(columnNames.toString());

        StringBuilder recordBuilder = new StringBuilder();
        while (resultSet.next()) {
            recordBuilder.setLength(0);
            for (int i = 1; i <= columnCount; i++) {
                if (i == 5 || i == 6) {
                    Date date = new Date(resultSet.getLong(i));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    recordBuilder.append(sdf.format(date));
                } else {
                    recordBuilder.append(resultSet.getString(i));
                }
                if (i < columnCount) {
                    recordBuilder.append("\t\t");
                }
            }
            listView.getItems().add(recordBuilder.toString());
        }
    }

    /**
     * Changes the page to the Homepage
     *
     * @param event the current page
     */
    public void switchToHome(ActionEvent event) throws IOException {
        sceneController.switchToHome(event);
    }
}
