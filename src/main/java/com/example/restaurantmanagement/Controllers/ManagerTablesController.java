package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class ManagerTablesController {
    @FXML
    void handleBack(ActionEvent event) {
        changeScene("ManagerJob.fxml", "Панель менеджера", 240, 300, event);

    }
}
