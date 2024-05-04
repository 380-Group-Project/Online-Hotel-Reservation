import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Jade Dergevorkian
 */

public class Driver {
    public static void main(String[] args){
        Reservations reservations = new Reservations();

        int res_id = reservations.reserve(101,"Jane Doe",1, new Date(2024,03,22), new Date(2024,03,29));
        ArrayList<Object> rs = reservations.getReservation(res_id);
    }
}
