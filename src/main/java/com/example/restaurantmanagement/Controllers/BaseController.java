package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class BaseController {
    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }
}
