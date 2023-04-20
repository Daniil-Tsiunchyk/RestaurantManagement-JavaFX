package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class ManagerJobController {


    @FXML
    private Label handleHello;

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }

    @FXML
    void handleOrdersManagement(ActionEvent event) {
        changeScene("ManagerOrders.fxml", "Работа с заказами", 800, 450, event);

    }

    @FXML
    void handleTableManagement(ActionEvent event) {
        changeScene("ManagerTables.fxml", "Работа со столиками", 800, 450, event);

    }

    @FXML
    void handleWorkManagement(ActionEvent event) {
        changeScene("ManagerHours.fxml", "Работа со сменами", 800, 450, event);

    }


}
