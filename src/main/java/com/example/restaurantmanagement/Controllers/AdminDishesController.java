package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdminDishesController {

    @FXML
    private TableColumn<?, ?> category_column;

    @FXML
    private ComboBox<?> choice_category;

    @FXML
    private TableView<?> dishes_table;

    @FXML
    private TextField enter_name;

    @FXML
    private TextField enter_price;

    @FXML
    private Label error_message;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> number_column;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> recipe_column;

    @FXML
    private ComboBox<?> update_category;

    @FXML
    private TextField update_name;

    @FXML
    private TextField update_price;

    @FXML
    void handleAddAccount(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("AdminJob.fxml", "Панель администратора", 250, 275, event);

    }


    @FXML
    void handleDeleteDish(ActionEvent event) {

    }

    @FXML
    void handleUpdateDish(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}

