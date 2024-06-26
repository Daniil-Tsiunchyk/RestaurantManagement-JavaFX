package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class ManagerOrdersController {


    @FXML
    private TableColumn<?, ?> end_time;

    @FXML
    private TableColumn<?, ?> number_column;

    @FXML
    private TableColumn<?, ?> order_column;

    @FXML
    private TableView<?> order_table;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> start_time;

    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("ManagerJob.fxml", "Панель менеджера", 240, 300, event);

    }

    @FXML
    void initialize() {

    }

}
