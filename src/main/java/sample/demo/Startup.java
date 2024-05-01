package sample.demo;
import java.sql.*;

/**
 * Class created to facilitate initial setup of necessary database files
 * @author Jade Dergevorkian
 */

public class Startup {
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

            command = "INSERT INTO room (room_number, room_type, bednum, price, reserved) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(command);
            pstmt.setInt(1,101);
            pstmt.setString(2,"basic");
            pstmt.setInt(3,1);
            pstmt.setInt(4,50);
            pstmt.setBoolean(5,false);
            pstmt.execute();
            pstmt.setInt(1,102);
            pstmt.execute();
            pstmt.setInt(1,103);
            pstmt.setInt(3,2);
            pstmt.setInt(4,75);
            pstmt.execute();

            pstmt.setInt(1,201);
            pstmt.setString(2,"family");
            pstmt.setInt(3,2);
            pstmt.setInt(4,100);
            pstmt.execute();
            pstmt.setInt(1,202);
            pstmt.execute();
            pstmt.setInt(1,203);
            pstmt.setInt(3,3);
            pstmt.setInt(4,125);
            pstmt.execute();

            pstmt.setInt(1,301);
            pstmt.setString(2,"suite");
            pstmt.setInt(3,2);
            pstmt.setInt(4,200);
            pstmt.execute();
            pstmt.setInt(1,302);
            pstmt.execute();
            pstmt.setInt(1,303);
            pstmt.execute();


            //conn.prepareStatement("INSERT INTO room (room_number, room_type, bednum, price, reserved) VALUES(101, 'basic', 1, 50, 0").executeUpdate();
            /* conn.prepareStatement("INSERT INTO room VALUES(102, basic, 1, 50, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(103, basic, 2, 75, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, family, 2, 100, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, family, 2, 100, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(201, family, 3, 125, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, suite, 2, 200, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, suite, 2, 200, FALSE").execute();
            conn.prepareStatement("INSERT INTO room VALUES(301, suite, 2, 200, FALSE").execute();*/
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
