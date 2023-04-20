package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdminJobController {


    @FXML
    private Label handleHello;

    @FXML
    void handleDishManagement(ActionEvent event) {
        changeScene("AdminDishes.fxml", "Работа с меню", 800, 450, event);

    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }

    @FXML
    void handleOrderManagement(ActionEvent event) {
        changeScene("AdminOrders.fxml", "Работа с заказами", 800, 450, event);

    }

    @FXML
    void handleStaffManagement(ActionEvent event) {
        changeScene("AdminStaff.fxml", "Работа с сотрудниками", 800, 450, event);

    }
}
