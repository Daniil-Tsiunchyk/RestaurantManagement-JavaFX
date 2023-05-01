package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.DishService.*;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class AdminDishesController {
    @FXML
    private TableColumn<Dish, String> category_column;

    @FXML
    private ComboBox<String> choice_category;

    @FXML
    private TableView<Dish> dishes_table;

    @FXML
    private TextField enter_name;

    @FXML
    private TextField enter_price;

    @FXML
    private Label error_message;

    @FXML
    private TableColumn<Dish, String> name_column;

    @FXML
    private TableColumn<Dish, Integer> number_column;

    @FXML
    private TableColumn<Dish, Double> price_column;

    @FXML
    private TableColumn<Dish, String> recipe_column;

    @FXML
    private ComboBox<String> update_category;

    @FXML
    private TextField update_name;

    @FXML
    private TextField update_price;

    @FXML
    void handleAddDish(ActionEvent event) throws SQLException {
        String name = enter_name.getText();
        String category = choice_category.getSelectionModel().getSelectedItem();
        BigDecimal price = BigDecimal.valueOf(0);
        try {
            price = BigDecimal.valueOf(Double.parseDouble(enter_price.getText()));
        } catch (NumberFormatException e) {
            error_message.setText("Пожалуйста, введите корректную цену");
            return;
        }
        if (name.isEmpty() || category == null) {
            error_message.setText("Пожалуйста, заполните все поля");
            return;
        }
        createDish(name, category, price);
        enter_name.setText("");
        enter_price.setText("");
        updateTable();
    }

    @FXML
    void handleBack(ActionEvent event) {
        changeScene("AdminJob.fxml", "Панель администратора", 250, 275, event);
    }

    @FXML
    void handleDeleteDish(ActionEvent event) throws SQLException {
        Dish selectedDish = dishes_table.getSelectionModel().getSelectedItem();
        if (selectedDish == null) {
            error_message.setText("Пожалуйста, выберите блюдо");
            return;
        }
        deleteDish(selectedDish.getId());
        updateTable();
    }

    @FXML
    void handleUpdateDish(ActionEvent event) throws SQLException {
        //
        updateDish(dishes_table.getSelectionModel().getSelectedItem().getId(), update_name.getText(), update_category.getValue(), BigDecimal.valueOf(Double.parseDouble(update_price.getText())));
        update_name.setText("");
        update_price.setText("");
        updateTable();
    }

    void updateTable() throws SQLException {
        number_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("cost"));
        recipe_column.setCellValueFactory(new PropertyValueFactory<>(null));

        dishes_table.setItems(getDataDish());

        dishes_table.setRowFactory(tv -> {
            TableRow<Dish> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Dish selectedDish = row.getItem();
                    update_name.setText(selectedDish.getName());
                    update_price.setText(String.valueOf(selectedDish.getCost()));
                    update_category.getSelectionModel().select(selectedDish.getType());
                }
            });
            return row;
        });
    }


    @FXML
    void initialize() throws SQLException {
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Напитки",
                "Закуски",
                "Салаты",
                "Супы",
                "Основные блюда",
                "Гарниры",
                "Десерты",
                "Комплексные обеды");
        choice_category.setItems(categories);
        update_category.setItems(categories);
        updateTable();
    }
}