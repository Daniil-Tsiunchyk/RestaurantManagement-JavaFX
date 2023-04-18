package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Order;
import com.example.restaurantmanagement.Enums.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    void createOrder(Order order) {
        // Implement createOrder functionality
    }

    void updateOrder(Order order) {
        // Implement updateOrder functionality
    }

    void deleteOrder(int orderId) {
        // Implement deleteOrder functionality
    }

    Order getOrderById(int orderId) {
        // Implement getOrderById functionality
        return null;
    }

    List<Order> getOrdersByWaiterId(int waiterId) {
        // Implement getOrdersByWaiterId functionality
        return new ArrayList<>();
    }

    List<Order> getOrdersByStatus(OrderStatus status) {
        // Implement getOrdersByStatus functionality
        return new ArrayList<>();
    }
}
