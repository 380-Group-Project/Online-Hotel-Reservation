package sample.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author Jade
 */
public class ReservationsTest {
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
            conn.prepareStatement("INSERT INTO reservations VALUES(0, 101, 'Jane Doe', 1, '1714892400000', '1715497200000')").execute();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
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

    @Test
    public void testReserve() {
        clean();

        Reservations rez = new Reservations();
        int id = rez.reserve(101, "Jane Doe", 1, java.sql.Date.valueOf("2024-05-05"), java.sql.Date.valueOf("2024-05-12"));
        String sql = "SELECT room_num, name, guest_num, check_in, check_out FROM reservations WHERE res_num = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            ResultSet rs1 = conn.prepareStatement("SELECT room_num, name, guest_num, check_in, check_out FROM reservations WHERE res_num = 0").executeQuery();
            assertEquals(rs.getInt("room_num"), rs1.getInt("room_num"));
            assertEquals(rs.getString("name"), rs1.getString("name"));
            assertEquals(rs.getInt("guest_num"), rs1.getInt("guest_num"));
            assertEquals(rs.getString("check_in"), rs1.getString("check_in"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testCancel(){
        clean();

        Reservations rez = new Reservations();
        rez.cancel(0);
        try (Connection conn = this.connect()){
            ResultSet rs = conn.prepareStatement("SELECT room_num FROM reservations WHERE res_num = 0").executeQuery();
            assertEquals(0,rs.getInt("room_num"));
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetReservation(){
        clean();

        Reservations rez = new Reservations();
        ArrayList<Object> rezList = rez.getReservation(0);
        try (Connection conn = this.connect()){
            ResultSet rs = conn.prepareStatement("SELECT * FROM reservations WHERE res_num = 0").executeQuery();
            assertEquals(rezList.get(0),rs.getInt("res_num"));
            assertEquals(rezList.get(1),rs.getInt("room_num"));
            assertEquals(rezList.get(2),rs.getString("name"));
            assertEquals(rezList.get(3),rs.getInt("guest_num"));
            assertEquals(rezList.get(4),rs.getString("check_in"));

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
