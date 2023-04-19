package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Dish;
import com.example.restaurantmanagement.Entities.Order;
import com.example.restaurantmanagement.Enums.DishCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.restaurantmanagement.Utils.DBConnection.getDbConnection;

public class DishService {
    public static ObservableList<Dish> getDataDish() throws SQLException {
        ObservableList<Dish> list = FXCollections.observableArrayList();
        String select = "SELECT * FROM dishes";
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
                rs.getInt("recipe_id"),
                rs.getString("name"),
                rs.getDouble("cost"),
                rs.getInt("type_id"),
                rs.getInt("order_id")
        );
    }

    void createDish(Dish dish) {
    }

    void updateDish(Dish dish) {
    }

    void deleteDish(int dishId) {
    }

    Dish getDishById(int dishId) {
        return null;
    }

    List<Dish> getDishesByCategory(DishCategory category) {
        return null;
    }
}
