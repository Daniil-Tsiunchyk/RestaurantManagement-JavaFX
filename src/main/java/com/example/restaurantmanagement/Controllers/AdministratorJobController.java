package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdministratorJobController extends ManagerJobController {
    void handleAddDish(ActionEvent event) {
    }

    void handleUpdateDish(ActionEvent event) {
    }

    void handleDeleteDish(ActionEvent event) {
    }

    void handleViewDishes(ActionEvent event) {
    }

    void handleManageUsers(ActionEvent event) {
    }

    void handleLogout(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }

}
