package com.example.restaurantmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WaiterJobController {

    @FXML
    private Button NewOrderButton;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<?, ?> category_column;
    @FXML
    private TableColumn<?, ?> name_column;
    @FXML
    private TableColumn<?, ?> number_column;
    @FXML
    private TableView<?> order_table;
    @FXML
    private TableColumn<?, ?> price_column;
    @FXML
    private Label totalCoseLabel;


    @FXML
    void initialize() {

    }

    public void handleBack(ActionEvent actionEvent) {
    }
}
