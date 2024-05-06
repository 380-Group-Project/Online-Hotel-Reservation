package sample.demo;

import java.sql.*;
import java.time.LocalDate;

/**
 * This class exists primarily to store information about rooms which are being reserved/viewed.
 *
 * @author Seng Dieng
 * @author Jade (minor edits)
 */

public class Room {
    private int roomNumber;
    private String roomType;
    private int bedNum;
    private double price;
    private boolean reserved;
    private LocalDate start;
    private LocalDate end;

    /**
     * This constructor allows for the creation of a room with the following parameters:
     * @param roomNumber the number of the room
     * @param roomType the type of the room
     * @param bedNum the number of beds in the room
     * @param price the per-night price of the room
     * @param reserved whether or not the room is currently reserved
     */

    public Room(int roomNumber, String roomType, int bedNum, double price, boolean reserved) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedNum = bedNum;
        this.price = price;
        this.reserved = reserved;
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

    /**
     * Alternative constructor which takes the rest of the parameters directly from the database
     * @param roomNumber The room number
     */
    public Room(int roomNumber) {
        String sql = "SELECT room_type, bednum, price, reserved FROM room WHERE room_number = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,roomNumber);

            ResultSet rs = pstmt.executeQuery();

            this.roomNumber = roomNumber;
            this.roomType = rs.getString("room_type");
            this.bedNum = rs.getInt("bednum");
            this.price = rs.getDouble("price");
            this.reserved = rs.getBoolean("reserved");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Getter
     * @return the number of the room
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter
     * @return type of the room
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Getter
     * @return the amount of beds in the room
     */
    public int getBedNum() {
        return bedNum;
    }

    /**
     * Getter
     * @return the price-per-night of the room
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter
     * @return the reservation state of the room
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     *
     * @return All of the attributes of the room in a neatly packaged string
     */
    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                ", Room Type: " + roomType +
                ", Bed Num: " + bedNum +
                ", Price: " + price +
                ", Reserved: " + reserved;
    }

}
