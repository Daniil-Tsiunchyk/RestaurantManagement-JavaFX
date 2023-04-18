package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterMainMenuController {
    @FXML
    void handleCreateOrder(ActionEvent event) {
    }

    @FXML
    void handleUpdateOrder(ActionEvent event) {
    }

    @FXML
    void handleDeleteOrder(ActionEvent event) {
    }

    @FXML
    void handleViewOrders(ActionEvent event) {
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }
}
