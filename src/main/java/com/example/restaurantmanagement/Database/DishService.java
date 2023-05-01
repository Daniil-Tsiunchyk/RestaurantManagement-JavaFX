package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Dish;
import com.example.restaurantmanagement.Entities.OrderedDish;
import com.example.restaurantmanagement.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

public class DishService extends DBConnection {
    public static ObservableList<OrderedDish> getDataDishForKitchen() throws SQLException {
        ObservableList<OrderedDish> list = FXCollections.observableArrayList();
        String select = """
                SELECT ordered_dish.id AS idordered_dish, dishes.name AS dish_name, dish_types.name AS dish_type, ordered_dish.status
                FROM ordered_dish
                JOIN dishes ON ordered_dish.dish_id = dishes.id
                JOIN dish_types ON dishes.type_id = dish_types.id;
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
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("type"),
                rs.getString("status")
        );
    }

    public static ObservableList<Dish> getDataDish() throws SQLException {
        ObservableList<Dish> list = FXCollections.observableArrayList();
        String select = """
                SELECT d.id, d.name, d.cost, dt.name as type
                FROM dishes d
                LEFT JOIN dish_types dt ON d.type_id = dt.id;
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
                SELECT d.id, d.recipe_id, d.name, d.cost, dt.name as type
                FROM dishes d
                LEFT JOIN dish_types dt ON d.type_id = dt.id
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
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("cost"),
                rs.getString("type")
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
                insertOrderedDishStatement.setInt(1, dish.getId());
                insertOrderedDishStatement.setInt(2, orderId);
                insertOrderedDishStatement.setString(3, status);
                insertOrderedDishStatement.executeUpdate();
            }
        }
    }

    public static ObservableList<OrderedDish> getDataServedDishes() throws SQLException {
        ObservableList<OrderedDish> list = FXCollections.observableArrayList();
        String select = """
                SELECT ordered_dish.id AS id, dishes.name AS name, dish_types.name AS type, ordered_dish.status, orders.table_id
                FROM ordered_dish
                JOIN dishes ON ordered_dish.dish_id = dishes.id
                JOIN dish_types ON dishes.type_id = dish_types.id
                JOIN orders ON ordered_dish.order_id = orders.id
                WHERE ordered_dish.status = 'SERVED';
                 """;
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToOrderedDish(rs));
            }

        }
        return list;
    }

    private static OrderedDish mapResultSetToOrderedDish(ResultSet rs) throws SQLException {
        return new OrderedDish(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("type"),
                rs.getString("status"),
                rs.getInt("table_id")
        );
    }

    public static void updateOrderedDishStatus(int id, String status) throws SQLException {
        String updateQuery = "UPDATE ordered_dish SET status = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateOrderStatusByOrderedDishId(id);
    }

    public static void updateOrderStatus(int orderId) throws SQLException {
        String updateOrderStatusQuery = """
                    UPDATE orders
                    SET status = CASE
                        WHEN (
                            SELECT COUNT(*)
                            FROM ordered_dish
                            WHERE order_id = orders.id AND status <> 'CLOSED'
                        ) = 0 THEN 'CLOSED'
                        WHEN (
                            SELECT COUNT(*)
                            FROM ordered_dish
                            WHERE order_id = orders.id AND status <> 'SERVED'
                        ) = 0 THEN 'SERVED'
                        WHEN (
                            SELECT COUNT(*)
                            FROM ordered_dish
                            WHERE order_id = orders.id AND status = 'OPEN'
                        ) > 0 THEN 'OPEN'
                        WHEN (
                            SELECT COUNT(*)
                            FROM ordered_dish
                            WHERE order_id = orders.id AND status = 'OPEN'
                        ) > 0 THEN 'PREPARED'
                        ELSE orders.status
                    END,
                                end_time = IF((
                            SELECT COUNT(*)
                            FROM ordered_dish
                            WHERE order_id = orders.id AND status <> 'CLOSED'
                        ) = 0             , CURRENT_TIMESTAMP, end_time)
                                WHERE id = ?;
                """;
        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(updateOrderStatusQuery)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
//            int rowsAffected = ps.executeUpdate();
//            System.out.println("Изменилось строк: " + rowsAffected);
        }
    }


    public static void updateOrderStatusByOrderedDishId(int orderedDishId) throws SQLException {

        String select = "SELECT order_id FROM ordered_dish WHERE id = ?";
        int orderId = 0;

        try (Connection connection = getDbConnection();
             PreparedStatement ps = connection.prepareStatement(select)) {
            ps.setInt(1, orderedDishId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                }
            }
        }

        if (orderId != 0) {
            updateOrderStatus(orderId);
        }
    }

    public static void createDish(String name, String category, BigDecimal price) throws SQLException {
        String insert = "INSERT INTO dishes (name, type_id, cost) VALUES (?, (SELECT id FROM dish_types WHERE name = ?), ?)";

        try (PreparedStatement insertDishStatement = getDbConnection().prepareStatement(insert)) {
            insertDishStatement.setString(1, name);
            insertDishStatement.setString(2, category);
            insertDishStatement.setBigDecimal(3, price);
            insertDishStatement.executeUpdate();
        }
    }

    public static void deleteDish(int id) throws SQLException {
        String delete = "DELETE FROM dishes WHERE id = ?";

        try (PreparedStatement deleteDishStatement = getDbConnection().prepareStatement(delete)) {
            deleteDishStatement.setInt(1, id);
            deleteDishStatement.executeUpdate();
        }
    }

    public static void updateDish(int id, String name, String category, BigDecimal price) throws SQLException {
        String update = "UPDATE dishes SET name = ?, type_id = (SELECT id FROM dish_types WHERE name = ?), cost = ? WHERE id = ?";

        try (PreparedStatement updateDishStatement = getDbConnection().prepareStatement(update)) {
            updateDishStatement.setString(1, name);
            updateDishStatement.setString(2, category);
            updateDishStatement.setBigDecimal(3, price);
            updateDishStatement.setInt(4, id);
            updateDishStatement.executeUpdate();
        }
    }

}
