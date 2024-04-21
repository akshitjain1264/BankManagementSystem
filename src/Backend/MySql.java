package Backend;

import java.sql.*;

public class MySql {
    private static String url = "jdbc:mysql://localhost:3306/bank";
    private static String username = "root";
    private static String password = "Test@123";


     public static Connection getConn(){
        Connection conn;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
