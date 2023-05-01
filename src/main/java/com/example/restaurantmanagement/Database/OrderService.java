package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Order;
import com.example.restaurantmanagement.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrderService extends DBConnection {

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
                rs.getInt("id"),
                rs.getString("information"),
                rs.getDouble("total_cost"),
                rs.getInt("table_id"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"),
                rs.getString("status")
        );
    }

    public static String bookingTable(int tableID, LocalDate date, String time, String name) throws SQLException {

        String checkQuery = "SELECT COUNT(*) FROM tables_booking WHERE table_id = ? AND date = ? AND time = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setInt(1, tableID);
            checkStatement.setDate(2, java.sql.Date.valueOf(date));
            checkStatement.setTime(3, java.sql.Time.valueOf(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))));

            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();
            if (checkResult.getInt(1) > 0) {
                return "Бронь на это время уже существует";
            } else {
                // Добавление данных о бронировании в базу данных
                String insertQuery = "INSERT INTO tables_booking (table_id, date, time, info) VALUES (?, ?, ?, ?)";

                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, tableID);
                    insertStatement.setDate(2, java.sql.Date.valueOf(date));
                    insertStatement.setTime(3, java.sql.Time.valueOf(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))));
                    insertStatement.setString(4, name);

                    int rowsInserted = insertStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        return "Успешно забронировано";
                    } else {
                        return "Ошибка добавления бронирования";
                    }
                }
            }
        }
    }

}
