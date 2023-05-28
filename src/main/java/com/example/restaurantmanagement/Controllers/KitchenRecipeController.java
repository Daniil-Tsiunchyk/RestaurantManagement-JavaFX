package com.example.restaurantmanagement.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KitchenRecipeController {
    @FXML
    private Button closeButton;

    @FXML
    void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
