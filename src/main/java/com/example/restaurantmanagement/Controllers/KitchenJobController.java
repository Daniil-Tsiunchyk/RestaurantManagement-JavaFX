package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.OrderedDish;
import com.example.restaurantmanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.DishService.getDataDishForKitchen;
import static com.example.restaurantmanagement.Utils.FxUtils.changeScene;

public class KitchenJobController {


    @FXML
    private TableColumn<?, ?> type_column;

    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<OrderedDish> cooking_table;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> ready_number_column;

    @FXML
    private TableView<OrderedDish> ready_table;

    @FXML
    private TableColumn<?, ?> kol_column;
    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    void handeGetInfo(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("KitchenRecipe.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Информация по рецепту");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void handleLogout(ActionEvent event) {
        changeScene("Authorisation.fxml", "Авторизация", 250, 275, event);
    }


    @FXML
    void handleUpdateOrderStatus(ActionEvent event) {
    }

    @FXML
    void initialize() throws SQLException {
        cooking_number_column.setCellValueFactory(new PropertyValueFactory<>("idordered_dish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("dish_name"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("dish_type"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        kol_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cooking_table.setItems(getDataDishForKitchen());
        ready_number_column.setCellValueFactory(new PropertyValueFactory<>("idordered_dish"));
        ready_table.setItems(getDataDishForKitchen());
    }
}
