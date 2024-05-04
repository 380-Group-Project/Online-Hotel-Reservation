package sample.demo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {
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

    public void clean(){
        try(Connection conn = connect();){
            conn.prepareStatement("DROP TABLE room").execute();
            conn.prepareStatement("DROP TABLE reservations").execute();

            String command = "CREATE TABLE room(room_number INTEGER PRIMARY KEY, room_type TEXT, bednum INTEGER, price INTEGER, reserved BOOLEAN)";

            conn.prepareStatement(command).execute();

            command = "CREATE TABLE reservations(res_num INTEGER PRIMARY KEY, room_num INTEGER, name TEXT, guest_num INTEGER, check_in TEXT, check_out TEXT)";
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

    @Test
    public void testRoom(){
        Room dummy = new Room(101);
        int exNum = 101;
        String exType = "basic";
        int exBed = 1;
        int exPrice = 50;
        boolean exRes = false;

        assertEquals(exNum, dummy.getRoomNumber());
        assertEquals(exType,dummy.getRoomType());
        assertEquals(exBed,dummy.getBedNum());
        assertEquals(exPrice,dummy.getPrice());
        assertEquals(exRes,dummy.isReserved());
    }

    @Test
    public void testToString(){
        Room dummy = new Room(101, "basic", 1, 50, false);
        assertEquals("Room Number: 101, Room Type: basic, Bed Num: 1, Price: 50.0, Reserved: false",dummy.toString());
    }
}
