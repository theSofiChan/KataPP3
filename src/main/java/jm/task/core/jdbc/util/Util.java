package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static Connection connectStuff() throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "poTAt0345";
        String connectionURL = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);


        return connection;
    }
}
