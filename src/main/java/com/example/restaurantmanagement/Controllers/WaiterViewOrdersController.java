package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.OrderService.getDataOrders;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterViewOrdersController {

    @FXML
    private TableColumn<?, ?> end_time;

    @FXML
    private TableColumn<?, ?> number_column;

    @FXML
    private TableColumn<?, ?> order_column;

    @FXML
    private TableView<Order> order_table;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> start_time;

    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("WaiterJob.fxml", "Панель официанта", 250, 275, event);
    }

    @FXML
    void initialize() throws SQLException {
        number_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        order_column.setCellValueFactory(new PropertyValueFactory<>("information"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        start_time.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        end_time.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        order_table.setItems(getDataOrders());
    }
}