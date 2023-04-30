package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdminJob extends BaseRoleController{

    @FXML
    void handleDishManagement(ActionEvent event) {
        changeScene("AdminDishes.fxml", "Работа с меню", 950, 600, event);

    }

    @FXML
    void handleOrderManagement(ActionEvent event) {
        changeScene("AdminOrders.fxml", "Работа с заказами", 800, 450, event);

    }

    @FXML
    void handleStaffManagement(ActionEvent event) {
        changeScene("AdminStaff.fxml", "Работа с сотрудниками", 1100, 600, event);

    }
}
