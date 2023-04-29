package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Database.DishService;
import com.example.restaurantmanagement.Entities.Dish;
import com.example.restaurantmanagement.Utils.FxUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.sql.SQLException;

public class WaiterCreateController {
    ObservableList<Dish> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Dish, String> category_column;
    @FXML
    private TableColumn<Dish, String> name_column;
    @FXML
    private TableColumn<Dish, Integer> number_column;
    @FXML
    private TableView<Dish> order_table;
    @FXML
    private TableColumn<Dish, BigDecimal> price_column;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private ChoiceBox<Integer> tables_choicebox;

    @FXML
    void handleAddDish(ActionEvent event) throws SQLException {
        Button button = (Button) event.getSource();
        int typeId = Integer.parseInt(button.getUserData().toString());
        order_table.setItems(DishService.getDataDishWithType(typeId));
    }

    @FXML
    void handleDishDeleteFromOrder(ActionEvent event) {
        Dish selectedItem = order_table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            list.remove(selectedItem);
            updateTotalPrice();
        }
    }

    @FXML
    void handleNewOrder(ActionEvent event) throws SQLException {
        StringBuilder names = new StringBuilder();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (int i = 0; i < list.size(); i++) {
            Dish dish = list.get(i);
            names.append(dish.getName());
            if (i != list.size() - 1) {
                names.append(", ");
            }
            totalPrice = totalPrice.add(dish.getCost());
        }
        DishService.addOrderAndOrderedDishes(list, String.valueOf(names), totalPrice, tables_choicebox.getValue());
        FxUtils.changeScene("WaiterView.fxml", "Просмотр заказов", 800, 450, event);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        FxUtils.changeScene("WaiterJob.fxml", "Панель официанта", 250, 275, event);
    }

    private void updateTable() throws SQLException {
        number_column.setCellValueFactory(new PropertyValueFactory<>("iddish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("cost"));
        order_table.setItems(DishService.getDataDish());
    }

    @FXML
    void handleOrderReset(ActionEvent event) {
        list.clear();
        order_table.setItems(list);
        updateTotalPrice();
    }

    @FXML
    void handleCategoryReset(ActionEvent event) {
        order_table.setItems(list);
    }

    @FXML
    void handleAddToOrder(ActionEvent event) {
        Dish selectedItem = order_table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            addDishToOrder(selectedItem);
        }
    }

    private void addDishToOrder(Dish dish) {
        list.add(dish);
        order_table.setItems(list);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Dish dish : list) {
            totalPrice = totalPrice.add(dish.getCost());
        }
        totalPriceLabel.setText("Стоимость заказа: " + totalPrice);
    }

    @FXML
    void initialize() throws SQLException {
        ObservableList<Integer> tables = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        tables_choicebox.setItems(tables);
        tables_choicebox.setValue(1);
        updateTable();
    }
}