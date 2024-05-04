package sample.demo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class made primarily to handle making reservations in the hotel.db database.
 *
 * @author Jade Dergevorkian
 * @author Seng Dieng (edited)
 */

public class Reservations
{


    /**
     * Connect to the hotel.db database
     *
     * @return the Connection object
     */
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

    /**
     * Insert a new row into the reservations table
     *
     * @param room the number of the room being reserved
     * @param name the name of the guest
     * @param guests the number of guests
     * @param inDate the date when they will check in
     * @param outDate the date when they will check out
     * @return res_id
     */
    public int reserve(int room,String name,int guests,Date inDate,Date outDate){
        String sql = "INSERT INTO reservations(res_num,room_num,name,guest_num,check_in,check_out) VALUES(?,?,?,?,?,?)";
        Random rand = new Random();
        int resId = rand.nextInt(2147483647);

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, resId);
            pstmt.setInt(2, room);
            pstmt.setString(3, name);
            pstmt.setInt(4, guests);
            pstmt.setDate(5, inDate);
            pstmt.setDate(6, outDate);
            pstmt.executeUpdate();
            return resId;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Delete a reservation from the reservations table
     *
     * @param resId The ID of the reservation getting cancelled.
     */
    public void cancel(int resId){
        String sql = "DELETE FROM reservations WHERE res_num = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, resId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public void update(int resId, int room,String name,int guests,Date inDate,Date outDate){
        String sql = "UPDATE reservations SET room_num = ?, name = ?, guest_num = ?, check_in = ?, check_out = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, room);
            pstmt.setString(2, name);
            pstmt.setInt(3, guests);
            pstmt.setDate(4, inDate);
            pstmt.setDate(5, outDate);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }*/

    /**
     * Returns information about the reservation with the corresponding ID
     * @param resId The ID of the relevent reservation
     * @return Returns an ArrayList containing all information about the reservation.
     */
    public ArrayList<Object> getReservation(int resId){
        ArrayList<Object> resList = new ArrayList<>();
        String sql = "SELECT FROM reservations WHERE res_id = ?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, resId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                resList.add(rs.getInt("res_num"));
                resList.add(rs.getInt("room_num"));
                resList.add(rs.getString("name"));
                resList.add(rs.getInt("guest_num"));
                resList.add(rs.getDate("check_in"));
                resList.add(rs.getDate("check_in"));
            }
            return resList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return resList;
        }

    }
}
