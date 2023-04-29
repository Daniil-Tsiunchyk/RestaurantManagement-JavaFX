package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterJobController {

    @FXML
    void handleCreateOrder(ActionEvent event) {
        changeScene("WaiterCreate.fxml", "Новый заказ", 700, 500, event);
    }

    @FXML
    void handleServeDish(ActionEvent event) {
        changeScene("WaiterServe.fxml", "Подача блюд", 600, 400, event);

    }
    @FXML
    void handleViewOrders(ActionEvent event) {
        changeScene("WaiterView.fxml", "Просмотр заказов", 800, 450, event);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);
    }

}