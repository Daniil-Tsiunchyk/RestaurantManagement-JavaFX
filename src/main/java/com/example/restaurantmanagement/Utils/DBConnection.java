package com.example.restaurantmanagement.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management", "root", "root");
    }

}
