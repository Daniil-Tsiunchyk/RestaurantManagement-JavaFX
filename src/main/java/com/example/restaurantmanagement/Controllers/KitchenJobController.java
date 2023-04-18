package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class KitchenJobController {


    @FXML
    private TableColumn<?, ?> category_column;

    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<?> cooking_table;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> ready_number_column;

    @FXML
    private TableView<?> ready_tabe;

    @FXML
    void handeGetInfo(ActionEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);

    }

    @FXML
    void handleStartCooking(ActionEvent event) {

    }

    @FXML
    void handleUpdateOrderStatus(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
