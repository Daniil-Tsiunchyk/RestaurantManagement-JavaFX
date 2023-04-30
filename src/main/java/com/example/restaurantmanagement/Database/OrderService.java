package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.restaurantmanagement.Utils.DBConnection.getDbConnection;

public class OrderService {

    public static ObservableList<Order> getDataOrders() throws SQLException {
        ObservableList<Order> list = FXCollections.observableArrayList();
        String select = "SELECT * FROM orders";
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToOrder(rs));
            }
        }
        return list;
    }

    private static Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id_order"),
                rs.getString("information"),
                rs.getDouble("total_cost"),
                rs.getInt("table_id"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"),
                rs.getString("status")
        );
    }
}
