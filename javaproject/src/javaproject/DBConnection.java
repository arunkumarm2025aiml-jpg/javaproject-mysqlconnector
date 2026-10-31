
package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/collaboration";
        String username = "root";
        String password = "arun";

        Connection con = DriverManager.getConnection(
            url, username, password
        );

        return con;
    }
}
