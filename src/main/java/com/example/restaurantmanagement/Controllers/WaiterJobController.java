package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterJobController {
    @FXML
    private Label handleHello;

    @FXML
    void handleCreateOrder(ActionEvent event) {
        changeScene("WaiterCreate.fxml", "Новый заказ", 600, 400, event);
    }

    @FXML
    void handleUpdateOrder(ActionEvent event) {
        changeScene("WaiterUpdate.fxml", "Редактирование заказа", 600, 400, event);
    }

    @FXML
    void handleViewOrders(ActionEvent event) {
        changeScene("WaiterView.fxml", "Просмотр заказов", 800, 450, event);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);
    }

    @FXML
    void initialize() {
        handleHello.setText("Здравствуй, {name}!");
    }
}