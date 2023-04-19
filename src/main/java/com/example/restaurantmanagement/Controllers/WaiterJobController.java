package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterJobController {

    @FXML
    void handleCreateOrder(ActionEvent event) {
        changeScene("WaiterCreateOrder.fxml", "Новый заказ", 600, 400, event);
    }

    @FXML
    void handleUpdateOrder(ActionEvent event) {
        changeScene("WaiterUpdateOrder.fxml", "Редактирование заказа", 600, 400, event);
    }

    @FXML
    void handleViewOrders(ActionEvent event) {
        changeScene("WaiterViewOrders.fxml", "Просмотр заказов", 800, 450, event);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);
    }
}