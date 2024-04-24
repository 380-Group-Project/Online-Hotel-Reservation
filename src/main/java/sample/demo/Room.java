package sample.demo;

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
