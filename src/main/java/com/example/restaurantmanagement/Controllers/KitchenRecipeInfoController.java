package com.example.restaurantmanagement.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class KitchenRecipeInfoController {

    @FXML
    private ImageView food_image;

    @FXML
    private TextArea recipe_text;

    @FXML
    private Label title_label;
    @FXML
    private Button closeButton;

    @FXML
    void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
//        title_label.setText(title);
//        recipe_text.setText(recipeInfo);
//        food_image.setImage(image);
    }

}
