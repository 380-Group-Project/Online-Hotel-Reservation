import java.sql.*;
import java.util.Random;

/**
 *
 * @author Jade Dergevorkian
 */

public class Reservations
{
    public static void main(String[] args)
    {

    }

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
     * @param room
     * @param name
     * @param guests
     * @param inDate
     * @param outDate
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
}