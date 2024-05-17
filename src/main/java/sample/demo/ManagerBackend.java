package sample.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerBackend {

    private static final String DB_URL = "jdbc:sqlite:hotel.db";

    public ResultSet retrieveData() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement statement = conn.createStatement();
        return statement.executeQuery("SELECT * FROM reservations");
    }
}
