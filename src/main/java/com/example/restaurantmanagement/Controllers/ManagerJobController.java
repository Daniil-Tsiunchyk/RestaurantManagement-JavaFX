package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class ManagerJobController {
    void handleAddInfo(ActionEvent event) {
    }

    void handleUpdateInfo(ActionEvent event) {
    }


    void handleViewUsers(ActionEvent event) {
    }

    void handleViewOrders(ActionEvent event) {
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }
}
