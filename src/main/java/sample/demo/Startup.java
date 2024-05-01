package sample.demo;
import java.sql.*;

/**
 * Class created to facilitate initial setup of necessary database files
 * @author Jade Dergevorkian
 */

public class Startup {
    private Connection connect() {
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
    public void Setup(){

        try(Connection conn = this.connect();){
            String command = "CREATE TABLE room(room_number INTEGER PRIMARY KEY, room_type VARCHAR(10), bednum INTEGER, price INTEGER, reserved BOOLEAN)";

            conn.prepareStatement(command).execute();

            command = "CREATE TABLE reservations(res_num INTEGER PRIMARY KEY, room_num INTEGER, name TEXT, guest_num INTEGER, check_in TEXT, check_out TEXT)";
            conn.prepareStatement(command).execute();

            conn.prepareStatement("INSERT INTO room VALUES(101, \"basic\", 1, 50, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(102, \"basic\", 1, 50, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(103, \"basic\", 2, 75, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, \"family\", 2, 100, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, \"family\", 2, 100, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, \"family\", 3, 125, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, \"suite\", 2, 200, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, \"suite\", 2, 200, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, \"suite\", 2, 200, FALSE").execute();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
