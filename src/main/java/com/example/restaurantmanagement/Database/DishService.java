package com.example.restaurantmanagement.Database;

import com.example.restaurantmanagement.Entities.Dish;
import com.example.restaurantmanagement.Entities.OrderedDish;
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
