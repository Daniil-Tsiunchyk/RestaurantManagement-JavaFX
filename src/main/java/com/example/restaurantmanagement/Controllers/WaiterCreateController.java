package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Database.DishService;
import com.example.restaurantmanagement.Entities.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class WaiterCreateController {
    @FXML
    private TableColumn<Dish, String> category_column;

    @FXML
    private TableColumn<Dish, String> name_column;

    @FXML
    private TableColumn<Dish, Integer> number_column;

    @FXML
    private TableView<Dish> order_table;

    @FXML
    private TableColumn<Dish, Double> price_column;

    @FXML
    private Label totalPriceLabel;

    @FXML
    void handleAddDish(ActionEvent event) throws SQLException {
        Button button = (Button) event.getSource();
        int type_id = Integer.parseInt(button.getUserData().toString());
        order_table.setItems(DishService.getDataDishWithType(type_id));
        totalPriceLabel.setText("");
    }


    @FXML
    void handleNewOrder(ActionEvent event) {
        changeScene("WaiterJob.fxml", "Панель официанта", 240, 275, event);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        changeScene("WaiterJob.fxml", "Панель официанта", 250, 275, event);
    }

    void updateTable() throws SQLException {
        number_column.setCellValueFactory(new PropertyValueFactory<>("iddish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("cost"));
        order_table.setItems(DishService.getDataDish());
    }

    @FXML
    void handleOrderReset(ActionEvent event) {
    }

    @FXML
    void handleCategoryReset(ActionEvent event) {
    }

    @FXML
    void handleAddToOrder(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException {
        updateTable();
    }
}
