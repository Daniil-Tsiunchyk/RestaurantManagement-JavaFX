package com.example.restaurantmanagement.Controllers;

import com.example.restaurantmanagement.Entities.OrderedDish;
import com.example.restaurantmanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.restaurantmanagement.Database.DishService.getDataDishForKitchen;
import static com.example.restaurantmanagement.Database.DishService.updateOrderedDishStatus;

public class KitchenJob extends BaseRoleController {


    @FXML
    private TableColumn<?, ?> cooking_number_column;

    @FXML
    private TableView<OrderedDish> cooking_table;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    private TableColumn<?, ?> type_column;


    @FXML
    void handleUpdateOrderStatus(ActionEvent event) throws SQLException {
        Button button = (Button) event.getSource();
        int status = Integer.parseInt(button.getUserData().toString());
        OrderedDish selectedOrderedDish = cooking_table.getSelectionModel().getSelectedItem();
        if (selectedOrderedDish == null) {
            //error_message.setText("Пожалуйста, выберите блюдо");
            return;
        }
        if (status == 1) {
            updateOrderedDishStatus(selectedOrderedDish.getIdOrderedDish(), "PREPARING");
        } else if (status == 2) {
            updateOrderedDishStatus(selectedOrderedDish.getIdOrderedDish(), "SERVED");
        }
        updateTable();
    }

    void updateTable() throws SQLException {
        cooking_number_column.setCellValueFactory(new PropertyValueFactory<>("idOrderedDish"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
        cooking_table.setItems(getDataDishForKitchen());
    }

    @FXML
    void handeGetInfo() {
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
    void initialize() throws SQLException {
        updateTable();
    }

}
