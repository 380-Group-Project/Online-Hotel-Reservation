package sample.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
/**
 *
 * @author Seng Dieng
 * @author Jade (modified)
 */
public class tester extends Application {

        private static Connection connect() {
            // SQLite connection string
            String url = "jdbc:sqlite:hotel.db";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

        public static void setup(){

            try(Connection conn = connect();){
                String command = "CREATE TABLE IF NOT EXISTS  room(room_number INTEGER PRIMARY KEY, room_type TEXT, bednum INTEGER, price INTEGER, reserved BOOLEAN)";

                conn.prepareStatement(command).execute();

                command = "CREATE TABLE IF NOT EXISTS  reservations(res_num INTEGER PRIMARY KEY, room_num INTEGER, name TEXT, guest_num INTEGER, check_in TEXT, check_out TEXT)";
                conn.prepareStatement(command).execute();

                conn.prepareStatement("INSERT INTO room VALUES(101, 'basic', 1, 50, 0)").executeUpdate();
                conn.prepareStatement("INSERT INTO room VALUES(102, 'basic', 1, 50, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(103, 'basic', 2, 75, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(201, 'family', 2, 100, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(202, 'family', 2, 100, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(203, 'family', 3, 125, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(301, 'suite', 2, 200, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(302, 'suite', 2, 200, FALSE)").execute();
                conn.prepareStatement("INSERT INTO room VALUES(303, 'suite', 2, 200, FALSE)").execute();
            }

            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Homepage");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        public static void main(String[] args) {
            setup();
            launch(args);
        }

}
