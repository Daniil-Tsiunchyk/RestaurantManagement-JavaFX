package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterServeController {

    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<?> cooking_table;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    private TableColumn<?, ?> table_column;

    @FXML
    private TableColumn<?, ?> type_column;

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("WaiterJob.fxml", "Панель официанта", 240, 275, event);

    }

    @FXML
    void handleUpdateOrderStatus(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
