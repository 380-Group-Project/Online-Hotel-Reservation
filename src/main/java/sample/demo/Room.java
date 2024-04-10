package sample.demo;

import java.time.LocalDate;

public class Room {
    private int roomNumber;
    private String roomType;
    private int bedNum;
    private double price;
    private boolean reserved;
    private LocalDate start;
    private LocalDate end;

    public Room(int roomNumber, String roomType, int bedNum, double price, boolean reserved) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedNum = bedNum;
        this.price = price;
        this.reserved = reserved;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBedNum() {
        return bedNum;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                ", Room Type: " + roomType +
                ", Bed Num: " + bedNum +
                ", Price: " + price +
                ", Reserved: " + reserved;
    }

}
