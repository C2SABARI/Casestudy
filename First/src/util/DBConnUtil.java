package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    
    public static Connection getConnection() throws SQLException, FileNotFoundException, IOException, ClassNotFoundException {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/Casedb?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "Sabari@123";

            con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (Exception e) {
            e.printStackTrace(); // Always print the stack trace for debugging
        }

        return con;
    }
}
