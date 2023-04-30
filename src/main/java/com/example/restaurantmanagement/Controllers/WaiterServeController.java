package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.OrderedDish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.DishService.getDataServedDishes;
import static com.example.restaurantmanagement.Database.DishService.updateOrderedDishStatus;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterServeController {

    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<OrderedDish> cooking_table;

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
    void handleUpdateOrderStatus(ActionEvent event) throws SQLException {
        OrderedDish selectedOrderedDish = cooking_table.getSelectionModel().getSelectedItem();
        if (selectedOrderedDish == null) {
            //error_message.setText("Пожалуйста, выберите блюдо");
            return;
        }
        updateOrderedDishStatus(selectedOrderedDish.getDishId(), "CLOSED");
        updateTable();
    }

    void updateTable() throws SQLException {
        cooking_number_column.setCellValueFactory(new PropertyValueFactory<>("idordered_dish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        table_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type_name"));

        cooking_table.setItems(getDataServedDishes());
    }

    @FXML
    void initialize() throws SQLException {
        updateTable();
    }

}
