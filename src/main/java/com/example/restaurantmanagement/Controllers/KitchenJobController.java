package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.DishService.getDataDish;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class KitchenJobController {


    @FXML
    private TableColumn<?, ?> category_column;

    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<Dish> cooking_table;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> ready_number_column;

    @FXML
    private TableView<Dish> ready_table;


    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    void handeGetInfo(ActionEvent event) {
        changeScene("KitchenRecipeInfo.fxml", "Информация по рецепту", 600, 400, event);

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
    void initialize() throws SQLException {
        cooking_number_column.setCellValueFactory(new PropertyValueFactory<>("iddish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_column.setCellValueFactory(new PropertyValueFactory<>("cost"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        cooking_table.setItems(getDataDish());

        ready_number_column.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        ready_table.setItems(getDataDish());

    }
}
