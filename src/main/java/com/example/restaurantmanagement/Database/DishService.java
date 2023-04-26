package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Dish;
import com.example.restaurantmanagement.Entities.OrderedDish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

import static com.example.restaurantmanagement.Utils.DBConnection.getDbConnection;

public class DishService {
    public static ObservableList<OrderedDish> getDataDishForKitchen() throws SQLException {
        ObservableList<OrderedDish> list = FXCollections.observableArrayList();
        String select = """
                SELECT ordered_dish.idordered_dish, dishes.name AS dish_name, dish_types.name AS dish_type, ordered_dish.status, ordered_dish.quantity
                FROM ordered_dish
                JOIN dishes ON ordered_dish.dish_id = dishes.iddish
                JOIN dish_types ON dishes.type_id = dish_types.iddish_type
                """;
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDishForKitchen(rs));
            }

        }
        return list;
    }

    private static OrderedDish mapResultSetToDishForKitchen(ResultSet rs) throws SQLException {
        return new OrderedDish(
                rs.getInt("idordered_dish"),
                rs.getString("dish_name"),
                rs.getString("dish_type"),
                rs.getString("status"),
                rs.getInt("quantity")
        );
    }

    public static ObservableList<Dish> getDataDish() throws SQLException {
        ObservableList<Dish> list = FXCollections.observableArrayList();
        String select = """
                SELECT d.iddish, d.name, d.cost, dt.name as type_name
                FROM dishes d
                LEFT JOIN dish_types dt ON d.type_id = dt.iddish_type;
                             
                """;
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDish(rs));
            }

        }
        return list;
    }

    public static ObservableList<Dish> getDataDishWithType(int type_id) throws SQLException {
        ObservableList<Dish> list = FXCollections.observableArrayList();
        String select = """
                SELECT d.iddish, d.recipe_id, d.name, d.cost, dt.name as type_name
                FROM dishes d
                LEFT JOIN dish_types dt ON d.type_id = dt.iddish_type
                WHERE d.type_id =
                """ + type_id + ';';
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDish(rs));
            }

        }
        return list;
    }

    private static Dish mapResultSetToDish(ResultSet rs) throws SQLException {
        return new Dish(
                rs.getInt("iddish"),
                rs.getString("name"),
                rs.getBigDecimal("cost"),
                rs.getString("type_name")
        );
    }

    public static void addOrderAndOrderedDishes(ObservableList<Dish> list, String names, BigDecimal totalCost, int table_id) throws SQLException {
        String insert = "INSERT INTO `orders` (information, total_cost, table_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?, ?)";
        LocalDateTime startTime = LocalDateTime.now();
        String status = "OPEN";

        int orderId;

        try (PreparedStatement insertOrderStatement = getDbConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            insertOrderStatement.setString(1, names);
            insertOrderStatement.setDouble(2, Double.parseDouble(String.valueOf(totalCost)));
            insertOrderStatement.setInt(3, table_id);
            insertOrderStatement.setTimestamp(4, Timestamp.valueOf(startTime));
            insertOrderStatement.setTimestamp(5, null);
            insertOrderStatement.setString(6, status);
            insertOrderStatement.executeUpdate();

            try (ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Ошибка создания заказа");
                }
            }
        }

        for (Dish dish : list) {
            insert = "INSERT INTO `ordered_dish` (dish_id, order_id, status) VALUES (?, ?, ?)";
            try (PreparedStatement insertOrderedDishStatement = getDbConnection().prepareStatement(insert)) {
                insertOrderedDishStatement.setInt(1, dish.getIddish());
                insertOrderedDishStatement.setInt(2, orderId);
                insertOrderedDishStatement.setString(3, status);
                insertOrderedDishStatement.executeUpdate();
            }
        }
    }
}
